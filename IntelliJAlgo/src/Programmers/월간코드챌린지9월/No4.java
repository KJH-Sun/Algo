package Programmers.월간코드챌린지9월;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class No4 {

    public static void main(String[] args) {
        System.out
            .println(Arrays.toString(
                solution(new int[]{1, 1, 1, 1, 1, 1, 2, 5, 8, 2, 1, 1, 4, 8, 8, 8, 12, 6, 6},
                    new int[]{4, 3, 1, 5, 6})));
    }

    static int[] answer;
    static int now;

    public static int[] solution(int[] a, int[] s) {
        answer = new int[s.length];
        int idx = 0;
        for (int i = 0; i < answer.length; i++) {
            now = i;
            dfs(0, Arrays.copyOfRange(a, idx, idx + s[i]), s[i], 0);
            if (s[i] == 1) {
                answer[i] = 1;
            }
            idx += s[i];
        }

        return answer;
    }

    private static void dfs(int i, int[] nums, int n, int idx) {
        if (i == n || nums.length == 1) {
            return;
        }
        if (idx - 1 < 0) {
            answer[now]++;
            dfs(i + 1, nums, n, idx + 1);
            return;
        }
        if (nums[idx] == nums[idx - 1]) {
            dfs(i + 1, nums, n, idx + 1);

            int[] tmp = new int[nums.length - 1];
            for (int j = 0; j < nums.length - 1; j++) {
                if (j < idx - 1) {
                    tmp[j] = nums[j];
                } else if (idx - 1 == j) {
                    tmp[j] = nums[j] + nums[j + 1];
                } else {
                    tmp[j] = nums[j + 1];
                }
            }
            answer[now]++;
            dfs(i, tmp, n, idx - 1);
        } else {
            dfs(i + 1, nums, n, idx + 1);
        }
    }
}
