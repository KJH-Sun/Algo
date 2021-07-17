package Programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class 동굴탐험 {

    public static void main(String[] args) {
        System.out.println(solution(9,
            new int[][]{{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}},
            new int[][]{{8, 5}, {6, 7}, {4, 1}}));
    }

    public static boolean solution(int n, int[][] path, int[][] order) {
        int[] before = new int[n];
        int[] reservation = new int[n]; // 지금 못가는 곳이지만, 길이 열려있어 선행 노드가 열리면 함께 열어줄 노드
        Set<Integer> visit = new HashSet<>();
        List<Integer>[] route = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            route[i] = new ArrayList<>();
        }
        for (int[] p : path) {
            route[p[0]].add(p[1]);
            route[p[1]].add(p[0]);
        }
        for (int[] o : order) {
            before[o[1]] = o[0];
        }
        if (before[0] != 0) { // 30번 테스트케이스 예외처리
            return false;
        }
        visit.add(0);
        Deque<Integer> stack = new ArrayDeque<>(route[0]);
        while (!stack.isEmpty()) {
            int now = stack.pop();

            if (visit.contains(now)) {
                continue;
            }
            if (!visit.contains(before[now])) {
                reservation[before[now]] = now;
                continue;
            }

            visit.add(now);
            stack.addAll(route[now]);
            stack.add(reservation[now]);
        }

        return visit.size() == n;
    }
}
