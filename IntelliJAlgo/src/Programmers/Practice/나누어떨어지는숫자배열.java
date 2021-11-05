package Programmers.Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 나누어떨어지는숫자배열 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{5, 9, 7, 10}, 5)));
    }

    public static int[] solution(int[] arr, int divisor) {
        List<Integer> res = new ArrayList<>();
        for (int num : arr) {
            if (num % divisor == 0) {
                res.add(num);
            }
        }
        if (res.size() == 0) {
            res.add(-1);
        }
        Collections.sort(res);
        return res.stream().mapToInt(i -> i).toArray();
    }
}
