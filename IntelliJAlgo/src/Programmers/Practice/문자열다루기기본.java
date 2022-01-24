package Programmers.Practice;

public class 문자열다루기기본 {

    public static void main(String[] args) {
        System.out.println(solution("a234"));
    }

    public static boolean solution(String s) {
        return isNumber(s) && (s.length()==4|| s.length()==6);
    }

    public static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
