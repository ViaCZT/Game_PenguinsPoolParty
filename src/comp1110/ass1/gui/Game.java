package comp1110.ass1.gui;

import comp1110.ass1.Challenge;
import comp1110.ass1.Hex;
import comp1110.ass1.Ice;
import comp1110.ass1.PenguinsPoolParty;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Game extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;

    private static final double HEX_HEIGHT = 100;
    private static final double HEX_WIDTH = (int) (HEX_HEIGHT * Math.sqrt(3) / 2);

    private static final int MARGIN_X = (int) (HEX_HEIGHT * 0.5);
    private static final int BOARD_X = (int) (HEX_HEIGHT * 0.75);
    private static final int MARGIN_Y = (int) (HEX_HEIGHT * 0.5);
    private static final int BOARD_Y = MARGIN_Y;
    private static final int BOARD_WIDTH = PenguinsPoolParty.BOARD_WIDTH * (int) HEX_WIDTH;
    private static final int BOARD_HEIGHT = (int) (PenguinsPoolParty.BOARD_HEIGHT * HEX_HEIGHT);

    private static final String URI_BASE = "assets/";

    private final Group board = new Group();
    private final Group controls = new Group();
    private final Group blocks = new Group();
    private final Group solution = new Group();
    private final Group penguins = new Group();

    private static final Color[] SOLUTION_COLOURS
            = new Color[]{Color.RED, Color.GREEN, Color.YELLOW, Color.PURPLE};

    private final Slider difficulty = new Slider();

    private final Text completionText = new Text("Well done!");

    private final static String INSTRUCTIONS = """
            Try to get all four ice blocks on the board!
            Drag ice blocks onto the board, and rotate them by right-clicking on them.
            Make sure not to cover any of the penguins with the ice blocks.
            If you're stumped, you can press "/" to see the solution.
            Change difficulty using the slider, and start a new game by pressing the "Restart" button.
            """;

    PenguinsPoolParty penguinsPoolParty;

    class Hexagon extends Polygon {
        double mouseX, mouseY;
        double startX, startY;
        IceBlock iceBlock;

        Hexagon(double startX, double startY, IceBlock iceBlock) {
            this.startX = startX;
            this.startY = startY;
            this.setLayoutX(startX);
            this.setLayoutY(startY);
            this.iceBlock = iceBlock;

            this.addHexagonPoints();

            Color fillColour;
            Color strokeColour;
            if (iceBlock == null) {
                fillColour = Color.DARKBLUE;
                strokeColour = Color.LIGHTBLUE;
            }
            else {
                strokeColour = Color.BLUE;
                if (iceBlock instanceof DraggableIceBlock)
                    fillColour = Color.ALICEBLUE;
                else
                    fillColour = SOLUTION_COLOURS[iceBlock.ice.getId() - 'A'];
            }
            this.setStroke(strokeColour);
            this.setStrokeWidth(2.0);
            this.setStrokeType(StrokeType.INSIDE);
            this.setFill(fillColour);

            if (iceBlock instanceof DraggableIceBlock draggableIceBlock) {

                this.setOnMousePressed(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        this.mouseX = event.getSceneX();
                        this.mouseY = event.getSceneY();
                        if (draggableIceBlock.ice.isOnBoard()) {
                            penguinsPoolParty.removeIceBlock(draggableIceBlock.ice);
                        }
                    }
                    else if (event.getButton() == MouseButton.SECONDARY) {
                        if (draggableIceBlock.ice.isOnBoard()) {
                            penguinsPoolParty.removeIceBlock(draggableIceBlock.ice);
                        }
                        draggableIceBlock.rotate();
                        if (draggableIceBlock.isOnBoard()) {
                            draggableIceBlock.setPosition();
                            if (penguinsPoolParty.isIcePlacementValid(draggableIceBlock.ice)) {
                                draggableIceBlock.snapToGrid();
                                penguinsPoolParty.placeIceBlock(draggableIceBlock.ice);
                            }
                            else {
                                draggableIceBlock.snapToHome();
                            }
                        }
                        event.consume();
                    }
                });

                this.setOnMouseDragged(event -> {
                    draggableIceBlock.toFront();
                    double movementX = event.getSceneX() - this.mouseX;
                    double movementY = event.getSceneY() - this.mouseY;
                    draggableIceBlock.drag(movementX, movementY);
                    this.mouseX = event.getSceneX();
                    this.mouseY = event.getSceneY();
                });

                this.setOnMouseReleased(event -> {
                    if (draggableIceBlock.isOnBoard()) {
                        draggableIceBlock.setPosition();
                        System.out.println(draggableIceBlock.ice);
                        if (penguinsPoolParty.isIcePlacementValid(draggableIceBlock.ice)) {
                            draggableIceBlock.snapToGrid();
                            penguinsPoolParty.placeIceBlock(draggableIceBlock.ice);
                            if (penguinsPoolParty.isSolution())
                                showCompletion();
                        }
                        else {
                            draggableIceBlock.snapToHome();
                        }
                    }
                    else {
                        draggableIceBlock.snapToHome();
                    }
                });
            }
        }

        /**
         * Add the points necessary for each hexagon in the game.
         */
        private void addHexagonPoints() {
            double sideLength = HEX_HEIGHT * 3 / 5;
            double bearing = 0;
            for (int i = 0; i < 6; i++) {
                double x = Math.cos(Math.PI / 18 * bearing) * sideLength;
                double y = Math.sin(Math.PI / 18 * bearing) * sideLength;
                this.getPoints().add(x);
                this.getPoints().add(y);
                bearing += 6;
            }
        }
    }

    class IceBlock extends Group {
        final Ice ice;
        int x;
        int y;
        int rotation;
        Rotate rotate;
        List<Hexagon> hexagons = new ArrayList<>();
        boolean invisible = false;

        IceBlock(char id) {
            if (!(id >= 'A' && id <= 'D')) {
                String errorMsg = "Bad piece id: '" + id + "'";
                throw new IllegalArgumentException(errorMsg);
            }
            this.ice = penguinsPoolParty.getIceBlocks()[id - 'A'];

            for (Hex hex : this.ice.getHexes()) {
                double yOffset = hex.isXEven() ? 0.5 : 0;
                double x = hex.getX() * HEX_WIDTH;
                double y = (hex.getY() + yOffset) * HEX_HEIGHT;
                Hexagon hexagon = new Hexagon(x, y, this);
                this.hexagons.add(hexagon);
                blocks.getChildren().add(hexagon);
            }

            this.rotate = new Rotate();
            this.rotate.setPivotX(0);
            this.rotate.setPivotY(0);
            this.getTransforms().add(this.rotate);
        }

        IceBlock(String placement) {
            this(placement.charAt(0));
            this.x = placement.charAt(1) - '0';
            this.y = placement.charAt(2) - '0';
            this.rotation = placement.charAt(3) - '0';
            this.updateRotation();
            this.snapToGrid();
        }

        /**
         * Update the visual rotation of this ice block.
         */
        protected void updateRotation() {
            int numRotations = this.ice.getId() == 'C' ? 3 : 6;
            this.rotate.setAngle((this.rotation % numRotations) * 60);
            for (Hexagon hexagon : this.hexagons) {
                double radius = Math.hypot(hexagon.startX, hexagon.startY);
                double angle = Math.atan2(hexagon.startY, hexagon.startX);
                double newAngle = angle + Math.toRadians(rotate.getAngle());
                double newX = Math.cos(newAngle) * radius;
                double newY = Math.sin(newAngle) * radius;
                hexagon.setLayoutX(newX);
                hexagon.setLayoutY(newY);
            }
        }

        /**
         * Snap this ice block to the nearest grid position, if it is on the
         * grid.
         */
        protected void snapToGrid() {
            System.out.println("Coordinate: (" + this.x + "," + this.y + ")");
            Hex hex = penguinsPoolParty.getHex(this.x, this.y);
            double yOffset = hex.isXEven() ? 0.5 : 0;
            double[] rotationOffsets = getRotationOffsets();
            this.setLayoutX(BOARD_X + MARGIN_X + this.x * HEX_WIDTH + rotationOffsets[0]);
            this.setLayoutY(BOARD_Y + ((this.y + yOffset)) * HEX_HEIGHT + rotationOffsets[1]);
            System.out.println("Layout: (" + this.getLayoutX() + "," + this.getLayoutY() + ")");
            for (Hexagon hexagon : this.hexagons) {
                hexagon.setTranslateX(this.getLayoutX());
                hexagon.setTranslateY(this.getLayoutY());
                hexagon.setOpacity(1.0);
            }
        }

        protected double[] getRotationOffsets() {
            return switch ((int) this.rotate.getAngle() / 60) {
                case 0 -> new double[]{0.0, 0.0};
                case 1 -> new double[]{0.5 * HEX_WIDTH, 0.25 * HEX_HEIGHT};
                case 2 -> new double[]{0.5 * HEX_WIDTH, 0.75 * HEX_HEIGHT};
                case 3 -> new double[]{0, HEX_HEIGHT};
                case 4 -> new double[]{-0.5 * HEX_WIDTH, 0.75 * HEX_HEIGHT};
                case 5 -> new double[]{-0.5 * HEX_WIDTH, 0.25 * HEX_HEIGHT};
                default -> throw new IllegalStateException("Unexpected value: " + (int) this.rotate.getAngle() / 60);
            };
        }

        /**
         * Determines whether the piece is invisible or not. Used for showing
         * hints/solutions.
         */
        protected void toggleInvisible() {
            double opacity;
            if (this.invisible) {
                this.invisible = false;
                opacity = 1.0;
            }
            else {
                this.invisible = true;
                opacity = 0.0;
            }
            for (Hexagon hexagon : this.hexagons) {
                hexagon.setOpacity(opacity);
            }
        }
    }

    class DraggableIceBlock extends IceBlock {

        double homeX, homeY;

        DraggableIceBlock(char id) {
            super(id);

            double[] coordinates = this.getHomeCoordinates();
            this.homeX = coordinates[0];
            this.homeY = coordinates[1];

            this.snapToHome();
        }

        protected double[] getHomeCoordinates() {
            double distanceX = 3 * HEX_WIDTH;
            double distanceY = 3.5 * HEX_HEIGHT;
            double startX = WINDOW_WIDTH - 2 * MARGIN_X;
            double startY = 4.5 * MARGIN_Y;
            return switch (this.ice.getId()) {
                case 'A' -> new double[]{startX - distanceX, startY};
                case 'B' -> new double[]{startX, startY};
                case 'C' -> new double[]{startX - distanceX, startY + distanceY};
                case 'D' -> new double[]{startX, startY + distanceY};
                default -> throw new IllegalStateException("Unexpected value: " + this.ice.getId());
            };
        }

        protected void drag(double movementX, double movementY) {
            this.setLayoutX(this.getLayoutX() + movementX);
            this.setLayoutY(this.getLayoutY() + movementY);
            for (Hexagon hexagon : this.hexagons) {
                hexagon.setTranslateX(this.getLayoutX());
                hexagon.setTranslateY(this.getLayoutY());
                hexagon.setOpacity(0.5);
                hexagon.toFront();
            }
        }

        /**
         * @return whether this piece is on the game board or not
         */
        private boolean isOnBoard() {
            return getLayoutX() > (BOARD_X - HEX_WIDTH) && getLayoutX() < (BOARD_X + BOARD_WIDTH) &&
                    getLayoutY() > (BOARD_Y - HEX_HEIGHT) && getLayoutY() < (BOARD_Y + BOARD_HEIGHT);
        }

        /**
         * Snaps this draggable ice block to its home location, if it is not on
         * the grid.
         */
        private void snapToHome() {
            this.setLayoutX(this.homeX);
            this.setLayoutY(this.homeY);

            for (Hexagon hexagon : this.hexagons) {
                hexagon.setTranslateX(this.getLayoutX());
                hexagon.setTranslateY(this.getLayoutY());
                hexagon.setOpacity(1.0);
            }

            this.setRotate(0.0);
            this.setOpacity(1.0);
        }

        /**
         * Rotates this draggable ice block by 60 degrees.
         */
        private void rotate() {
            this.rotation = (this.rotation + 1) % 6;
            this.updateRotation();
            this.ice.rotate60Degrees();
        }

        private void setPosition() {
            double[] rotationOffsets = this.getRotationOffsets();
            this.x = (int) ((this.getLayoutX() - BOARD_X - rotationOffsets[0]) / HEX_WIDTH);
            Hex hex = penguinsPoolParty.getHex(this.x, 0);
            double yOffset = hex.isXEven() ? 0.5 : 0;
            this.y = (int) ((this.getLayoutY() - yOffset - BOARD_Y - rotationOffsets[1]) / HEX_HEIGHT);
            Hex destHex = penguinsPoolParty.getHex(this.x, this.y);
            this.ice.translate(destHex);
        }

        @Override
        public String toString() {
            return this.ice.toString();
        }
    }

    private void setUpHandlers(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.Q) {
                Platform.exit();
                event.consume();
            } else if (event.getCode() == KeyCode.SLASH) {
                this.toggleSolutionVisibility();
                event.consume();
            }
        });
