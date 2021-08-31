package Programmers;

import java.util.HashMap;
import java.util.Map;

public class 단체사진찍기 {

    public static void main(String[] args) {
        System.out.println(solution(2, new String[]{"N~F=0", "R~T>2"}));
    }

    static char[] kakaos = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static Map<String, Integer> dist = new HashMap<>();
    static int answer = 0;

    public static int solution(int n, String[] data) {
        perm(new StringBuilder(), 0, data);
        return answer;
    }

    public static void perm(StringBuilder sb, int flag, String[] data) {
        if (sb.length() == 8) {
            for (String s : data) {
                int fIdx = sb.indexOf(Character.toString(s.charAt(0)));
                int bIdx = sb.indexOf(Character.toString(s.charAt(2)));
                int dist = Math.abs(fIdx - bIdx);
                int num = s.charAt(4) - '0';
                if (s.charAt(3) == '=') {
                    if (dist != num + 1) {
                        return;
                    }
                } else if (s.charAt(3) == '>') {
                    if (dist <= num + 1) {
                        return;
                    }
                } else {
                    if (dist >= num + 1) {
                        return;
                    }
                }
            }
            answer++;
            return;
        }

        for (int i = 0; i < 8; i++) {
            if ((flag & 1 << i) == 0) {
                sb.append(kakaos[i]);
                perm(sb, flag + (1 << i), data);
                sb.setLength(sb.length() - 1);
            }
        }
    }
}
