package Programmers.Practice;

import java.util.Arrays;

public class 서울에서김서방찾기 {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"Jane", "Kim"}));
    }

    public static String solution(String[] seoul) {
        final String PREFIX = "김서방은 ";
        final String SUBFIX = "에 있다.";
        final String name = "Kim";
        int num = Arrays.asList(seoul).indexOf(name);

        return PREFIX+num+SUBFIX;
    }
}
