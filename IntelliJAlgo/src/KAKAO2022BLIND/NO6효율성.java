package KAKAO2022BLIND;

public class NO6효율성 {

    public static void main(String[] args) {
        System.out.println(solution(
            new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
            new int[][]{{1, 1, 1, 2, 2, 4}, {1, 0, 0, 1, 1, 2}, {2, 2, 0, 2, 0, 100}}));
    }

    static int[][] map, dp;

    public static int solution(int[][] board, int[][] skill) {
        map = board;
        dp = new int[board.length][board[0].length];
        for (int[] use : skill) {
            int type = use[0];
            int r1 = use[1];
            int c1 = use[2];
            int r2 = use[3];
            int c2 = use[4];
            int degree = use[5];
            if (type == 1) {
                dp[r2][c2] -= degree * (r2 + 1) * (c2 + 1);
                if (r1 > 0) {
                    dp[r1 - 1][c2] += degree * (r1) * (c2 + 1);
                }
                if (c1 > 0) {
                    dp[r2][c1 - 1] += degree * (r2 + 1) * (c1);
                }
                if (r1 > 0 && c1 > 0) {
                    dp[r1 - 1][c1 - 1] -= degree * r1 * c1;
                }
            } else {
                dp[r2][c2] += degree * (r2 + 1) * (c2 + 1);
                if (r1 > 0) {
                    dp[r1 - 1][c2] -= degree * (r1) * (c2 + 1);
                }
                if (c1 > 0) {
                    dp[r2][c1 - 1] -= degree * (r2 + 1) * (c1);
                }
                if (r1 > 0 && c1 > 0) {
                    dp[r1 - 1][c1 - 1] += degree * r1 * c1;
                }
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                addDp(i, j);
            }
        }

        return checkMap();
    }

    private static int checkMap() {
        int cnt = 0;
        for (int[] ints : map) {
            for (int j = 0; j < map[0].length; j++) {
                if (ints[j] > 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void addDp(int x, int y) {
        int div = dp[x][y] / ((x + 1) * (y + 1));
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                map[i][j] += div;
            }
        }
    }

}
