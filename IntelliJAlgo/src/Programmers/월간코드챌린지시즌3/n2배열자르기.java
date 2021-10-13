package Programmers.월간코드챌린지시즌3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class n2배열자르기 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(3, 2, 5)));
    }

    public static int[] solution(int n, long left, long right) {
        List<Long> ans = new ArrayList<>();
        for (long i = left; i < right + 1; i++) {
            ans.add(Math.max(i / n, i % n) + 1);
        }
        return ans.stream().mapToInt(Math::toIntExact).toArray();
    }


}
