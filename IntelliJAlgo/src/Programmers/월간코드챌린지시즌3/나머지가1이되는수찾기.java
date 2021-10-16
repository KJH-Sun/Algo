package Programmers.월간코드챌린지시즌3;

public class 나머지가1이되는수찾기 {

    public int solution(int n) {
        for (int i = 1; i < n; i++) {
            if (n % i == 1) {
                return i;
            }
        }
        return n - 1;
    }
}
