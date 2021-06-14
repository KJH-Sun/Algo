package BOJ.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

/*
 풀이 성공
 BFS 처리 시에 현재 que에 넣으면서 Visit처리를 해줘야 통과할 수 있었다.
 */

public class BOJ_21922_학부연구생민상 {

    static int N, M;
    static int[][] map;
    static Function<String, Integer> stoi = Integer::parseInt;
    static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][][] visit;
    static List<Node> AC = new ArrayList<>();
    static Queue<Wind> que = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi.apply(st.nextToken());
        M = stoi.apply(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stoi.apply(st.nextToken());
                if (map[i][j] == 9) {
                    for (int k = 0; k < 4; k++) {
                        que.offer(new Wind(new Node(i, j), k));
                        visit[i][j][k] = true;
                    }
                }
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {

        while (!que.isEmpty()) {
            Wind w = que.poll();
            int nx = w.pos.x + dx[w.dir];
            int ny = w.pos.y + dy[w.dir];
            if (isValid(nx, ny) && !visit[nx][ny][w.dir]) {
                visit[nx][ny][w.dir] = true;
                switch (map[nx][ny]) {
                    case 0:
                        que.offer(new Wind(new Node(nx, ny), w.dir));
                        break;
                    case 1:
                        que.add(
                            new Wind(new Node(nx, ny), w.dir % 2 == 0 ? w.dir : ((w.dir + 2) % 4)));
                        break;
                    case 2:
                        que.add(
                            new Wind(new Node(nx, ny), w.dir % 2 == 1 ? w.dir : ((w.dir + 2) % 4)));
                        break;
                    case 3:
                        que.add(new Wind(new Node(nx, ny), convert(w.dir, true)));
                        break;
                    case 4:
                        que.add(new Wind(new Node(nx, ny), convert(w.dir, false)));
                        break;

                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isVisit(i, j)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static boolean isVisit(int x, int y) {
        for (int dir = 0; dir < 4; dir++) {
            if (visit[x][y][dir]) {
                return true;
            }
        }
        return false;

    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && 0 <= y && x < N && y < M;
    }

    private static int convert(int now, boolean flag) {
        switch (now) {
            case UP:
                return flag ? RIGHT : LEFT;
            case RIGHT:
                return flag ? UP : DOWN;
            case DOWN:
                return flag ? LEFT : RIGHT;
            case LEFT:
                return flag ? DOWN : UP;
        }
        return -1;
    }


    static class Wind {

        Node pos;
        int dir;

        public Wind(Node pos, int dir) {
            this.pos = pos;
            this.dir = dir;
        }
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
