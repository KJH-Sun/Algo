package Programmers.위클리챌린지;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class 위클리챌린지_4주차 {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"SI JAVA JAVASCRIPT SQL PYTHON C#",
                "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT",
                "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"},
            new String[]{"JAVA", "JAVASCRIPT"}, new int[]{7, 5}));
    }

    static Map<String, Map<String, Integer>> ls = new HashMap<>();
    static Map<String, Integer> answer = new HashMap<>();

    public static String solution(String[] table, String[] languages, int[] preference) {
        StringTokenizer st;
        for (String job : table) {
            st = new StringTokenizer(job);
            String jobName = st.nextToken();
            ls.put(jobName, new HashMap<>()); // 직업 이름
            answer.put(jobName, 0);
            Map<String, Integer> map = ls.get(jobName);
            for (int i = 5; i > 0; i--) {
                map.put(st.nextToken(), i);
            }
        }
        List<Language> res = new ArrayList<>();
        for (String key : ls.keySet()) {
            Map<String, Integer> map = ls.get(key);
            for (int i = 0; i < languages.length; i++) {
                String lang = languages[i];
                int pref = preference[i];
                if (map.containsKey(lang)) {
                    answer.put(key, answer.get(key) + (map.get(lang) * pref));
                }
            }
            res.add(new Language(answer.get(key), key));
        }
        Collections.sort(res);

        return res.get(0).name;
    }

    static class Language implements Comparable<Language> {

        int score;
        String name;

        public Language(int score, String name) {
            this.score = score;
            this.name = name;
        }

        @Override
        public int compareTo(Language o) {
            return o.score - this.score == 0 ? name.compareTo(o.name) : o.score - this.score;
        }
    }
}