//        scene.setOnKeyReleased(event -> {
//            if (event.getCode() == KeyCode.SLASH) {
//                this.toggleSolutionVisibility();
//                event.consume();
//            }
//        });
    }

    private void toggleSolutionVisibility() {
        for (Node node : solution.getChildren()) {
            if (node instanceof IceBlock) {
                IceBlock piece = (IceBlock) (node);
                piece.toggleInvisible();
            }
        }
    }

    private void makeSolution(String solution) {
        this.solution.getChildren().clear();
        if (solution.isEmpty()) return;

        if (solution.length() != 16) {
            throw new IllegalArgumentException("Solution incorrect length: " + solution);
        }
        for (int i = 0; i < solution.length(); i += 4) {
            IceBlock piece = new IceBlock(solution.substring(i, i + 4));
            piece.toggleInvisible();
            this.solution.getChildren().add(piece);
        }
    }

    private void makeBoard() {
        this.board.setLayoutX(BOARD_X + MARGIN_X);
        this.board.setLayoutY(BOARD_Y + MARGIN_Y);
        this.board.getChildren().clear();
        for (int y = 0; y < PenguinsPoolParty.BOARD_HEIGHT; y++) {
            for (int x = 0; x < PenguinsPoolParty.BOARD_WIDTH; x++) {
                Hex hex = this.penguinsPoolParty.getHex(x, y);
                double yOffset = hex.isXEven() ? 0.5 : 0;
                double startX = x * HEX_WIDTH;
                double startY = (y + yOffset) * HEX_HEIGHT;
                Hexagon hexagon = new Hexagon(startX, startY, null);
                this.board.getChildren().add(hexagon);
            }
        }
        this.board.toBack();
    }

    private void makePenguins(Challenge challenge) {
        double imgOffsetX = - HEX_WIDTH / 2;
        double imgOffsetY = - HEX_HEIGHT / 2;
        this.penguins.getChildren().clear();
        String initialState = challenge.getInitialState();
        for (int i = 0; i < initialState.length(); i += 2) {
            Image penguinImage = new Image(Game.class.getResource(URI_BASE + "penguin.png").toString());
            ImageView penguin = new ImageView(penguinImage);
            penguin.setFitHeight(HEX_HEIGHT);
            penguin.setFitWidth(HEX_WIDTH);
            int x = initialState.charAt(i) - '0';
            int y = initialState.charAt(i + 1) - '0';
            Hex hex = this.penguinsPoolParty.getHex(x, y);
            double yOffset = hex.isXEven() ? 0.5 : 0;
            penguin.setLayoutX(x * HEX_WIDTH + imgOffsetX);
            penguin.setLayoutY((y + yOffset) * HEX_HEIGHT + imgOffsetY);
            this.penguins.getChildren().add(penguin);
        }
        this.penguins.setLayoutX(BOARD_X + MARGIN_X);
        this.penguins.setLayoutY(BOARD_Y + MARGIN_Y);
    }

    private void makeBlocks() {
        this.blocks.getChildren().clear();
        for (char id = 'A'; id < 'E'; id++) {
            DraggableIceBlock draggableIceBlock = new DraggableIceBlock(id);
            this.blocks.getChildren().add(draggableIceBlock);
        }
    }

    private void makeControls() {
        Button button = new Button("Restart");
        button.setLayoutX(MARGIN_X + 90);
        button.setLayoutY(WINDOW_HEIGHT - 45);
        button.setOnAction(e -> this.newGame());
        this.controls.getChildren().add(button);

        Button instructions = new Button("Instructions");
        instructions.setLayoutX(MARGIN_X + 180);
        instructions.setLayoutY(WINDOW_HEIGHT - 45);
        instructions.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Instructions and Controls");
            alert.setContentText(INSTRUCTIONS);
            alert.show();
        });
        this.controls.getChildren().add(instructions);

        this.difficulty.setMin(0);
        this.difficulty.setMax(3);
        this.difficulty.setValue(0);
        this.difficulty.setShowTickLabels(true);
        this.difficulty.setShowTickMarks(true);
        this.difficulty.setMajorTickUnit(1);
        this.difficulty.setSnapToTicks(true);

        this.difficulty.setLayoutX(MARGIN_X + 60);
        this.difficulty.setLayoutY(WINDOW_HEIGHT - 85);
        this.controls.getChildren().add(this.difficulty);

        final Label difficultyCaption = new Label("Difficulty:");
        difficultyCaption.setTextFill(Color.GRAY);
        difficultyCaption.setLayoutX(MARGIN_X);
        difficultyCaption.setLayoutY(WINDOW_HEIGHT - 85);
        this.controls.getChildren().add(difficultyCaption);
    }

    private void makeCompletion() {
        DropShadow ds = new DropShadow();
        ds.setOffsetY(4.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        this.completionText.setFill(Color.RED);
        this.completionText.setEffect(ds);
        this.completionText.setCache(true);
        this.completionText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 80));
        this.completionText.setLayoutX(WINDOW_WIDTH / 2.0 - 200);
        this.completionText.setLayoutY(350);
        this.completionText.setTextAlignment(TextAlignment.CENTER);
        this.root.getChildren().add(this.completionText);
    }

    private void showCompletion() {
        this.completionText.toFront();
        this.completionText.setOpacity(1.0);
    }

    private void hideCompletion() {
        this.completionText.toBack();
        this.completionText.setOpacity(0.0);
    }

    private void newGame() {
        try {
            this.hideCompletion();
            int difficulty = (int) this.difficulty.getValue();
            Challenge challenge = Challenge.newChallenge(difficulty);
            System.out.println("Challenge number: " + challenge.getProblemNumber());
            this.penguinsPoolParty = new PenguinsPoolParty(challenge);
            this.makeBlocks();
            this.makePenguins(challenge);
            String solution = this.penguinsPoolParty.findSolution();
            System.out.println(solution);
            for (Ice ice : penguinsPoolParty.getIceBlocks()) {
                if (ice.isOnBoard()) penguinsPoolParty.removeIceBlock(ice);
                ice.translate(penguinsPoolParty.getHex(0, 0));
                while (ice.getRotation() != 0) ice.rotate60Degrees();
            }
            this.makeSolution(solution);
        } catch (IllegalArgumentException e) {
            System.err.println("Uh oh. " + e);
            e.printStackTrace();
            Platform.exit();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Penguins Pool Party");
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.root.getChildren().add(this.blocks);
        this.root.getChildren().add(this.board);
        this.root.getChildren().add(this.solution);
        this.root.getChildren().add(this.controls);
        this.root.getChildren().add(this.penguins);

        this.newGame();
        this.makeBoard();
        this.makeControls();
        this.makeCompletion();
        this.setUpHandlers(scene);

        stage.setScene(scene);
        stage.show();
    }
}
