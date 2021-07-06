package Programmers.위클리챌린지;

import java.util.HashMap;
import java.util.Map;

public class 위클리챌린지_5주차 {

    public static void main(String[] args) {
        System.out.println(solution("AAAAE"));
    }

    static Map<String, Integer> result = new HashMap<>();
    static char[] alpa = {'A', 'E', 'I', 'O', 'U'};
    static int idx = 1;

    public static int solution(String word) {
        perm(new StringBuilder());
        return result.get(word);
    }

    public static void perm(StringBuilder sb) {
        if (sb.length() >= 1) {
            result.put(sb.toString(), idx++);
        }
        if (sb.length() == 5) {
            return;
        }
        for (int i = 0; i < 5; i++) {
            sb.append(alpa[i]);
            perm(sb);
            sb.setLength(sb.length() - 1);
        }

    }
}
