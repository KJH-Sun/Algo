package Programmers.Practice;

public class 시저암호 {

    public static void main(String[] args) {
        System.out.println(solution("z", 1));
    }

    public static String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int pushNum = s.charAt(i) + n;
            if (Character.isUpperCase(s.charAt(i))) {
                sb.append(pushNum / 90 > 0 ? Character.toString(pushNum % 90 + 65)
                    : Character.toString(pushNum % 90));
            } else if (Character.isLowerCase(s.charAt(i))) {
                sb.append(pushNum / 122 > 0 ? Character.toString(pushNum % 122 + 96)
                    : Character.toString(pushNum % 122));
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
