package DevMatch;

import java.util.Collections;
import java.util.HashSet;

public class DevMatchNo1 {

    public static void main(String[] args) {
        System.out
            .println(solution(
                new String[]{"cow", "cow1", "cow2", "cow3", "cow4", "cow9", "cow8", "cow7", "cow6",
                    "cow5"},
                "cow"));
    }

    public static String solution(String[] registered_list, String new_id) {
        HashSet<String> registSet = new HashSet<>();
        Collections.addAll(registSet, registered_list);

        if (registSet.contains(new_id)) {
            int firstNidx = findNidx(new_id);
            String S = new_id.substring(0, firstNidx);
            String N = new_id.substring(firstNidx);
            if (N.equals("")) {
                N = "0";
            }
            int next = Integer.parseInt(N) + 1;
            StringBuilder sb = new StringBuilder();
            sb.append(S);
            sb.append(next);
            while (registSet.contains(sb.toString())) {
                sb.setLength(S.length());
                next = next + 1;
                sb.append(next);
            }
            return sb.toString();
        }

        return new_id;
    }

    private static int findNidx(String new_id) {
        for (int i = 0; i < new_id.length(); i++) {
            char c = new_id.charAt(i);
            if (c - '0' < 10 && 0 < c - '0') {
                return i;
            }
        }
        return new_id.length();
    }
}
