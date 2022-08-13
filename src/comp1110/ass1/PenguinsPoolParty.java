package comp1110.ass1;

import java.util.ArrayList;
import java.util.List;

public class PenguinsPoolParty {

    // The game board
    public Hex[][] board;

    // The given challenge for the game
    public Challenge challenge;

    // The width of the board
    public final static int BOARD_WIDTH = 5;

    // The height of the board
    public final static int BOARD_HEIGHT = 4;

    // An array containing all four ice blocks for the game. Please consult the
    // readme for more details about ice blocks.
    //
    // Please note that positions instantiated for each ice block are not
    // permanent. Each ice block has an `onBoard` field that determines whether
    // they are on the board or not. The positions used here are simply used
    // for your ease in viewing (as opposed to negative coordinates, which are
    // harder on the eyes).
    private final Ice[] iceBlocks = new Ice[]{
            new Ice('A', new int[]{0, 0, 0, -1, -1, -1, -2, -1}, 0), // Ice block A
            new Ice('B', new int[]{0, 0, 0, -1, -1, -1, -2, -2}, 0), // Ice block B
            new Ice('C', new int[]{0, 0, 0, -1, -1, -1, -1, -2}, 0), // Ice block C
            new Ice('D', new int[]{0, 0, 0, -1, 0, -2, -1, -1}, 0) // Ice block D
    };

    /**
     * Instantiates the game according to a given challenge. This defines the
     * challenge number and the initial placement of penguins on the board.
     *
     * @param challenge the challenge according to which to set up the game
     */
    public PenguinsPoolParty(Challenge challenge) {
        this.challenge = challenge;
        this.initialiseBoard(challenge);
    }

