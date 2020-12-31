/**
 * Represents a Brick with two {@link Square}s and a value.
 */
public class Brick {
    private int value;
    private Square s1;
    private Square s2;

    /**
     * Constructor
     * @param value the value of the Squares
     * @param s1 First Square
     * @param s2 Second Square
     */
    public Brick(int value, Square s1, Square s2) {
        this.value = value;
        this.s1 = s1;
        s1.setBrick(this);
        this.s2 = s2;
        s2.setBrick(this);
    }

    /**
     * Getter for the first {@link Square}.
     * @return s1
     */
    public Square getS1() {
        return s1;
    }

    /**
     * Getter for the second {@link Square}.
     * @return s2
     */
    public Square getS2() {
        return s2;
    }

    /**
     * Getter for the value of the brick.
     * @return s1
     */
    public int getValue() {
        return value;
    }

    /**
     * Method that gets the coordinates of the space between the two squares in a {@link Brick}.
     * This coordinates will be used for the print method in the {@link Layout} class.
     * The coordinates are calculated based on the format requirements.
     */
    public Coords getSpaceCoords(boolean needSpaces) {
        int x1 = s1.getCoords().getX();
        int y1 = s1.getCoords().getY();
        int x2 = s2.getCoords().getX();
        int y2 = s2.getCoords().getY();

        int x, y;

        //Calculates the coordinates of the spaces needed to be put in the char layout
        //Depends on whether only digits are used or not
        if (x1 == x2) {
            x = x1 * 2 + 1;
            if (!needSpaces) {
                if (y1 < y2) {
                    y = y1 * 2 + 2;
                } else {
                    y = y2 * 2 + 2;
                }
            } else {
                if (y1 < y2) {
                    y = (y1 + 1) * 3;
                } else {
                    y = (y2 + 1) * 3;
                }
            }
        } else {
            if (!needSpaces) {
                y = y1 * 2 + 1;
            } else {
                y = y1 * 3 + 1;
            }

            if (x1 < x2) {
                x = x1 * 2 + 2;
            } else {
                x = x2 * 2 + 2;
            }
        }
        return new Coords(x, y);
    }
}
