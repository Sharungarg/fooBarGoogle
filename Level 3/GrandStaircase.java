public class GrandStaircase {
    private static int[][] matrix;
    private static boolean isMatrixGenerated = false;

    public static int solution(int n) {
        if (!isMatrixGenerated) generateCombinations();
        int row = n % 2 != 0 ? (n + 1) / 2 : (n / 2);
        return matrix[row][n] - 1;
    }

    private static void generateCombinations() {
        int rows = 101; //101
        int cols = 201; //201
        int[][] dummyMatrix = new int[rows][cols];
        dummyMatrix[0][0] = 1;
        for (int row = 1; row <= rows - 1; row++) {
            int oddSummand = (2 * row) - 1;
            for (int col = 0; col <= cols - 1; col++) {
                dummyMatrix[row][col] = dummyMatrix[row - 1][col];
                if (oddSummand <= col) {
                    dummyMatrix[row][col] += dummyMatrix[row][col - oddSummand];
                }
            }
        }
        matrix = dummyMatrix;
        isMatrixGenerated = true;
    }

    public static void main(String[] args) {
        // Printing the generated matrix
//        int[][] result = generateCombinations();
//        for (int[] i : result) {
//            System.out.println(Arrays.toString(i));
//        }

        // Testing against hardcoded values
//        int[] reference = {1, 1, 1, 2, 2, 3, 4, 5, 6, 8, 10, 12, 15, 18, 22, 27, 32, 38, 46, 54, 64, 76, 89, 104, 122, 142, 165, 192, 222, 256, 296, 340, 390, 448, 512, 585, 668, 760, 864, 982, 1113, 1260, 1426, 1610, 1816, 2048, 2304, 2590, 2910, 3264, 3658, 4097, 4582, 5120, 5718, 6378};
//        for (int i = 3; i <= 55; i++) {
//            System.out.println(reference[i]);
//            System.out.println(solution(i));
//            if (reference[i] != solution(i) + 1) {
//                System.out.println("failed for n = " + i);
//            }
//        }

        // Testing the value for combinations
        System.out.println(solution(200));
    }
}