    /**
     * Constructs an instance of the Penguins Pool Party using a given initial
     * state and an array of ice block placements. Note that there is no
     * challenge corresponding to this constructor.
     *
     * This constructor is used only for unit tests. You shouldn't find any
     * need to use it for your own implementation.
     *
     * @param initialState  the initial state of the board
     * @param icePlacements the ice block placements made on the board
     */
    public PenguinsPoolParty(String initialState, Ice[] icePlacements) {
        this.challenge = null;
        this.board = new Hex[5][4];
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                HexType type = HexType.fromChar(initialState.charAt(5 * y + x));
                this.board[x][y] = new Hex(x, y, type);
            }
        }
        for (Ice ice : icePlacements) {
            Ice iceBlock = iceBlocks[ice.getId() - 'A'];
            iceBlock.setOnBoard(true);
            iceBlock.setOriginX(ice.getOriginX());
            iceBlock.setOriginY(ice.getOriginY());
            iceBlock.setRotation(ice.getRotation());
            iceBlock.setHexes(ice.getHexes());
        }
    }

    /**
     * Instantiates the game according to a predefined board state, in String
     * form. This constructor is only used for unit tests, you most likely
     * won't need to use it in your implementation.
     *
     * @param initialState the initial state of the board in String form
     */
    public PenguinsPoolParty(String initialState) {
        this.challenge = null;
        this.board = new Hex[5][4];
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                HexType type = HexType.fromChar(initialState.charAt(5 * y + x));
                this.board[x][y] = new Hex(x, y, type);
            }
        }
    }

    /**
     * Instantiates the game with no pre-defined challenge and an empty board.
     */
    public PenguinsPoolParty() {
        this("EEEEEEEEEEEEEEEEEEEE");
    }

    /**
     * Instantiates the board according to the initial state of the given
     * challenge.
     *
     * @param challenge the challenge with which to initialise the board
     */
    private void initialiseBoard(Challenge challenge) {
        this.board = new Hex[5][4];
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                this.board[x][y] = new Hex(x, y, HexType.EMPTY);
            }
        }
        String penguins = challenge.getInitialState();
        for (int i = 0; i < penguins.length(); i += 2) {
            this.setHex(penguins.charAt(i) - '0', penguins.charAt(i + 1) - '0', HexType.PENGUIN);
        }
    }

    /**
     * @return the state of the board, in String form
     */
    public String boardToString() {
        StringBuilder s = new StringBuilder();
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                s.append(this.getHex(x, y).getType().toChar());
            }
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * @param x the x-coordinate of the hex
     * @param y the y-coordinate of the hex
     * @return  the hex at the given coordinates
     */
    public Hex getHex(int x, int y) {
        return this.board[x][y];
    }

    /**
     * Sets the hex at the given coordinates on this game's board to be the
     * given type.
     *
     * @param x    the x-coordinate of the hex to change
     * @param y    the y-coordinate of the hex to change
     * @param type the hex type that this hex will have (out of EMPTY, ICE and
     *             PENGUIN)
     */
    public void setHex(int x, int y, HexType type) {
        this.board[x][y].setType(type);
    }

    /**
     * @return the ice blocks in this game
     */
    public Ice[] getIceBlocks() {
        return this.iceBlocks;
    }

    public Ice applyPlacement(String placement) {
        int idx = placement.charAt(0) - 'A';
        Ice ice = this.getIceBlocks()[idx];
        int x = placement.charAt(1) - '0';
        int y = placement.charAt(2) - '0';
        ice.translate(this.getHex(x, y));
        int r = placement.charAt(3) - '0';
        while (ice.getRotation() != r) ice.rotate60Degrees();
        return ice;
    }

    public String getSolutionString() {
        StringBuilder res = new StringBuilder();
        for (Ice i : this.getIceBlocks()) {
            res.append(i.toString());
        }
        return res.toString();
    }

    /**
     * Returns an array of hexes, representing the neighbours of a given hex on
     * the board. The neighbours are returned in clockwise order, starting from
     * the hex directly on top of the given hex. You might like to think of the
     * first neighbouring hex as the one at 12 o'clock of the given hex. If a
     * potential neighbour is off the board, its place in the resulting array
     * should contain `null`.
     *
     * Hint 1: the locations of the neighbours of a hex depend on the x-coordinate
     * of that hex. You might want to break this method up into sub-problems
     * depending on the x-coordinate of the given hex. Consult the readme for
     * the design of the board, and draw out the board on paper if you need.
     *
     * Hint 2: Make sure you use the `board` field provided to you in this
     * class. This is important for later tasks. Please do not create any new
     * instances of the Hex class in your implementation.
     *
     * @param hex the hex whose neighbours we wish to find
     * @return the neighbours of the given hex, in the order: <north neighbour>,
     *         <north-east neighbour>, <south-east neighbour>, <south neighbour>,
     *         <south-west neighbour>, <north-west neighbour>.
     */
    public Hex[] getNeighbours(Hex hex) {
        // FIXME: Task 4
        Hex[] myHex = new Hex[6];
        //应该返回已经在棋盘上的Hex，而不是新建Hex。因为棋盘上的Hex还有type属性(EMPTY, ICE or PENGUIN)，而新建的无
        //必须先判断Hex是否在盘上，否则 getHex()的时候会out of bounds，用myGetHex()来判断
        if (hex.getX() % 2 != 0){
            myHex[0] = myGetHex(hex.getX(), hex.getY()-1);
            myHex[1] = myGetHex(hex.getX()+1, hex.getY()-1);
            myHex[2] = myGetHex(hex.getX()+1, hex.getY());
            myHex[3] = myGetHex(hex.getX(), hex.getY()+1);
            myHex[4] = myGetHex(hex.getX()-1, hex.getY());
            myHex[5] = myGetHex(hex.getX()-1, hex.getY()-1);
        }else {
            myHex[0] = myGetHex(hex.getX(), hex.getY()-1);
            myHex[1] = myGetHex(hex.getX()+1, hex.getY());
            myHex[2] = myGetHex(hex.getX()+1, hex.getY()+1);
            myHex[3] = myGetHex(hex.getX(), hex.getY()+1);
            myHex[4] = myGetHex(hex.getX()-1, hex.getY()+1);
            myHex[5] = myGetHex(hex.getX()-1, hex.getY());
        }
        return myHex;
    }
    public Hex myGetHex(int x, int y) {
        if (x<0 || x>4 || y<0 || y>3){
            return null;
        }else {
            return getHex(x,y);
        }
    }

    /**
     * Determine whether the current board represents a solution to the game.
     * Remember, for a board state to be a solution to the game, all four ice
     * blocks must be placed on the board.
     *
     * Hint: can you think of a way to know whether all four ice blocks are on
     * the board without knowing their positions or rotations?
     *
     * @return whether the current board represents a solution to the game
     */
    public boolean isSolution() {
        // FIXME: Task 6
        return iceBlocks[0].isOnBoard() && iceBlocks[1].isOnBoard() &&
                iceBlocks[2].isOnBoard() && iceBlocks[3].isOnBoard();
    }

    /**
     * Determine whether a placement of ice is valid according to this game's
     * board. You can obtain the placement of each hexagon in the ice block by
     * using the `getHexes()` method in the `Ice` class.
     *
     * For an ice block placement to be valid, each hexagon in the ice block
     * must:
     *
     * 1. be on the board, that is, no hexagon can be hanging off the board;
     *    and
     * 2. not overlap with any penguins or other ice blocks that have already
     *    been placed on the board.
     *
     * You do not need to worry about duplicate ice blocks. That is, you can
     * assume that the given ice block is not already on the board.
     *
     * @param ice the ice block to place on the board, at positions according
     *            to the ice block's hexagons
     * @return    whether the placement of the given ice block is valid
     *            according to the game rules
     */
    public boolean isIcePlacementValid(Ice ice) {
        // FIXME: Task 7
        for (int i=0; i<4; i++){
            Hex iceHex = ice.getHexes()[i];
            if (iceHex.getX()<0 || iceHex.getX()>4 || iceHex.getY()<0 || iceHex.getY()>3){
                return false;
            }
        }
        for (int i=0; i<4; i++){
            Hex iceHex = ice.getHexes()[i];
            Hex broadHex = getHex(iceHex.getX(), iceHex.getY());
            if (broadHex.getType() == HexType.ICE || broadHex.getType() == HexType.PENGUIN){
                return false;
            }
        }
        return true;
    }

    /**
     * Place an ice block on the board.
     *
     * Note that, for this method, you must change the board according to the
     * placement of the ice blocks. That is, you must change all hexes on the
     * board to type ICE that share a coordinate with one of the hexes in the
     * ice block.
     *
     * Note that you can assume that the placement of the ice block is valid.
     *
     * Once the ice block is successfully placed on the board, you will need
     * to modify it so that it is on the board. You will need to use the
     * `setOnBoard()` method in the Ice class to do this.
     *
     * Hint: you might find the `setHex()` method useful to solve this task.
     *
     * @param ice the ice block to place on the board
     */
    public void placeIceBlock(Ice ice) {
        // FIXME: Task 8a
        for (int i=0; i<4; i++){
            Hex iceHex = ice.getHexes()[i];
            setHex(iceHex.getX(), iceHex.getY(), iceHex.getType());
        }
        ice.setOnBoard(true);
    }

    /**
     * Remove an ice block from the board.
     *
     * Note that, for this method, you must change the board according to the
     * placement of the ice blocks. That is, you must change all hexes on the
     * board to type EMPTY that share a coordinate with one of the hexes in the
     * ice block to be removed.
     *
     * Note that you can assume that the removal of the ice block is valid,
     * that is that the ice block is validly placed and is already on the
     * board.
     *
     * Once the ice block is successfully removed from the board, you will need
     * to modify it so that it is off the board. You will need to use the
     * `setOnBoard()` method in the Ice class to do this.
     *
     * Hint: you might find the `setHex()` method useful to solve this task.
     *
     * @param ice the ice block to remove from the board
     */
    public void removeIceBlock(Ice ice) {
        // FIXME: Task 8b
        for (int i=0; i<4; i++){
            Hex iceHex = ice.getHexes()[i];
            setHex(iceHex.getX(), iceHex.getY(), HexType.EMPTY);
        }
        ice.setOnBoard(false);
    }

    /**
     * Get all the valid ice block placements from this game's board state.
     *
     * The ice blocks can be accessed from the `iceBlocks` field. Note that an
     * ice block should not be placed on the board if it is already on the
     * board. You can determine this using the `isOnBoard` field of the `Ice`
     * class.
     *
     * Return the array elements in alphabetical order: that is, first order by
     * ice block ID, then by origin x-coordinate, then by origin y-coordinate,
     * and finally by rotation.
     *
     * Note that this task is particularly difficult, and may require knowledge
     * of concepts beyond what has been taught in the course so far. If you
     * feel stuck on this problem, I would recommend you come back to it later
     * once we have covered the relevant topics in class. Remember, this
     * assignment is redeemable against the exam, so it is not particularly
     * important to complete all tasks in this assignment.
     *
     * @return all valid ice block placements from this game's board state
     */
    public String[] getAllValidPlacements() {
        // FIXME: Task 10
//      1.for each一般用在遍历数组和集合，好处是不必考虑指定下标值。
//      2.for each的语法语句格式：
//        for (元素类型type  元素变量x : 遍历对象obj) {
//            引用x的java语句;　　
//        }
//        for (Ice i : iceBlocks) {
//            System.out.println(i);
//        }
        ArrayList<String> resulist = new ArrayList<>();
        for (Ice ice : iceBlocks){
            if (!ice.isOnBoard()){
                for (int x = 0; x < BOARD_WIDTH; x++){
                    for (int y = 0; y < BOARD_HEIGHT; y++){
                        //遍历板上的Hex
                        if (getHex(x,y).getType() == HexType.EMPTY){
                            ice.translate(getHex(x,y));
                            //因为冰块C是对称的，所以只能转3次
                            if (ice.getId()=='C'){
                                for (int i=0; i<3; i++){
                                    if (isIcePlacementValid(ice)){ //ice的四个hex全都在板上
                                        resulist.add(ice.toString());
                                        ice.rotate60Degrees();
                                    }else {
                                        ice.rotate60Degrees();
                                    }
                                }
                            }else {
                                for (int i=0; i<6; i++){
                                    if (isIcePlacementValid(ice)){ //ice的四个hex全都在板上
                                        resulist.add(ice.toString());
                                        ice.rotate60Degrees();
                                    }else {
                                        ice.rotate60Degrees();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
//        System.out.println(resulist);
        String[] resulString = resulist.toArray(new String[0]);
        /**
         * 语法：arraylist.toArray(T[] arr)  T指数组的类型
         * String长度可以是0，因为ArrayList的toArray方法定义的是泛型（参数化类型），
         *      即所操作的数据类型被指定为一个参数
         * 等价于
         * String[] resulString = resulist.toArray(new String[resulist.size()]);
         * 等价于以下2句
         * String[] resulString = new String[resulist.size()];
         * resulist.toArray(resulString);
         * 下面这句是错的，因为如果不指定数组的数据类型，默认是object类型，而object不能强制转换成其他类型
         * String[] resulString = (String[]) resulist.toArray();
         */
        return resulString;
    }

    /**
     * Find the solution to this game.
     *
     * The solution is a string, represented by:
     *
     * {String representation of ice block A}{String representation of ice block B}
     * {String representation of ice block C}{String representation of ice block D}
     *
     * Please consult the readme for solution and ice block encodings.
     *
     * Note that this task is particularly difficult, and may require knowledge
     * of concepts beyond what has been taught in the course so far. If you
     * feel stuck on this problem, I would recommend you come back to it later
     * once we have covered the relevant topics in class. Remember, this
     * assignment is redeemable against the exam, so it is not particularly
     * important to complete all tasks in this assignment.
     *
     * @return the solution to this game, in String form
     */
    public String findSolution() {
        // FIXME: Task 11
        return "";
    }

}
