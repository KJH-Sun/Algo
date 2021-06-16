package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;

public class BOJ_18126_너구리구구 {

    static int N;
    static Function<String, Integer> stoi = Integer::parseInt;
    static int[][] map;
    static long max = Long.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi.apply(br.readLine());
        map = new int[N + 1][N + 1];
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = stoi.apply(st.nextToken());
            int e = stoi.apply(st.nextToken());
            int d = stoi.apply(st.nextToken());
            map[s][e] = d;
            map[e][s] = d;
        }
        boolean[] visit = new boolean[N + 1];
        dfs(1, 0, visit);
        System.out.println(max);
    }

    private static void dfs(int now, long dist, boolean[] visit) {
        visit[now] = true;
        for (int i = 1; i <= N; i++) {
            if (map[now][i] != 0 && !visit[i]) {
                dfs(i, dist + map[now][i], visit);
            }
        }
        max = Math.max(dist, max);
    }

}
