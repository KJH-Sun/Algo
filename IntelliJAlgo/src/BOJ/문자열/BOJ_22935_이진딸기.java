package BOJ.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BOJ_22935_이진딸기 {

    static Function<String, Integer> stoi = Integer::parseInt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi.apply(br.readLine());

        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 15; i++) {
            sb.setLength(0);
            String binary = Integer.toBinaryString(i);
            if (binary.length() < 4) {
                sb.append("V".repeat(Math.max(0, 4 - binary.length())));
            }
            for (int j = 0; j < binary.length(); j++) {
                char c = binary.charAt(j);
                if (c - '0' == 0) {
                    sb.append("V");
                } else {
                    sb.append("딸기");
                }
            }
            ans.add(sb.toString());
        }

        for (int i = 0; i < N; i++) {
            int num = stoi.apply(br.readLine());
            if (num > 15) {
                int num2 = num - 16;
                int p = num2 / 14;
                int q = num2 % 14;
                if (p % 2 == 0) { //양수
                    System.out.println(ans.get(13 - q));
                } else {
                    System.out.println(ans.get(q + 1));
                }
            } else {
                System.out.println(ans.get(num - 1));
            }

        }
    }

}
