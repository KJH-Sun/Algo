package Programmers.카카오코드;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    하나의 솔루션을 반복실행하는 것 같은데, 그 이유로 computeIfAbsent를 사용하면 틀렸다고 나왔다.
    굉장히 슬픈 문제다
    시간을 얼마나 버린건지...
 */



public class GPS {

    public static void main(String[] args) {
        System.out.println(solution(7, 10,
            new int[][]{{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7},
                {6, 7}}, 6, new int[]{1, 2, 3, 3, 6, 7}));
    }


    static final int INF = (int) 1e9;
    static Map<Integer, List<Integer>> ways = new HashMap<>();

    public static int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int[][] dp = new int[k][n + 1];

        for (int i = 1; i < n + 1; i++) {
            ways.put(i, new ArrayList<>());
        }

        for (int[] edge : edge_list) {
            int start = edge[0];
            int end = edge[1];
//            ways.computeIfAbsent(start, (e) -> new ArrayList<>());
//            ways.computeIfAbsent(end, (e) -> new ArrayList<>());
            ways.get(start).add(end);
            ways.get(end).add(start);

        }

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][gps_log[0]] = 0;

        for (int i = 1; i < k; i++) { // i번째 경로, 0번째 경로에서는 문제가 발생하지 않으므로 1번부터 시작
            for (int j = 1; j < n + 1; j++) {

                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);

                for (int node : ways.get(j)) {
                    dp[i][j] = Math.min(dp[i - 1][node], dp[i][j]);
                }

                dp[i][j] += gps_log[i] == j ? 0 : 1;
            }
        }

        return dp[k - 1][gps_log[k - 1]] >= INF ? -1 : dp[k - 1][gps_log[k - 1]];
    }

}
