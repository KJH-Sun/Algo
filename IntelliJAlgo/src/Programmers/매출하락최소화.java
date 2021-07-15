package Programmers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class 매출하락최소화 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{14, 17, 15, 18, 19, 14, 13, 16, 28, 17},
            new int[][]{{10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3},
                {10, 2}}));
    }

    static Map<Integer, List<Integer>> team = new HashMap<>();
    static int[][] dp;
    static int N;
    static final int INF = (int) 1e9;

    public static int solution(int[] sales, int[][] links) {
        N = sales.length;
        for (int[] link : links) {
            team.computeIfAbsent(link[0], (e) -> new ArrayList<Integer>());
            team.get(link[0]).add(link[1]);
        }
        dp = new int[sales.length + 1][2]; // 0이 참석한 경우 , 1이 참석하지 않은 경우
        for (int i = 0; i < sales.length + 1; i++) {
            Arrays.fill(dp[i], INF);
        }
        dfs(1, sales);

        return Math.min(dp[1][0], dp[1][1]);
    }

    private static void dfs(int cur, int[] sales) {
        dp[cur][0] = sales[cur - 1];
        dp[cur][1] = 0;
        if (!team.containsKey(cur)) {
            return;
        }
        int extra = INF;
        for (int teamMate : team.get(cur)) {
            dfs(teamMate, sales);
            dp[cur][0] += Math.min(dp[teamMate][0], dp[teamMate][1]);
            dp[cur][1] += Math.min(dp[teamMate][0], dp[teamMate][1]);
            if (dp[teamMate][0] > dp[teamMate][1]) { // 팀원이 선택되지 않는 경우가 더 작은 경우
                extra = Math.min(extra, dp[teamMate][0] - dp[teamMate][1]);
            } else {
                extra = 0;
            }
        }
        dp[cur][1] += extra;
    }

}
