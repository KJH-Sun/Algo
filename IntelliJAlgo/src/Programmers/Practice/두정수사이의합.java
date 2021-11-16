package Programmers.Practice;

public class 두정수사이의합 {

    public static void main(String[] args) {
        System.out.println(solution(3, 5));
    }

    public static long solution(int a, int b) {
        int low = 0;
        int high = 0;
        if (a < b) {
            low = a;
            high = b;
        } else if (a == b) {
            return a;
        } else {
            low = b;
            high = a;
        }
        long start = ((long) low * (low - 1)) / 2;
        long end = ((long) high * (high + 1)) / 2;
        return end - start;
    }
}
