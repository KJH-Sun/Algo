package Programmers.월간코드챌린지시즌3;

/*
    이분탐색, 파라메트릭 서치
 */


public class 금과은운반하기 {

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long start = 0;
        long end = (long) 1e9 * 2 * (long) 1e5 * 2;
        long answer = (long) 1e9 * 2 * (long) 1e5 * 2;

        while (start <= end) {
            long mid = (end + start) / 2;
            long g_carry = 0;
            long s_carry = 0;
            long total = 0;

            for (int i = 0; i < g.length; i++) {
                long nG = g[i];
                long nS = s[i];
                long nW = w[i];
                long nT = t[i];
                long move = (mid / nT) / 2 + (mid / nT) % 2;
                g_carry += Math.min(nG, move * nW);
                s_carry += Math.min(nS, move * nW);
                total += Math.min(nG + nS, move * nW);
            }
            if (g_carry >= a && s_carry >= b && total >= (a + b)) {
                end = mid - 1;
                answer = Math.min(mid, answer);
            } else {
                start = mid + 1;
            }
        }

        return answer;
    }
}
