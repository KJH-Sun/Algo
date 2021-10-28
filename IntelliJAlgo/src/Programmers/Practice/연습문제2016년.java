package Programmers.Practice;

public class 연습문제2016년 {

    public static void main(String[] args) {
        System.out.println(solution(1, 6));
    }

    /*
        윤년은 2월이 29일
     */
    static int[] months = new int[]{0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static String solution(int a, int b) {
        int days = 0;
        for (int i = 1; i < a; i++) {
            days += months[i];
        }
        days += b;
        String answer = "";
        switch (days % 7) {
            case 0:
                answer = "THU";
                break;
            case 1:
                answer = "FRI";
                break;
            case 2:
                answer = "SAT";
                break;
            case 3:
                answer = "SUN";
                break;
            case 4:
                answer = "MON";
                break;
            case 5:
                answer = "TUE";
                break;
            case 6:
                answer = "WED";
                break;
        }
        return answer;
    }
}
