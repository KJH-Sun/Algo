package Programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 후보키 {

    public static void main(String[] args) {
        System.out.println(solution(
            new String[][]{{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}}));
    }

    static int col, row, answer;

    public static int solution(String[][] relation) {
        col = relation[0].length;
        row = relation.length;
        List<Integer> candidateKey = new ArrayList<>();
        for (int i = 1; i < (1 << col); i++) {
            if (!isMinimal(i, candidateKey)) {
                continue;
            }
            if (isUnique(i, candidateKey, relation)) {
                System.out.println(Integer.toBinaryString(i));
                candidateKey.add(i);
            }
        }
        return candidateKey.size();
    }

    private static boolean isUnique(int set, List<Integer> candidateKey, String[][] relation) {
        Set<String> key = new HashSet<>();

        for (int i = 0; i < row; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < col; j++) {
                if ((set & (1 << j)) != 0) {
                    sb.append(relation[i][j]);
                }
            }
            if (key.contains(sb.toString())) {
                return false;
            }
            key.add(sb.toString());
        }
        return true;
    }

    private static boolean isMinimal(int set, List<Integer> candidateKey) {
        for (int key : candidateKey) {
            if ((set & key) == key) {
                return false;
            }
        }
        return true;
    }


}
