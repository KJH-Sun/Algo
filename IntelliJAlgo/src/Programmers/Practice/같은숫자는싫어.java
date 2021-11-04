package Programmers.Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class 같은숫자는싫어 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 1, 3, 3, 0, 1, 1})));
    }

    public static int[] solution(int[] arr) {
        int prev = arr[0];
        List<Integer> ans = new ArrayList<>();
        ans.add(prev);
        for (int i = 1; i < arr.length; i++) {
            int num = arr[i];
            if (prev != num) {
                ans.add(num);
                prev = num;
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

}
