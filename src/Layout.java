import java.util.*;

/**
 * Represents a Layout of {@link Brick}s and {@link Square}s.
 * Has a two dimensional array of {@link Square}s.
 * Has a set of all used values in the layout.
 */
public class Layout {
    private int n;
    private int m;
    private Square[][] layout;
    private Set<Integer> valueSet = new HashSet<>();
    private ArrayList<Brick> allBricks = new ArrayList<>();

    /**
     * Constructor
     *
     * @param layout two-dimensional array of integers
     */
    public Layout(int[][] layout) {
        n = layout.length;
        m = layout[0].length;
        this.layout = new Square[n][m];
        //Create a square for every int in the inputted layout
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int value = layout[i][j];
                Coords coords = new Coords(i, j);
                this.layout[i][j] = new Square(value, coords);
                valueSet.add(value);
            }
        }
    }

    /**
     * Constructor that does not put values in the squares of the layout.
     * Used for the upper Layout.
     *
     * @param n number of lines
     * @param m number of columns
     */
    public Layout(int n, int m) {
        this.n = n;
        this.m = m;
        layout = new Square[n][m];
        //Create a square for every int in the inputted layout
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                layout[i][j] = null;
            }
        }
    }

    /**
     * Getter method for a square in the layout with given coordinates.
     *
     * @param x coordinate
     * @param y coordinate
     * @return the square with x and y coordinates
     */
    public Square getSquare(int x, int y) {
        return layout[x][y];
    }

    /**
     * Puts a {@link Square} in the layout.
     *
     * @param square
     */
    public void setSquare(Square square) {
        int x = square.getCoords().getX();
        int y = square.getCoords().getY();
        layout[x][y] = square;
    }

    /**
     * Puts a {@link Brick} in the Layout.
     *
     * @param brick
     */
    public void setBrick(Brick brick) {
        setSquare(brick.getS1());
        setSquare(brick.getS2());
        allBricks.add(brick);
        valueSet.add(brick.getValue());
    }

    public Set<Integer> getValueSet() {
        return valueSet;
    }

    /**
     * Method that gets the digit length of the max value in the set of all values
     * Decides whether the output needs spaces for the 1-digit numbers for a better format
     *
     * @return the length of the biggest value
     */
    public boolean needSpaces() {
        return (String.valueOf(Collections.max(valueSet)).length() == 2);
    }

    /**
     * Getter for the number of lines in the Layout.
     *
     * @return N
     */
    public int getN() {
        return n;
    }

    /**
     * Getter for the number of columns in the Layout.
     *
     * @return M
     */
    public int getM() {
        return m;
    }

    /**
     * Method that prints the Layout in a readable format.
     */
    public void print() {
        int columns;

        //System.out.print(getMaxValueLength());
        //Check whether to put more columns (for the two-digit numbers) or not
        if (needSpaces()) {
            columns = 3 * m + 1;
        } else {
            columns = 2 * m + 1;
        }
        //Create a two-dimensional array of chars for all the output
        char[][] output = new char[2 * n + 1][columns];

        //Put the new format in the string builder
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            //Put lines of *
            str.append("*".repeat(Math.max(0, columns)));
            str.append("\n");
            //Put the numbers with * between them
            str.append("*");
            for (int j = 0; j < m; j++) {
                //Add a space to the 1-digit numbers when there are 2-digit numbers in the layout
                if (needSpaces() && layout[i][j].getValue() < 10) {
                    str.append(" ");
                }
                str.append(layout[i][j].getValue()).append("*");
            }
            str.append("\n");
        }

        //Put the last line (down - border)
        str.append("*".repeat(Math.max(0, columns)));

        //Transfer the information from string builder to char[][]
        String[] s = str.toString().split("\n");
        for (int i = 0; i < s.length; i++) {
            output[i] = s[i].toCharArray();
        }


        //Put the spaces where needed to indicate a brick (between two squares)
        for (Brick brick : allBricks) {

            int x = brick.getSpaceCoords(needSpaces()).getX();
            int y = brick.getSpaceCoords(needSpaces()).getY();

            //Check if there is a need to put two spaces when numbers are bigger than 9
            if (needSpaces()) {
                if (x % 2 != 0) {
                    output[x][y] = ' ';
                } else {
                    output[x][y] = ' ';
                    output[x][y + 1] = ' ';
                }
            } else {
                output[x][y] = ' ';
            }
        }

        //Print the final output
        for (int i = 0; i < 2 * n + 1; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(output[i][j]);
            }
            System.out.println();
        }

    }

    /**
     * Method that validate that the requirements for brick size are met
     */
    public boolean validateBricks() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                Square currSquare = layout[i][j];

                //Checks if both the current and right square are not part of a brick
                if (!currSquare.hasBrick()) {
                    if (j + 1 < m) {
                        Square rightSquare = layout[i][j + 1];
                        if (!rightSquare.hasBrick() && currSquare.getValue() == rightSquare.getValue()) {
                            //Check right square value
                            new Brick(currSquare.getValue(), currSquare, rightSquare);
                        } //Checks if both the current and down square are not part of a brick
                    } else if (i + 1 < n) {
                        Square downSquare = layout[i + 1][j];
                        if (!downSquare.hasBrick() && currSquare.getValue() == downSquare.getValue()) {
                            //Check down square value
                            new Brick(currSquare.getValue(), currSquare, downSquare);
                        }
                    } else { // Returns false if the square can not be a part of a brick
                        return false;
                    }
                }
            }
        }
        //Returns true if every square has a brick
        return true;
    }
}


