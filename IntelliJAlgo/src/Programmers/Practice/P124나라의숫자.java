package Programmers.Practice;

public class P124나라의숫자 {

    public static void main(String[] args) {
        System.out.println(solution(4));
    }
    public static String solution(int n) {
        String[] numbers = {"4", "1", "2"};
        StringBuilder answer = new StringBuilder();

        int num = n;
        while(num > 0){
            int remainder = num % 3;
            num /= 3;
            if(remainder == 0){
                num--;
            }
            answer.insert(0, numbers[remainder]);
        }

        return answer.toString();
    }
}
