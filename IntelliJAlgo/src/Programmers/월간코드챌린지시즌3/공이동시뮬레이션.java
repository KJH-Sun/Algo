package Programmers.월간코드챌린지시즌3;


import java.util.ArrayDeque;
import java.util.Queue;

public class 공이동시뮬레이션 {

    public static void main(String[] args) {
        System.out
            .println(
                solution(2, 5, 0, 1, new int[][]{{3, 1}, {2, 2}, {1, 1}, {2, 3}, {0, 1}, {2, 1}}))
        ;
    }

    /*
        0 : 왼
        1 : 오
        2 : 위
        3 : 아래
        실패
     */
    static int[] box;
    static int N, M;

    public static long solution(int n, int m, int x, int y, int[][] queries) {
        N = n - 1; // maxRow
        M = m - 1; // maxCol
        box = new int[]{x, x, y, y};
        Queue<int[]> que = new ArrayDeque<>();

        for (int[] query : queries) {
            int dir = query[0];
            int dx = query[1];
            if (check(dir)) {
                plus(dir, dx);
            } else {
                move(dir, dx);
            }

        }
        return (long) (n == 1 ? 1 : box[1] - box[0] + 1) * (m == 1 ? 1 : box[3] - box[2] + 1);
    }

    private static void move(int dir, int dx) {
        switch (dir) {
            case 0: // col+
                box[2] = Math.min(box[2] + dx, M);
                box[3] = Math.min(box[3] + dx, M);
                break;
            case 1: // col-
                box[2] = Math.max(box[2] - dx, 0);
                box[3] = Math.max(box[3] - dx, 0);
                break;
            case 2: // row+
                box[0] = Math.min(box[0] + dx, N);
                box[1] = Math.min(box[1] + dx, N);
                break;
            case 3: // row-
                box[0] = Math.max(box[0] - dx, 0);
                box[1] = Math.max(box[1] - dx, 0);
                break;
        }
    }

    private static void plus(int dir, int dx) {
        switch (dir) {
            case 0: // col+
                box[3] = Math.min(box[3] + dx, M);
                break;
            case 1: // col-
                box[2] = Math.max(box[2] - dx, 0);
                break;
            case 2: // row+
                box[1] = Math.min(box[1] + dx, N);
                break;
            case 3: // row-
                box[0] = Math.max(box[0] - dx, 0);
                break;
        }
    }


    private static boolean check(int dir) {
        return (dir == 0 && box[2] == 0) || (dir == 1 && box[3] == M) || (dir == 2 && box[0] == 0)
            || (dir == 3 && box[1] == N);
    }
}
