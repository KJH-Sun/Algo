package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178_미로탐색 {

    static class pos {

        int x;
        int y;

        pos() {
        }

        pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static Queue<pos> que = new LinkedList<>();
    static int[][] field;
    static StringBuffer sb = new StringBuffer();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int max;

    private static void bfs() {
        que.offer(new pos(0, 0));
        while (!que.isEmpty()) {
            pos p = que.poll();
            if (p.x == N - 1 && p.y == M - 1) {
                max = field[p.x][p.y];
            }
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (0 <= nx && 0 <= ny && nx < N && ny < M && field[nx][ny] == 1) {
                    que.offer(new pos(nx, ny));
                    field[nx][ny] = field[p.x][p.y] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        field = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                field[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }
        bfs();
        System.out.println(max);

    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

}
