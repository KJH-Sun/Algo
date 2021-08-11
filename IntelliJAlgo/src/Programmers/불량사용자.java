package Programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class 불량사용자 {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
            new String[]{"fr*d*", "abc1**"}));
    }

    static Set<Set<String>> result = new HashSet<>();

    public static int solution(String[] user_id, String[] banned_id) {
        Set<String> set = new LinkedHashSet<>();
        dfs(user_id, banned_id, set);
        return result.size();
    }

    private static void dfs(String[] user_id, String[] banned_id, Set<String> set) {
        if (banned_id.length == set.size()) {
            if (isCorrectSet(banned_id, set)) {
                result.add(new HashSet<>(set));
            }
            return;
        }

        for (String user : user_id) {
            if (set.contains(user)) {
                continue;
            }
            set.add(user);
            dfs(user_id, banned_id, set);
            set.remove(user);
        }

    }

    private static boolean isCorrectSet(String[] banned_id, Set<String> set) {
        int idx = 0;
        for (String s : set) {
            if (!canBan(banned_id[idx++], s)) {
                return false;
            }
        }
        return true;

    }

    private static boolean canBan(String ban, String user) {
        if (ban.length() != user.length()) {
            return false;
        }
        for (int i = 0; i < ban.length(); i++) {
            if (ban.charAt(i) == '*') {
                continue;
            }
            if (ban.charAt(i) != user.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
