package Programmers.SummerWinterCoding;

import java.util.HashSet;

public class 영어끝말잇기 {

    public int[] solution(int n, String[] words) {
        int[] count = new int[n];
        int idx = 0;
        int prev = 0;
        char prevChar = words[0].charAt(0);
        HashSet<String> set = new HashSet<>();
        for (String word : words) {
            count[idx++ % n]++;
            set.add(word);
            if (set.size() == prev || word.charAt(0) != prevChar) {
                return new int[]{(idx - 1) % n + 1, count[(idx - 1) % n]};
            }
            prev = set.size();
            prevChar = word.charAt(word.length() - 1);
        }

        return new int[]{0, 0};
    }
}
