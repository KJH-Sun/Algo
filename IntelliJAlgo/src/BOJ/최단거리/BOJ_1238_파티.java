package BOJ.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.function.Function;

public class BOJ_1238_파티 {

    static int N, M, X;
    static Function<String, Integer> stoi = Integer::parseInt;
    static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi.apply(st.nextToken());
        M = stoi.apply(st.nextToken());
        X = stoi.apply(st.nextToken());
        List<Town>[] roads = new ArrayList[N + 1];
        List<Town>[] reverse_roads = new ArrayList[N + 1];
        int[] dp = new int[N + 1];
        int[] reverse_dp = new int[N + 1];
        Arrays.fill(dp, INF);
        Arrays.fill(reverse_dp, INF);
        dp[X] = 0;
        reverse_dp[X] = 0;
        for (int i = 1; i <= N; i++) {
            roads[i] = new ArrayList<Town>();
            reverse_roads[i] = new ArrayList<Town>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = stoi.apply(st.nextToken());
            int e = stoi.apply(st.nextToken());
            int c = stoi.apply(st.nextToken());
            roads[s].add(new Town(e, c));
            reverse_roads[e].add(new Town(s, c));
        }
        dijk(roads, dp);
        dijk(reverse_roads, reverse_dp);
        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dp[i] + reverse_dp[i]);
        }
        System.out.println(max);

    }

    private static int[] dijk(List<Town>[] roads, int[] dp) {
        PriorityQueue<Town> pQ = new PriorityQueue<>();
        pQ.add(new Town(X, 0));
        boolean[] visit = new boolean[N + 1];
        while (!pQ.isEmpty()) {
            Town t = pQ.poll();
            int cur = t.e;
            if (!visit[cur]) {
                visit[cur] = true;
                for (Town town : roads[cur]) {
                    if (!visit[town.e] && dp[cur] + town.cost < dp[town.e]) {
                        dp[town.e] = dp[cur] + town.cost;
                        pQ.add(new Town(town.e, dp[town.e]));
                    }
                }
            }
        }
        return dp;
    }


    static class Town implements Comparable<Town> {

        int e;
        int cost;

        public Town(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }

        @Override
        public int compareTo(Town o) {
            return this.cost - o.cost;
        }
    }
}
