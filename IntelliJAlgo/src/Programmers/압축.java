package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    카카오 인턴십
 */

public class 압축 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("ABABABABABABABAB")));
    }

    public static int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        init(map);
        int idx = 27;
        List<Integer> res = new ArrayList<>();
        int w = 1;
        outer:
        for (int i = 0; i < msg.length(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(msg.charAt(i));
            while (map.containsKey(sb.toString())) {
                w = map.get(sb.toString());
                if (i < msg.length() - 1) {
                    sb.append(msg.charAt(++i));
                } else {
                    res.add(w);
                    break outer;
                }
            }
            res.add(w);
            i--;
            map.put(sb.toString(), idx++);
        }

        return res.stream().mapToInt(i->i).toArray();
    }

    private static void init(Map<String, Integer> map) {
        char ch = 'A';
        for (int i = 1; i <= 26; i++) {
            map.put(ch + "", i);
            ch += 1;
        }
    }

}
