package Programmers.Practice;

public class 가운데글자가져오기 {

    public static void main(String[] args) {
        System.out.println(solution("abcde"));
    }

    public static String solution(String s) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        if (len % 2 == 0) { // 짝수
            sb.append(s.charAt(len / 2 - 1)).append(s.charAt(len / 2));
        } else {
            sb.append(s.charAt(len / 2));
        }
        return sb.toString();
    }
}
