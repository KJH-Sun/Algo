package 삼성모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Function;

public class Samsung1 {

    static Function<String, Integer> stoi = Integer::parseInt;
    static int N;
    static int[][] map;
    static boolean[][] canCatch;
    static Node start;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi.apply(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = stoi.apply(br.readLine());
            map = new int[N][N];
            canCatch = new boolean[N][N];
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int num = stoi.apply(st.nextToken());
                    map[i][j] = num;
                    if (num == 2) {
                        start = new Node(i, j, 0);
                        map[i][j] = 0;
                    }
                }
            }
            simulate();
            System.out.println("#" + tc + " " + countCatch());
        }
    }

    private static void simulate() {
        for (int i = 0; i < 4; i++) {
            move(start, i);
        }
    }

    private static void move(Node now, int dir) {
        if (now.cnt == 3) {
            return;
        }
        int nx = now.x + dx[dir];
        int ny = now.y + dy[dir];
        boolean first = true;
        while (isValid(nx, ny)) {
            if (map[nx][ny] == 1 && first) {
                first = false;
                nx += dx[dir];
                ny += dy[dir];
            } else if (map[nx][ny] != 1 && first) {
                nx += dx[dir];
                ny += dy[dir];

            } else if (!first && map[nx][ny] != 1) {
                for (int i = 0; i < 4; i++) {
                    move(new Node(nx, ny, now.cnt + 1), i);
                }
                nx += dx[dir];
                ny += dy[dir];
            } else if (!first && map[nx][ny] == 1) {
                canCatch[nx][ny] = true;
                map[nx][ny] = 0;
                for (int i = 0; i < 4; i++) {
                    move(new Node(nx, ny, now.cnt + 1), i);
                }
                map[nx][ny] = 1;
                break;
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && 0 <= y && x < N && y < N;
    }

    private static int countCatch() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (canCatch[i][j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static class Node {

        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
