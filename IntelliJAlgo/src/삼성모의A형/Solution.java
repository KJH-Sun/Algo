package 삼성모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.Function;

public class Solution {

    static Function<String, Integer> stoi = Integer::parseInt;
    static int N;
    static int[] subways;
    static long answer = 0;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi.apply(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = stoi.apply(br.readLine());
            long answer = 0;
            subways = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                subways[i] = stoi.apply(st.nextToken());
            }
            for (int i = 0; i < N; i++) {
                findMaxRoute(i);
            }
            System.out.println(answer);
        }
    }

    private static void findMaxRoute(int start) {
        boolean[] canBuild = new boolean[N];
        Arrays.fill(canBuild, true);
        canBuild[start - 1] = false;
        canBuild[start] = false;
        canBuild[start + 1] = false;
        long firstMax = Long.MIN_VALUE;
        long secondMax = 0;
        int end = start + 2;
        while (canBuild[end]) {
            if (canBuild[end]) {
                if (firstMax < Math.pow(subways[start] + subways[end], 2)) {
                    firstMax = (long) Math.pow(subways[start] + subways[end], 2);
                    secondMax = findInnerMaxRoute(start, end);
                }
            } else {
                break;
            }
            end = (end + 1) % N;
        }
        answer = Math.max(firstMax + secondMax, answer);
    }

    private static long findInnerMaxRoute(int start, int end) {
        boolean[] canBuild = new boolean[N];
        Arrays.fill(canBuild, true);
        canBuild[start - 1] = false;
        canBuild[start] = false;
        canBuild[start + 1] = false;
        canBuild[end - 1] = false;
        canBuild[end] = false;
        canBuild[end + 1] = false;
        long firstMax = Long.MIN_VALUE;
        int s = (end + 2) % N;
        int e = (s + 2) % N;
        while (true) {
            if (!canBuild[e]) {
                s++;
                e = (s + 2) % N;
            }
            if (!canBuild[s]) {
                break;
            }
            firstMax = Math.max(firstMax, (long) Math.pow(subways[s] + subways[e], 2));
            e++;
        }
        return firstMax;
    }


}
