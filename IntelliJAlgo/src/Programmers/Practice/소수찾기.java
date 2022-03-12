package Programmers.Practice;

public class 소수찾기 {

    public static void main(String[] args) {

    }

    static boolean[] prime;

    public static int solution(int n) {
        prime = new boolean[n];
        return getPrime(n);
    }

    public static int getPrime(int N) {
        prime[0] = prime[1] = true;

        for (int i = 2; i * i <= N; i++) {
            if (!prime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    prime[j] = true;
                }
            }
        }
        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (!prime[i]) {
                result++;
            }
        }
        return result;
    }
}
