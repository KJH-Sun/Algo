package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

public class BOJ_2606_바이러스 {

    static Function<String, Integer> stoi = Integer::parseInt;
    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visit;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi.apply(br.readLine());
        M = stoi.apply(br.readLine());
        visit = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Integer>());
        }
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = stoi.apply(st.nextToken());
            int e = stoi.apply(st.nextToken());
            graph.get(s).add(e);
            graph.get(e).add(s);
        }
        dfs(1);

        System.out.println(cnt - 1);
    }


    // 1-> 2 -> 3 -> 5 -> 6
    private static void dfs(int now) {
        List<Integer> nowSearch = graph.get(now);
        for (int node : nowSearch) {
            if (!visit[node]) {
                visit[node] = true;
                cnt++;
                dfs(node);
            }
        }
    }

    // 1-> 2 -> 5 -> 3 -> 6
    private static void bfs() {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(1);
        visit[1] = true;
        while (!que.isEmpty()) {
            int n = que.poll();
            for (int node : graph.get(n)) {
                if (!visit[node]) {
                    visit[node] = true;
                    cnt++;
                    que.add(node);
                }
            }
        }
    }
}
