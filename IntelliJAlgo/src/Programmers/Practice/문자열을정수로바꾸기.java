package Programmers.Practice;

import java.util.stream.Stream;

public class 문자열을정수로바꾸기 {

    public static void main(String[] args) {

    }

    public static int solution(String s) {
        char[] chars = s.toCharArray();
        char c = chars[0];
        int sign = 1;
        boolean haveSign = false;
        if (c == '-') {
            sign = -1;
            haveSign = true;
        } else if (c == '+') {
            haveSign = true;
        }
        return (haveSign ? Integer.parseInt(s.substring(1)) : Integer.parseInt(s)) * sign;
    }
}
