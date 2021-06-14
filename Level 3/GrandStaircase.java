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
        System.out.println(solution(200));
    }
}
