package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.function.Function;

public class BOJ_1949_우수마을 {

    static int[][] dp;
    static int[] popul;
    static int N;
    static ArrayList<Integer>[] roads;
    static Function<String, Integer> stoi = Integer::parseInt;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi.apply(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        popul = new int[N + 1];
        dp = new int[N + 1][2]; // 0 -> 1번 노드 선택 O 1 -> 1번 노드 선택 X
        roads = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            popul[i] = stoi.apply(st.nextToken());
            roads[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = stoi.apply(st.nextToken());
            int e = stoi.apply(st.nextToken());
            roads[s].add(e);
            roads[e].add(s);
        }
        dfs(1, 0);
        System.out.println(Math.max(dp[1][0], dp[1][1]));

    }

    private static void dfs(int now, int parent) {
        for (int road : roads[now]) {
            if (road != parent) {
                dfs(road, now);
                dp[now][0] += Math.max(dp[road][0], dp[road][1]);
                dp[now][1] += dp[road][0];
            }
        }
        dp[now][1] += popul[now];
    }


}
