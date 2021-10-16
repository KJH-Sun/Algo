package DevMatch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class DevMatchNo3 {

    public static void main(String[] args) {
        String[] result = solution(new int[][]
            {{1, 1}, {2, 1}, {1, 2}, {3, 3}, {6, 4}, {3, 1}, {3, 3}, {3, 3}, {3, 4}, {2, 1}}
        );
        for (String res : result) {
            System.out.println(res);
        }
    }

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;


    public static String[] solution(int[][] macaron) {
        map = new int[6][6];
        for (int[] fall : macaron) {
            int x = 5;
            int y = fall[0] - 1;
            int color = fall[1];
            while (true) {
                if (map[x][y] == 0) {
                    map[x][y] = color;
                    break;
                }
                x--;
            }
            while (findGroup()) {
                downMacaron();
            }
        }
        String[] answer = new String[6];
        for (int i = 0; i < 6; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                sb.append(map[i][j]);
            }
            answer[i] = sb.toString();
        }
        return answer;
    }

    private static boolean findGroup() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (map[i][j] != 0) {
                    if (bfs(i, j, map[i][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean bfs(int x, int y, int color) {
        Queue<Node> que = new ArrayDeque<>();
        que.add(new Node(x, y));
        List<Node> group = new ArrayList<>();
        boolean[][] visit = new boolean[6][6];
        group.add(new Node(x, y));
        visit[x][y] = true;

        while (!que.isEmpty()) {
            Node n = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if (isValid(nx, ny) && !visit[nx][ny] && map[nx][ny] == color) {
                    Node nn = new Node(nx, ny);
                    que.add(nn);
                    visit[nx][ny] = true;
                    group.add(nn);
                }
            }
        }

        if (group.size() >= 3) {
            for (Node n : group) {
                map[n.x][n.y] = 0;
            }
            return true;
        }
        return false;
    }

    private static void downMacaron() {
        for (int i = 0; i < 6; i++) {
            int x = 5;
            Queue<Integer> que = new ArrayDeque<>();
            while (x >= 0) {
                if (map[x][i] != 0) {
                    que.add(map[x][i]);
                }
                x--;
            }
            x = 5;
            while (!que.isEmpty()) {
                map[x--][i] = que.poll();
            }
            while (x >= 0) {
                map[x--][i] = 0;
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && 0 <= y && x < 6 && y < 6;
    }

    static class Node {

        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
