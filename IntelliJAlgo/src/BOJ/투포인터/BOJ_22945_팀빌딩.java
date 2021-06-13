package BOJ.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;

public class BOJ_22945_팀빌딩 {

    static Function<String, Integer> stoi = Integer::parseInt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = 0;
        int n = stoi.apply(br.readLine());
        int[] developers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            developers[i] = stoi.apply(st.nextToken());
        }
        int left = 0;
        int right = n - 1;
        while (left != right) {
            ans = Math.max(ans, (right - left - 1) * Math.min(developers[left], developers[right]));
            if (developers[left] < developers[right]) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(ans);
    }

}
