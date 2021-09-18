package 삼성모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.Function;

public class Samsung2 {

    static Function<String, Integer> stoi = Integer::parseInt;
    static int N;
    static int[] subways;
    static long result;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi.apply(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            N = stoi.apply(br.readLine());
            subways = new int[N];
            result = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                subways[i] = stoi.apply(st.nextToken());
            }
            for (int i = 0; i < N; i++) {
                findMaxRoute(i);
            }
            System.out.println("#" + tc + " " + result);
        }
    }

    private static void findMaxRoute(int start) {
        boolean[] canBuild = new boolean[N];
        Arrays.fill(canBuild, true);
        canBuild[(start - 1 + N * N) % N] = false;
        canBuild[start] = false;
        canBuild[(start + 1) % N] = false;
        long firstMax = Long.MIN_VALUE;
        long secondMax = 0;
        int end = (start + 2) % N;
        while (canBuild[end]) {
            firstMax = (long) Math.pow(subways[start] + subways[end], 2);
            secondMax = findInnerMaxRoute(start, end);
            result = Math.max(firstMax + secondMax, result);
            end = (end + 1) % N;
        }
    }

    private static long findInnerMaxRoute(int start, int end) {
        boolean[] canBuild = new boolean[N];
        Arrays.fill(canBuild, true);
        canBuild[(start - 1 + N * N) % N] = false;
        canBuild[start] = false;
        canBuild[(start + 1) % N] = false;
        canBuild[(end - 1 + N * N) % N] = false;
        canBuild[end] = false;
        canBuild[(end + 1) % N] = false;
        long firstMax = Long.MIN_VALUE;
        int s = (end + 2) % N;
        int e = (s + 2) % N;
        while (true) {
            if (!canBuild[e]) {
                s = (s + 1) % N;
                e = (s + 2) % N;
                continue;
            }
            if (!canBuild[s]) {
                break;
            }
            firstMax = Math.max(firstMax, (long) Math.pow(subways[s] + subways[e], 2));
            e = (e + 1) % N;
        }
        return firstMax;
    }
}
