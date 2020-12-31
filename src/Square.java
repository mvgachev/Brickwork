/**
 * Represents a Square of the {@link Layout}.
 * Has an Integer value.
 * Has coordinates {@link Coords}.
 * Is a part of a {@link Brick}.
 */
public class Square {
    private Integer value;
    private Coords coords;
    private Brick brick = null;

    /**
     * Constructor
     * @param value takes the value of the Square
     * @param coords takes the coordinates of the Square
     */
    public Square (Integer value, Coords coords) {
        this.value = value;
        this.coords = coords;
    }

    /**
     * Getter for the value of the Square.
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     * Getter for the coordinates of the Square.
     * @return {@link Coords}
     */
    public Coords getCoords() {
        return coords;
    }

    /**
     * Sets a {@link Brick} for the Square to be part of.
     * @param brick {@link Brick}
     */
    public void setBrick(Brick brick) {
        this.brick = brick;
    }

    /**
     * Getter for the {@link Brick}, the Square is part of.
     * @return {@link Brick}
     */
    public Brick getBrick() {
        return brick;
    }

    /**
     * Checks if the Square is a part of a {@link Brick}.
     * @return true or false
     */
    public boolean hasBrick() {
        return brick != null;
    }
}
