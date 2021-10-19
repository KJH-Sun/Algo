package Programmers.월간코드챌린지시즌1;

public class 삼진법뒤집기 {

    public static void main(String[] args) {
        System.out.println(solution(45));
    }

    public static int solution(int n) {
        String s = Integer.toString(n, 3);
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return Integer.parseInt(sb.toString(),3);
    }
}
