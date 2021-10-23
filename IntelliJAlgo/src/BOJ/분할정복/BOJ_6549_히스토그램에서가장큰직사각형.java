package BOJ.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.function.Function;

/*
    스택 방식 풀이(On)
 */


public class BOJ_6549_히스토그램에서가장큰직사각형 {

    static Function<String, Integer> stoi = Integer::parseInt;
    static int[] sticks;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            String s = br.readLine();
            if (s.equals("0")) {
                break;
            }
            st = new StringTokenizer(s);
            int n = stoi.apply(st.nextToken());
            sticks = new int[n];
            for (int i = 0; i < n; i++) {
                sticks[i] = stoi.apply(st.nextToken());
            }
            getMaxRectangle();
        }
        System.out.println(sb.toString());
    }

    private static void getMaxRectangle() {
        Stack<Integer> stack = new Stack<>();
        int len = sticks.length;
        long max = 0;
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && sticks[stack.peek()] >= sticks[i]) {
                int h = sticks[stack.pop()];
                long w = stack.isEmpty() ? i : i - 1 - stack.peek();
                max = Math.max(max, w * h);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int h = sticks[stack.pop()];
            long w = stack.isEmpty() ? len : len - 1 - stack.peek();
            max = Math.max(max, w * h);
        }
        sb.append(max).append("\n");
    }
}
