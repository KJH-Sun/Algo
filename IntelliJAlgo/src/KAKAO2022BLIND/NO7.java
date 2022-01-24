package KAKAO2022BLIND;

import java.util.Arrays;

public class NO7 {

    public static void main(String[] args) {

        System.out.println(solution(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, new int[]{1, 0},
            new int[]{1, 2}));
    }

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N, M;

    public static int solution(int[][] board, int[] aloc, int[] bloc) {
        N = board.length;
        M = board[0].length;
        int[] result = simulation(0, aloc, bloc, board);
        return Arrays.stream(result).sum();
    }

    private static int[] simulation(int turn, int[] aloc, int[] bloc, int[][] board) {
        int[] apos = Arrays.copyOf(aloc, aloc.length);
        int[] bpos = Arrays.copyOf(bloc, bloc.length);
        int[][] map = deepcopy(board);
        int[][] result = new int[4][2];
        int canMove = 0;
        if (turn % 2 == 0) {// A턴
            map[aloc[0]][aloc[1]] = 0;
            for (int i = 0; i < 4; i++) {
                int nx = apos[0] + dx[i];
                int ny = apos[1] + dy[i];
                if (isValid(nx, ny) && board[nx][ny] != 0) {
                    canMove++;
                    result[i] = simulation(turn + 1, new int[]{nx, ny}, bpos, map);
                }
            }
            if (canMove == 0) {
                return new int[]{0, 1};
            } else {
                if (aloc[0] == bloc[0] && aloc[1] == bloc[1]) {
                    return new int[]{canMove, 0};
                }
            }
        } else { // B턴
            map[bloc[0]][bloc[1]] = 0;
            for (int i = 0; i < 4; i++) {
                int nx = bpos[0] + dx[i];
                int ny = bpos[1] + dy[i];
                if (isValid(nx, ny) && board[nx][ny] != 0) {
                    canMove++;
                    result[i] = simulation(turn + 1, apos, new int[]{nx, ny}, map);
                }
            }
            if (canMove == 0) {
                return new int[]{1, 0};
            } else {
                if (aloc[0] == bloc[0] && aloc[1] == bloc[1]) {
                    return new int[]{0, canMove};
                }
            }
        }
        int[] res = new int[2];
        for (int i = 0; i < 4; i++) {
            res[0] += result[i][0];
            res[1] += result[i][1];
        }
        return res;
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && 0 <= y && x < N && y < M;
    }

    private static int[][] deepcopy(int[][] board) {
        int[][] tmp = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, tmp[i], 0, board[0].length);
        }
        return tmp;
    }
}
