package Programmers.월간코드챌린지9월;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class No2 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"SL", "LR"})));
    }

    static final int LEFT = 0, UP = 1, RIGHT = 2, DOWN = 3;
    static List<Circle> circles = new ArrayList<>();
    static char[][] map;
    static int N, M;
    static boolean[][][] visit;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static List<Integer> ans = new ArrayList<>();


    public static int[] solution(String[] grid) {
        N = grid.length;
        M = grid[0].length();
        map = new char[N][M];
        visit = new boolean[N][M][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = grid[i].charAt(j);
                map[i][j] = c;
                circles.add(new Circle(new Node(i, j), c));
            }
        }
        for (Circle c : circles) {
            for (int i = 0; i < 4; i++) {
                if (!visit[c.pos.x][c.pos.y][i]) {
                    shoot(c, i);
//                    dfs(c, i, 0);
                }
            }
        }
        Collections.sort(ans);
        return ans.stream().mapToInt(i -> i).toArray();
    }

    private static void shoot(Circle c, int d) {
        int dir = d;
        int cnt = 0;
        while (!visit[c.pos.x][c.pos.y][dir]) {
            visit[c.pos.x][c.pos.y][dir] = true;
            cnt++;
            switch (c.grid) {
                case 'L':
                    if (dir == LEFT || dir == DOWN) {
                        dir = dir == LEFT ? DOWN : RIGHT;
                    } else { // RIGHT UP
                        dir = dir == RIGHT ? UP : LEFT;
                    }
                    break;
                case 'R':
                    if (dir == LEFT || dir == DOWN) {
                        dir = dir == LEFT ? UP : LEFT;
                    } else { // RIGHT UP
                        dir = dir == RIGHT ? DOWN : RIGHT;
                    }
                    break;
            }
            int nx = (c.pos.x + dx[dir] + N * N) % N;
            int ny = (c.pos.y + dy[dir] + M * M) % M;
            c = new Circle(new Node(nx, ny), map[nx][ny]);
        }
        ans.add(cnt);
    }

//    private static void dfs(Circle c, int dir, int cnt) {
//        if (visit[c.pos.x][c.pos.y][dir]) {
//            ans.add(cnt);
//            return;
//        }
//        visit[c.pos.x][c.pos.y][dir] = true;
//        switch (c.grid) {
//            case 'L':
//                if (dir == LEFT || dir == DOWN) {
//                    dir = dir == LEFT ? DOWN : RIGHT;
//                } else { // RIGHT UP
//                    dir = dir == RIGHT ? UP : LEFT;
//                }
//                break;
//            case 'R':
//                if (dir == LEFT || dir == DOWN) {
//                    dir = dir == LEFT ? UP : LEFT;
//                } else { // RIGHT UP
//                    dir = dir == RIGHT ? DOWN : RIGHT;
//                }
//                break;
//        }
//        int nx = (c.pos.x + dx[dir] + N * N) % N;
//        int ny = (c.pos.y + dy[dir] + M * M) % M;
//        dfs(new Circle(new Node(nx, ny), map[nx][ny]), dir, cnt + 1);
//    }

    static class Circle {

        Node pos;
        char grid;

        public Circle(Node pos, char c) {
            this.pos = pos;
            this.grid = c;
        }
    }

    static class Node {

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
