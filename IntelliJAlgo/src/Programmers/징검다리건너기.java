package Programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 징검다리건너기 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
    }
    // 이분탐색으로 체크
    // 정렬해서 시도해봤는데 시간초과에 걸렸음.

    public static int solution(int[] stones, int k) {
        int bot = 1;
        int top = 200000000;
        int mid = 0;
        while (bot <= top) {
            mid = (top + bot) / 2;
            if (canMove(mid, stones, k)) {
                bot = mid + 1;
            } else {
                top = mid - 1;
            }
        }
        return bot - 1;
    }

    private static boolean canMove(int mid, int[] stones, int k) {
        int cnt = 0;
        for (int stone : stones) {
            if (stone - mid < 0) {
                cnt++;
            } else {
                cnt = 0;
            }
            if (cnt == k) {
                return false;
            }
        }
        return true;
    }


}
