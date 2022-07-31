package comp1110.ass1;

public enum HexType {
    EMPTY, ICE, PENGUIN;

    /**
     * @param c the first letter of a hex type
     * @return the corresponding hex type, or null if there is no corresponding
     *         hex type
     */
    public static HexType fromChar(char c) {
        return switch (c) {
            case 'E' -> EMPTY;
            case 'I' -> ICE;
            case 'P' -> PENGUIN;
            default -> null;
        };
    }

    /**
     * @return the first character of this hex type's name (eg 'E' for EMPTY,
     *         'I' for ICE and 'P' for PENGUIN)
     */
    public char toChar() {
        return this.toString().charAt(0);
    }
}
