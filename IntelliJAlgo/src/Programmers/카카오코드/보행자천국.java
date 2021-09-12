package Programmers.카카오코드;

import java.util.Arrays;

public class 보행자천국 {

    public static void main(String[] args) {
        System.out.println(solution(3, 3, new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }

    static final int MOD = 20170805;
    static int[][][] dp;
    static int N, M;
    static int[] dx = {0, 1,};
    static int[] dy = {1, 0};

    public static int solution(int m, int n, int[][] cityMap) {
        N = m;
        M = n;
        dp = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        dp[N - 1][M - 1][0] = 1;
        dp[N - 1][M - 1][1] = 1;
        return solve(0, 0, 0, cityMap);
    }

    private static int solve(int x, int y, int dir, int[][] cityMap) {
        if (dp[x][y][dir] != -1) {
            return dp[x][y][dir];
        }

        int sum = 0;
        int status = cityMap[x][y];
        switch (status) {
            case 0:
                for (int i = 0; i < 2; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (isValid(nx, ny) && cityMap[nx][ny] != 1) {
                        sum += solve(nx, ny, i, cityMap);
                    }
                }
                break;
            case 2:
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (isValid(nx, ny) && cityMap[nx][ny] != 1) {
                    sum += solve(nx, ny, dir, cityMap);
                }
                break;
        }
        return dp[x][y][dir] = (sum % MOD);
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && 0 <= y && x < N && y < M;
    }
}
