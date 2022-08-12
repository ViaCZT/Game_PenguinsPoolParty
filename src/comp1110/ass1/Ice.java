package comp1110.ass1;

public class Ice {

    // The hexagons making up the ice block
    private Hex[] hexes;

    // The ID of the block. This is one of {'A', 'B', 'C', 'D'}.
    private final char id;

    // The x-coordinate of the ice block's origin
    private int originX;

    // The y-coordinate of the ice block's origin
    private int originY;

    // The rotation of the ice block
    private int rotation;

    // Whether the ice block is on the board or not
    private boolean onBoard;

    /**
     * Constructs an ice block according to a given ID and the position of each
     * hexagon. Note that two elements in the `positions` parameter make up a
     * Cartesian coordinate: the first is the x-coordinate, and the second is
     * the y-coordinate.
     *
     * The first Cartesian coordinate in the `positions` parameter defines the
     * coordinate of the origin.
     *
     * @param id        the ID of the ice block (out of 'A', 'B', 'C', 'D')
     * @param positions the Cartesian coordinates of each hexagon in the ice
     *                  block
     */
    public Ice(char id, int[] positions, int rotation) {
        assert id >= 'A' && id <= 'D';
        this.id = id;
        this.hexes = new Hex[positions.length / 2];
        for (int i = 0; i < positions.length; i += 2) {
            int x = positions[i];
            int y = positions[i + 1];
            this.hexes[i / 2] = new Hex(x, y, HexType.ICE);
        }
        this.originX = positions[0];
        this.originY = positions[1];
        this.rotation = rotation;
        this.onBoard = false;
    }

    /**
     * @return the string ID of this ice block
     */
    public char getId() {
        return this.id;
    }

    /**
     * @return the hexagons comprising this ice block
     */
    public Hex[] getHexes() {
        return this.hexes;
    }

    /**
     * Set the hexes making up this ice block.
     *
     * @param hexes the hexes which will make up this ice block
     */
    public void setHexes(Hex[] hexes) {
        this.hexes = hexes;
    }

    /**
     * @return the x-coordinate of the origin of this ice block
     */
    public int getOriginX() {
        return this.originX;
    }

    /**
     * Sets the x-coordinate of the origin of this ice block.
     *
     * @param x the value of the x-coordinate of the origin of this ice block
     */
    public void setOriginX(int x) {
        this.originX = x;
    }

    /**
     * @return the y-coordinate of the origin of this ice block
     */
    public int getOriginY() {
        return this.originY;
    }

    /**
     * Sets the y-coordinate of the origin of this ice block.
     *
     * @param y the value of the y-coordinate of the origin of this ice block
     */
    public void setOriginY(int y) {
        this.originY = y;
    }

    /**
     * @return the rotation of this ice block
     */
    public int getRotation() {
        return this.rotation;
    }

    /**
     * Sets the rotation value of this ice block to a given value between 0 and
     * 5 inclusive.
     *
     * @param r the rotation value to assign to this ice block
     */
    public void setRotation(int r) {
        assert r >= 0 && r <= 5;
        this.rotation = r;
    }

    /**
     * @return whether this ice block is on the board or not
     */
    public boolean isOnBoard() {
        return this.onBoard;
    }

    /**
     * Changes whether this piece is on the board or not.
     *
     * @param onBoard whether this piece is on the board
     */
    public void setOnBoard(boolean onBoard) {
        this.onBoard = onBoard;
    }

    /**
     * Move the ice block to a destination origin. Moves the entire ice block
     * by the vector [destination - origin].
     *
     * @param destHex the destination to which to move this ice block
     */
    public void translate(Hex destHex) {
        int[] destCube = destHex.getCubeCoordinates();
        int[] originCube = this.hexes[0].getCubeCoordinates();
        int[] vectorCube = new int[3];
        for (int i = 0; i < 3; i++) {
            vectorCube[i] = destCube[i] - originCube[i];
        }
        for (Hex hex : this.hexes) {
            int[] cubeCoords = hex.getCubeCoordinates();
            for (int i = 0; i < 3; i++) {
                cubeCoords[i] += vectorCube[i];
            }
            int[] newOffsetCoords = Hex.getOffsetCoordinates(cubeCoords[0], cubeCoords[1], cubeCoords[2]);
            hex.setX(newOffsetCoords[0]);
            hex.setY(newOffsetCoords[1]);
        }
        this.originX = this.hexes[0].getX();
        this.originY = this.hexes[0].getY();
    }

    /**
     * Rotate the ice block 60 degrees clockwise, and then translate(平移) it back to
     * its origin.
     */
    public void rotate60Degrees() {
        Hex origin = new Hex(this.hexes[0].getX(), this.hexes[0].getY());
        for (Hex hex : this.hexes) {
            int[] cube = hex.getCubeCoordinates();
            int[] rotateCoords = Hex.getOffsetCoordinates(-cube[1], -cube[2], -cube[0]);
            hex.setX(rotateCoords[0]);
            hex.setY(rotateCoords[1]);
        }
        this.rotation = (this.rotation + 1) % 6;
        this.fixSymmetries();
        translate(origin);
    }

    /**
     * Fix the symmetrical 'C' ice block, so that its rotation is one of
     * {0, 1, 2}.
     *
     * If the ice block is not of ID 'C', or it is in a rotation between 0 and
     * 2 inclusive, then this method should do nothing. Otherwise, the method
     * should change the rotation as follows:
     *
     * 3 -> 0;
     * 4 -> 1; and
     * 5 -> 2
     *
     * and the origin coordinate of this ice block should also be changed
     * accordingly. Finally, the `hexes` field of this ice block should be
     * reversed, so that it matches the order of the hexes in this ice block
     * moving from the origin along this ice block.
     *
     * For more details about ice block rotations, please consult the readme.
     */
    public void fixSymmetries() {
        // FIXME: Task 9
        if (id == 'C' && rotation > 2){
            switch (rotation){
                case 3 -> {
                    rotation = 0;
                    if (originX % 2 == 0){
                        originX += 1;
                        originY += 3;

                    }else {
                        originX += 1;
                        originY += 2;
                    }
                }
                case 4 -> {
                    rotation = 1;
                    originX -= 2;
                    originY += 2;
                }
                case 5 -> {
                    rotation = 2;
                    if (originX % 2 == 0){
                        originX -= 3;
                    }else {
                        originX -= 3;
                        originY -= 1;
                    }
                }
            }
            Hex temHex1 = new Hex(hexes[0].getX(), hexes[0].getY(),hexes[0].getType());
            hexes[0] = hexes[3];
            hexes[3] = temHex1;
            Hex temHex2 = new Hex(hexes[1].getX(), hexes[1].getY(),hexes[1].getType());
            hexes[1] = hexes[2];
            hexes[2] = temHex2;
        }
    }

    /**
     * @param obj the object to compare with this ice block
     * @return whether the given object is equal to this ice block, according
     *         to their instance fields
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ice ice) {
            if (this.id != ice.getId()) return false;
            if (this.rotation != ice.getRotation()) return false;
            if (this.hexes.length != ice.getHexes().length) return false;
            for (int i = 0; i < this.hexes.length; i++) {
                if (!this.hexes[i].equals(ice.getHexes()[i])) return false;
            }
            return true;
        }
        return false;
    }

    /**
     * @return the string representation of this ice block, given by its ID,
     *         origin coordinates and rotation
     */
    @Override
    public String toString() {
        return this.id + "" + this.originX + this.originY + this.rotation;
    }

}
