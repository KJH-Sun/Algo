package KAKAO2022BLIND;

import java.util.StringTokenizer;
import java.util.function.Function;

public class K진수에서소수개수구하기 {

    public static void main(String[] args) {
        System.out.println(solution(797494, 3));
    }

    static Function<String, Long> stol = Long::parseLong;

    public static int solution(int n, int k) {
        String binaryString = Integer.toString(n, k);
        StringTokenizer st = new StringTokenizer(binaryString, "0");
        int cnt = 0;
        while (st.hasMoreTokens()) {
            long num = stol.apply(st.nextToken());
            if (isPrime(num)) {
                cnt++;
            }
        }
        return cnt;
    }


    private static boolean isPrime(long n) {
        if (n == 1) {
            return false;
        }
        for (int i = 2; (long) i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
