package comp1110.ass1;

public class Hex {

    // The x-coordinate of this hex
    private int x;

    // The y-coordinate of this hex
    private int y;

    // The type of this hex (EMPTY, ICE or PENGUIN)
    private HexType type;

    /**
     * Constructor for the Hex class that allows for specifying the hex's
     * coordinates as well as its type (EMPTY, ICE or PENGUIN).
     *
     * @param x    the hex's x-coordinate
     * @param y    the hex's y-coordinate
     * @param type the hex's type (EMPTY, ICE or PENGUIN)
     */
    public Hex(int x, int y, HexType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * Constructor for the Hex class that only allows for specifying
     * coordinates.
     *
     * @param x the hex's x-coordinate
     * @param y the hex's y-coordinate
     */
    public Hex(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = HexType.EMPTY;
    }

    /**
     * Constructor for the Hex class that only allows for specifying type. Only
     * used for unit tests, so no need to interact with it in your
     * implementation.
     *
     * @param type the type of this hex (EMPTY, ICE or PENGUIN)
     */
    public Hex(HexType type) {
        this.x = -1;
        this.y = -1;
        this.type = type;
    }

    /**
     * @return the x-coordinate of this hex
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return the y-coordinate of this hex
     */
    public int getY() {
        return this.y;
    }

    /**
     * @return the type of this hex
     */
    public HexType getType() {
        return this.type;
    }

    /**
     * Sets the x-coordinate of this hex according to the given parameter.
     *
     * @param x the x-coordinate to which to move this hexagon
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this hex according to the given parameter.
     *
     * @param y the y-coordinate to which to move this hexagon
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the type of this hex according to the given parameter.
     *
     * @param type the hexagon type (EMPTY, ICE or PENGUIN) to which to change
     *             this hex
     */
    public void setType(HexType type) {this.type = type;}

    /**
     * @return whether the hex is of type EMPTY or not
     */
    public boolean isEmpty() {
        // FIXME: Task 2
        return type == HexType.EMPTY;
    }

    /**
     * @return true if this hex's x-coordinate is even, false otherwise
     */
    public boolean isXEven() {
        // FIXME: Task 3
        return x % 2 == 0;
    }

    /**
     * Converts the offset coordinates of this hexagon into cube coordinates.
     * You are not required to use this method in your implementation: it is
     * only used for translating hexagons. However, if you are curious, you can
     * read more about cube coordinates and offset coordinates here:
     * https://www.redblobgames.com/grids/hexagons/#coordinates
     *
     * @return the cube coordinates of this hexagon, from its offset
     *         coordinates
     */
    public int[] getCubeCoordinates() {
        int q = this.x;
        int r = this.y - (this.x + (this.x & 1)) / 2;
        return new int[]{q, r, -q - r};
    }

    /**
     * @param q the coordinate along the first cubic axis
     * @param r the coordinate along the second cubic axis
     * @param s the coordinate along the third cubic axis
     * @return the offset coordinates corresponding to the given cubic
     *         coordinates
     */
    public static int[] getOffsetCoordinates(int q, int r, int s) {
        int x = q;
        int y = r + (q + (q & 1)) / 2;
        return new int[]{x, y};
    }

    /**
     * @param obj the object to compare with this hex
     * @return whether the given object is equal to this hex, according to
     *         their instance fields
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Hex) {
            Hex hex = (Hex) obj;
            return this.x == hex.getX() && this.y == hex.getY() &&
                    this.type == hex.getType();
        }
        return false;
    }

    /**
     * @return the string representation of a hex, given by its coordinates and
     *         hex type
     */
    @Override
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ") " + this.getType().toString();
    }

}
