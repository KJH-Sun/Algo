package BOJ.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

public class BOJ_17244_아맞다우산 {


    static int N, M;
    static Function<String, Integer> stoi = Integer::parseInt;
    static char[][] map;
    static Map<Integer, Node> xPos = new HashMap<>();
    static List<int[]> nums = new ArrayList<>();
    static Node start;
    static Node door;
    static int answer = Integer.MAX_VALUE;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = stoi.apply(st.nextToken());
        N = stoi.apply(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        checkMap();
        perm(new ArrayList<Integer>(), 0);
        for (int[] num : nums) {
            answer = Math.min(answer, simulation(num));
        }
        System.out.println(answer);
    }

    private static int simulation(int[] orders) {
        Node now = new Node(start.x, start.y);
        int ans = 0;
        for (int order : orders) {
            Node dest = xPos.get(order);
            ans += bfs(now, dest);
            now = new Node(dest.x, dest.y);
        }
        ans += bfs(now, door);
        return ans;
    }

    private static int bfs(Node now, Node dest) {
        Queue<Move> que = new ArrayDeque<>();
        que.add(new Move(now, 0));
        boolean[][] visit = new boolean[N][M];
        visit[now.x][now.y] = true;

        while (!que.isEmpty()) {
            Move n = que.poll();
            if (n.pos.x == dest.x && n.pos.y == dest.y) {
                return n.cnt;
            }
            for (int i = 0; i < 4; i++) {
                int nx = n.pos.x + dx[i];
                int ny = n.pos.y + dy[i];
                if (isValid(nx, ny) && map[nx][ny] != '#' && !visit[nx][ny]) {
                    que.offer(new Move(new Node(nx, ny), n.cnt + 1));
                    visit[nx][ny] = true;
                }
            }

        }

        return 0;
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && 0 <= y && x < N && y < M;
    }

    private static void perm(ArrayList<Integer> res, int flag) {
        if (res.size() == xPos.size()) {
            nums.add(res.stream().mapToInt(i -> i).toArray());
            return;
        }
        for (int i = 1; i <= xPos.size(); i++) {
            if ((flag & 1 << i) == 0) {
                res.add(i);
                perm(res, flag + (1 << i));
                res.remove((Integer) i);
            }
        }
    }

    private static void checkMap() {
        int idx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'X') {
                    xPos.put(idx++, new Node(i, j));
                }
                if (map[i][j] == 'S') {
                    start = new Node(i, j);
                }
                if (map[i][j] == 'E') {
                    door = new Node(i, j);
                }
            }
        }
    }

    static class Move {

        Node pos;
        int cnt;

        public Move(Node pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
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
