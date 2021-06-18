package Programmers.SummerWinterCoding;

import java.util.Arrays;
import java.util.Collections;

public class 숫자게임 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{5, 1, 3, 7}, new int[]{2, 2, 6, 8}));
    }

    public static int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int answer = 0;
        int aIdx = 0;
        for (int i : B) {
            if (A[aIdx] < i) {
                aIdx++;
                answer++;
            }
        }

        return answer;
    }
}
