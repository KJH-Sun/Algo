package Programmers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class 경주로건설 {

    public static void main(String[] args) {
        System.out
            .println(solution(new int[][]{{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}}));
    }

    static final int U = 0, R = 1, D = 2, L = 3;
    static int[][] direc = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //URDL
    static int[][] dp;
    static int N;
    static int[][] map;

    public static int solution(int[][] board) {
        map = board;
        N = board.length;
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        bfs();
        return dp[N - 1][N - 1];
    }

    private static void bfs() {
        Queue<Car> que = new ArrayDeque<>();
        que.add(new Car(0, 0, R, 0));
        que.add(new Car(0, 0, D, 0));

        while (!que.isEmpty()) {
            Car n = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = n.x + direc[i][0];
                int ny = n.y + direc[i][1];
                int buildCost = 100;
                if (n.dir != i) {
                    buildCost += 500;
                }
                if (isValid(nx, ny) && dp[nx][ny] >= n.cost + buildCost) {
                    dp[nx][ny] = n.cost + buildCost;
                    que.add(new Car(nx, ny, i, n.cost + buildCost));
                }
            }
        }
    }

    private static boolean isValid(int nx, int ny) {
        return 0 <= nx && 0 <= ny && nx < N && ny < N && map[nx][ny] != 1;
    }

    static class Car {

        int x, y;
        int dir;
        int cost;

        public Car(int x, int y, int dir, int cost) {
            this.dir = dir;
            this.cost = cost;
            this.x = x;
            this.y = y;
        }
    }

}
