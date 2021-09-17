package Programmers.DevMatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 행렬테두리회전하기 {

    public static void main(String[] args) {
        System.out.println(
            Arrays.toString(solution(6, 6, new int[][]{{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}})));
    }

    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];

        initMap(rows, columns);
        List<Integer> ans = new ArrayList<>();
        for (int[] query : queries) {
            ans.add(turnMap(query));
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

    private static int turnMap(int[] query) {
        int min = Integer.MAX_VALUE;
        int r1 = query[0] - 1;
        int c1 = query[1] - 1;
        int r2 = query[2] - 1;
        int c2 = query[3] - 1;
        int prev = map[r1][c1];
        int dir = 0;
        int nx = r1;
        int ny = c1;
        while (true) {
            min = Math.min(prev, min);
            switch (dir) {
                case 0:
                    if (nx == r1 && ny == c2) {
                        dir++;
                    }
                    break;
                case 1:
                    if (nx == r2 && ny == c2) {
                        dir++;
                    }
                    break;
                case 2:
                    if (nx == r2 && ny == c1) {
                        dir++;
                    }
                    break;
                case 3:
                    if (nx == r1 && ny == c1) {
                        dir++;
                    }
                    break;

            }
            if (dir == 4) {
                break;
            }
            nx = nx + dx[dir];
            ny = ny + dy[dir];
            int now = map[nx][ny];
            map[nx][ny] = prev;
            prev = now;

        }
        return min;
    }

    private static void initMap(int rows, int columns) {
        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = num++;
            }
        }
    }


}
