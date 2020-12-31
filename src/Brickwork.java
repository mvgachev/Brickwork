import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents a class that simulates a brick work.
 * Has a lower and upper {@link Layout}.
 */
public class Brickwork {
    private Layout lowerLayout;
    private Set<Integer> valueSet;

    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(reader);

        try {
            String[] strNums = in.readLine().split("\\s");
            //Check if the dimensions entered are 2
            if (strNums.length != 2) {
                throw new IOException("The dimensions of the layout should be 2.");
            }
            int[] dims = new int[2];
            for (int i = 0; i < strNums.length; i++) {
                dims[i] = Integer.parseInt(strNums[i]);
            }
            int n = dims[0];
            int m = dims[1];
            //Validate the input of N and M
            if (n >= 100) {
                throw new IOException("The number of lines must be less than 100.");
            }
            if (m >= 100) {
                throw new IOException("The number of columns must be less than 100.");
            }

            int[][] nums = new int[n][m];
            //Enter the numbers of the configuration in two-dimensional array
            for (int l = 0; l < n; l++) {
                strNums = in.readLine().split("\\s");
                //Check the number of ints entered
                if (strNums.length != m) {
                    throw new IOException("The numbers entered on line " + (l + 1) + " are more than m");
                }
                //Put the numbers of the layout in a two-dimensional array
                for (int j = 0; j < m; j++) {
                    nums[l][j] = Integer.parseInt(strNums[j]);
                }
            }
            Brickwork brickwork = new Brickwork();
            brickwork.readLayout(nums);
            brickwork.fillUpperLayout();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Reads the first Layout and checks for mistakes (validates).
     *
     * @param intLayout
     * @throws Exception
     */
    private void readLayout(int[][] intLayout) throws Exception {
        lowerLayout = new Layout(intLayout);
        if (!lowerLayout.validateBricks()) {
            throw new Exception("There is a brick with more or less than 2 squares");
        }
        //Takes the set of values used in the layout
        valueSet = lowerLayout.getValueSet();
    }

    /**
     * Method that fills the upper Layout.
     * Checks that two bricks from the lower and upper layout do not lay in the same boxes.
     */
    private void fillUpperLayout() {
        int n = lowerLayout.getN();
        int m = lowerLayout.getM();
        Layout upperLayout = new Layout(n, m);
        Iterator allValues = valueSet.iterator();

        int currValue = (int) allValues.next();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Square currSquare = lowerLayout.getSquare(i, j);
                //Checks if the current position is available in the upper Layout
                if (upperLayout.getSquare(i, j) == null) {
                    Square rightSquare;
                    //Takes the square to the right if there is one
                    if (j + 1 < m) {
                        rightSquare = lowerLayout.getSquare(i, j + 1);
                    } else { // If there is no square to the right makes it equal to the current square
                        rightSquare = currSquare;
                    }

                    //Checks if both the current and right square have the same value
                    if (currSquare.getValue() != rightSquare.getValue()) {

                        //Creates a new brick in the upper layout with two new squares
                        Square s1 = new Square(currValue, new Coords(i, j));
                        Square s2 = new Square(currValue, new Coords(i, j + 1));
                        upperLayout.setBrick(new Brick(currValue, s1, s2));
                        //Takes a new value for the next brick
                        if (allValues.hasNext()) {
                            currValue = (int) allValues.next();
                        }

                    } else {
                        //Square downSquare = lowerLayout.getSquare(i + 1, j);
                        //Creates a new brick in the upper layout with two new squares
                        Square s1 = new Square(currValue, new Coords(i, j));
                        Square s2 = new Square(currValue, new Coords(i + 1, j));
                        upperLayout.setBrick(new Brick(currValue, s1, s2));
                        if (allValues.hasNext()) {
                            currValue = (int) allValues.next();
                        }
                    }
                }
            }
        }
        //Prints the upper layout in the desired format.
        upperLayout.print();
    }
}