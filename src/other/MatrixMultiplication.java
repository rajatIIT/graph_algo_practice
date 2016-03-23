package other;

/**
 * Solve the matrix multiplication problem :
 * 
 * Given n matrices, find the optimally way to multiply them such that least
 * number of scalar multiplications is required.
 * 
 * An m*n matrix when multipled with an n*r matrix (which outputs an m*r matrix)
 * requires m*n*r multiplications. Thus, to multiply three matrics M1, M2 and
 * M3, with orders p1*q1; q1*q2 and q2*q3 :
 * 
 * 1) if we first multiply M1 with M2, the number of multiplications required is
 * p1*q1*q2 + p1*q2*q3
 * 
 * 2) However, other way round, q1*q2*q3 + p1*q1*q3
 * 
 * Clearly, the two can be different. Thus the number of scalar multiplications
 * required varies.
 * 
 * @author rajatpawar
 *
 */
public class MatrixMultiplication {

    Matrix[] matrixArray;
    static int INFINITY = 1999999999;
    int[][] twoDMemoizationArray;
    boolean memoizationEnabled;

    public int computeMinimumScalerMultiplicationsUsingDynamic(Matrix[] inputMatrices,
            boolean enableMemoization) {

        memoizationEnabled = enableMemoization;
        matrixArray = inputMatrices;
        // the x,y th entry of array represents minimum number of scalar
        // multiplications required to multiply matrices between index i and
        // index j

        twoDMemoizationArray = new int[inputMatrices.length][inputMatrices.length];

        for (int a = 0; a < inputMatrices.length; a++) {
            for (int b = 0; b < inputMatrices.length; b++) {
                twoDMemoizationArray[a][b] = -1;
            }
        }

        minimumScalarMultiplicationsRecursive(0, inputMatrices.length - 1);

        // the simple case of i*i+1 and j*j-1 the
        // minimum number of scalar multiplications are clear.

        // let us compute a recursive implementation

        return 0;
    }

    public int minimumScalarMultiplicationsRecursive(int startI, int endI) {

        if (twoDMemoizationArray[startI][endI] != -1) {
            return twoDMemoizationArray[startI][endI];
        } else {

            System.out.println("Min scalar mul b/w " + startI + " and " + endI);

            // 1 2 3 4 5 6 7 8 9
            int min;
            if (endI == (startI + 2)) {

                // possibility 1 = starti, starti+1, starti+2

                int possibility1part1 = matrixArray[startI].rows * matrixArray[startI].columns
                        * matrixArray[startI + 1].columns;
                int possibility1part2 = matrixArray[startI].rows * matrixArray[startI + 1].columns
                        * matrixArray[startI + 2].columns;
                int possibility1 = possibility1part1 + possibility1part2;
                int possibility2part1 = matrixArray[startI + 1].rows
                        * matrixArray[startI + 1].columns * matrixArray[startI + 2].columns;
                int possibility2part2 = matrixArray[startI].rows * matrixArray[startI + 1].rows
                        * matrixArray[startI + 2].columns;
                int possibility2 = possibility2part1 + possibility2part2;

                System.out.println("---------------------");
                System.out.println("Special three matrices multiplication case. ");
                System.out.println(" 1 and 2 first and then the 3: " + possibility1);
                System.out.println(" 2 and 3 first and then the 1: " + possibility2);
                if (possibility1 < possibility2) {
                    min = possibility1;
                    System.out.println(" Minimum is " + min);
                } else {
                    min = possibility2;
                    System.out.println(" Minimum is " + min);
                }
                return min;
            }

            else if ((startI == (endI + 1)) || (endI == (startI + 1))) {
                min = matrixArray[startI].rows * matrixArray[startI].columns
                        * matrixArray[endI].columns;
                System.out.println("------------------");
                System.out.println("Min is " + min);
                System.out.println("------------------");
                return (matrixArray[startI].rows * matrixArray[startI].columns * matrixArray[endI].columns);
            } else {

                // startI ..........endI
                // start considering (startI,startI) startI+1

                // we want to find the max value of all considerations;

                min = INFINITY;
                int current;

                for (int i = startI + 1; i < (endI - 1); i++) {
                    // initial range : startI,i
                    // final range : i,endI
                    System.out.println(" consider (" + startI + "," + i + ") and (" + (i + 1) + ","
                            + endI + ").");
                    current = minimumScalarMultiplicationsRecursive(startI, i)
                            + minimumScalarMultiplicationsRecursive(i + 1, endI);
                    System.out.println(current + " scalar multiplications.");
                    if (current < min)
                        min = current;
                }
                System.out.println("------------------");
                System.out.println("Min is " + min);
                System.out.println("------------------");
                if (memoizationEnabled)
                    twoDMemoizationArray[startI][endI] = min;
                return min;
            }
        }
    }

    public class Matrix {

        public Matrix(int rows, int columns) {
            this.columns = columns;
            this.rows = rows;
        }

        int columns;
        int rows;
    }

}
