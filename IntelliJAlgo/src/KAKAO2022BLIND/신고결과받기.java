package KAKAO2022BLIND;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class 신고결과받기 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"muzi", "frodo", "apeach", "neo"},
            new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"},
            2)));
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> bans = new HashMap<>();
        Map<String, Integer> idNums = new HashMap<>();
        int idx = 0;
        for (String id : id_list) {
            idNums.put(id, idx++);
        }
        StringTokenizer st;
        for (String s : report) {
            st = new StringTokenizer(s);
            String from = st.nextToken();
            String to = st.nextToken();
            bans.computeIfAbsent(to, (e) -> new HashSet<>());
            bans.get(to).add(from);
        }

        int[] answer = new int[id_list.length];

        for (String name : bans.keySet()) {
            if (bans.get(name).size() >= k) {
                for (String s : bans.get(name)) {
                    answer[idNums.get(s)]++;
                }
            }
        }

        return answer;
    }
}
