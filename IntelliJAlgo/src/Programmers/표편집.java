package Programmers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 표편집 {

    public static void main(String[] args) {
        System.out.println(
            solution(8, 2,
                new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}));
    }

    public static String solution(int n, int k, String[] cmd) {
        Stack<Integer> cancel = new Stack<>();
        int tableLen = n;
        int now = k;
        int prev = k;
        for (String c : cmd) {
            if (c.length() == 1) {
                if (c.equals("C")) {
                    prev = now;
                    cancel.add(now);
                    tableLen--;
                    if (now == tableLen) {
                        now--;
                    }
                } else { //Z인 경우
                    tableLen++;
                    int r = cancel.pop();
                    if (now >= r) {
                        now++;
                    }
                }
                continue;
            }
            String[] order = c.split(" ");
            if (order[0].equals("U")) {
                now -= Integer.parseInt(order[1]);
            } else {
                now += Integer.parseInt(order[1]);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("O".repeat(Math.max(0, tableLen)));
        while (!cancel.isEmpty()) {
            sb.insert(cancel.pop().intValue(), 'X');
        }
        return sb.toString();
    }
}
