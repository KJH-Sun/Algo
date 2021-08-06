package BOJ.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;

public class BOJ_5430_AC {

    static int T;
    static Function<String, Integer> stoi = Integer::parseInt;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = stoi.apply(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            String p = br.readLine();
            ac(p, stoi.apply(br.readLine()), br.readLine());
        }
        System.out.println(ans);
    }

    private static void ac(String p, int n, String x) {
        Deque<String> nums;
        if (n != 0) {
            nums = new ArrayDeque<>(
                Arrays.asList(x.substring(1, x.length() - 1).split(",")));
        } else {
            nums = new ArrayDeque<>();
        }
        boolean head = true;
        for (int i = 0; i < p.length(); i++) {
            switch (p.charAt(i)) {
                case 'R':
                    head = !head;
                    break;
                case 'D':
                    if (nums.isEmpty()) {
                        ans.append("error" + "\n");
                        return;
                    }
                    if (head) {
                        if (nums.pollFirst() == null) {
                            ans.append("error" + "\n");
                        }
                    } else {
                        if (nums.pollLast() == null) {
                            ans.append("error" + "\n");
                        }
                    }
                    break;
            }
        }
        ans.append("[");
        if (nums.size() > 0) {
            if (head) {
                ans.append(nums.pollFirst());
                while (!nums.isEmpty()) {
                    ans.append(",").append(nums.pollFirst());
                }
            } else {
                ans.append(nums.pollLast());
                while (!nums.isEmpty()) {
                    ans.append(",").append(nums.pollLast());
                }
            }
        }
        ans.append("]\n");
    }
}
