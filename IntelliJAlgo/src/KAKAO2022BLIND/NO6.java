package KAKAO2022BLIND;

public class NO6 {

    public static void main(String[] args) {
        System.out.println(solution(
            new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}},
            new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2},
                {1, 0, 1, 3, 3, 1}}));
    }

    static int[][] map;

    public static int solution(int[][] board, int[][] skill) {
        map = board;
        for (int[] use : skill) {
            int type = use[0];
            int r1 = use[1];
            int c1 = use[2];
            int r2 = use[3];
            int c2 = use[4];
            int degree = use[5];
            if (type == 1) {
                attack(r1, c1, r2, c2, degree);
            } else {
                heal(r1, c1, r2, c2, degree);
            }
        }

        return checkSurvive();
    }

    private static int checkSurvive() {
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

    private static void heal(int r1, int c1, int r2, int c2, int degree) {
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                map[i][j] += degree;
            }
        }
    }

    private static void attack(int r1, int c1, int r2, int c2, int degree) {
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                map[i][j] -= degree;
            }
        }
    }

}
