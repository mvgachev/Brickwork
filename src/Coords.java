/**
 * Represents coordinates in a layout or matrix.
 */
public class Coords {
    private int x;
    private int y;

    /**
     * Constructor
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x coordinate.
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y coordinate.
     *
     * @return y
     */
    public int getY() {
        return y;
    }
}
