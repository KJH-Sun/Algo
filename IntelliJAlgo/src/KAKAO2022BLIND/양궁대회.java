package KAKAO2022BLIND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 양궁대회 {

    public static void main(String[] args) {
        System.out
            .println(Arrays.toString(solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));
    }

    static int[] apeach;
    static int N;
    static int max = Integer.MIN_VALUE;
    static List<Result> res = new ArrayList<>();

    public static int[] solution(int n, int[] info) {
        N = n;
        apeach = info;
        shoot(0, 0, new int[11]);
        if (res.isEmpty()) {
            return new int[]{-1};
        }
        Collections.sort(res);
        return res.get(0).target;
    }

    private static void shoot(int cnt, int idx, int[] lion) {
        if (cnt == N) {
            compareScore(lion);
            return;
        }

        for (int i = idx; i < 11; i++) {
            lion[i]++;
            shoot(cnt + 1, i, lion);
            lion[i]--;
        }
    }

    private static void compareScore(int[] lion) {
        int aScore = 0;
        int lScore = 0;
        for (int i = 0; i < apeach.length; i++) {
            if (apeach[i] == 0 && lion[i] == 0) {
                continue;
            }
            if (apeach[i] >= lion[i]) {
                aScore += 10 - i;
            } else {
                lScore += 10 - i;
            }
        }
        if (lScore > aScore) {
            int diff = lScore - aScore;
            if (diff > max) {
                max = diff;
                res.clear();
                res.add(new Result(Arrays.copyOf(lion, 11)));
            } else if (diff == max) {
                res.add(new Result(Arrays.copyOf(lion, 11)));
            }
        }
    }

    static class Result implements Comparable<Result> {

        int[] target;

        public Result(int[] target) {
            this.target = target;
        }

        @Override
        public int compareTo(Result o) {
            for (int i = target.length - 1; i >= 0; i--) {
                if (target[i] != o.target[i]) {
                    return o.target[i] - target[i];
                }
            }
            return 0;
        }
    }
}
