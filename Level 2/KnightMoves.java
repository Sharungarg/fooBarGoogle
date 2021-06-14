import java.util.Arrays;
import java.util.PriorityQueue;

public class KnightMoves {
    private static class TreeNode implements Comparable<TreeNode> {
        int moves;
        int distanceFromDestination;
        int boxNum;

        public TreeNode(TreeNode parent, int dest, int boxNum) {
            this.boxNum = boxNum;
            this.moves = parent != null ? parent.moves + 1 : 0;
            this.distanceFromDestination = this.calculateDistanceBetween(this.boxNum, dest);
        }

        private int calculateDistanceBetween(int src, int dest) {
            int srcX = src / 8;
            int srcY = src - (srcX * 8);
            int destX = dest / 8;
            int destY = dest - (destX * 8);
            return Math.abs(srcX - destX) + Math.abs(srcY - destY);
        }

        public int compareTo(TreeNode that) {
            return Integer.compare((this.distanceFromDestination + this.moves), (that.distanceFromDestination + that.moves));
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (this.getClass() != obj.getClass()) return false;
            TreeNode that = (TreeNode) obj;

            return this.compareTo(that) == 0;
        }

        @Override
        public int hashCode() {
            assert false : "hashcode not designed";
            return 0;
        }
    }

    public static int solution(int src, int dest) {
        if (src == dest) return 0;
        int[] chessBoard = new int[64];
        chessBoard[src] = 1;
        PriorityQueue<TreeNode> minQ = new PriorityQueue<>();
        minQ.add(new TreeNode(null, dest, src));
        while (!minQ.isEmpty()) {
            TreeNode dequeueNode = minQ.poll();
            if (dequeueNode.boxNum == dest) {
                return dequeueNode.moves;
            }
            for (int i : getNeighbours(dequeueNode.boxNum)) {
                if (chessBoard[i] != 1) {
                    minQ.add(new TreeNode(dequeueNode, dest, i));
                    chessBoard[i] = 1;
                }
            }
        }
        return 0;
    }

    private static int[] getNeighbours(int base) {
        int[] neighbours = new int[8];
        int index = 0;
        int baseX = base / 8;
        int baseY = base - (baseX * 8);
        for (int x = 1; x <= 2; x++) {
            int y = 3 - x;
            if (isValidBox(baseX + x, baseY + y)) neighbours[index++] = ((baseX + x) * 8) + baseY + y;
            if (isValidBox(baseX + x, baseY - y)) neighbours[index++] = ((baseX + x) * 8) + baseY - y;
            if (isValidBox(baseX - x, baseY + y)) neighbours[index++] = ((baseX - x) * 8) + baseY + y;
            if (isValidBox(baseX - x, baseY - y)) neighbours[index++] = ((baseX - x) * 8) + baseY - y;
        }
        return Arrays.copyOfRange(neighbours, 0, index);
    }

    private static boolean isValidBox(int x, int y) {
        if (x < 0 || y < 0) return false;
        return x <= 7 && y <= 7;
    }

    public static void main(String[] args) {
        System.out.println(solution(1, 60));
    }
}
