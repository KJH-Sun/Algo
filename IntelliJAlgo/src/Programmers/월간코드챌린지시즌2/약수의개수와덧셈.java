package Programmers.월간코드챌린지시즌2;

public class 약수의개수와덧셈 {


    /*
        자연수가 제곱수이면 그 수의 약수는 홀수개
        아니면 짝수개
     */

    public static void main(String[] args) {
        System.out.println(solution(13, 17));
    }

    public static int solution(int left, int right) {
        int answer = 0;
        for (int i = left; i <= right; i++) {
            if (Math.sqrt((double) i) % 1 == 0) {
                answer -= i;
            } else {
                answer += i;
            }
        }

        return answer;
    }

}
