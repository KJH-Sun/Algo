package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;
import org.w3c.dom.Node;

public class BOJ_5014_스타트링크 {

    static Function<String, Integer> stoi = Integer::parseInt;
    static int F, S, G, U, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = stoi.apply(st.nextToken());
        S = stoi.apply(st.nextToken());
        G = stoi.apply(st.nextToken());
        U = stoi.apply(st.nextToken());
        D = stoi.apply(st.nextToken());
        int ans = bfs();
        System.out.println(ans == -1 ? "use the stairs" : ans);
    }

    private static int bfs() {
        boolean[] visit = new boolean[F + 1];
        visit[S] = true;
        Queue<Node> que = new ArrayDeque<>();
        que.add(new Node(S, 0));

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.floor == G) {
                return now.cnt;
            }
            if (now.floor + U <= F && !visit[now.floor + U]) {
                visit[now.floor + U] = true;
                que.add(new Node(now.floor + U, now.cnt + 1));
            }
            if (now.floor - D > 0 && !visit[now.floor - D]) {
                visit[now.floor - D] = true;
                que.add(new Node(now.floor - D, now.cnt + 1));
            }
        }
        return -1;
    }

    static class Node {

        int floor;
        int cnt;

        Node(int floor, int cnt) {
            this.floor = floor;
            this.cnt = cnt;
        }
    }
}
