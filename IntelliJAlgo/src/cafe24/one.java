package cafe24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class one {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(2, 5)));
    }

    public static String[] solution(int startNumber, int endNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("0".repeat(9));
        List<String> ans = new ArrayList<>();
        int now = startNumber;
        while (true) {
            sb.append(now);
            ans.add(sb.toString());
            sb.deleteCharAt(0);

            if (now == endNumber) {
                break;
            } else if (now > endNumber) {
                now--;
            } else {
                now++;
            }
        }

        return ans.toArray(new String[0]);
    }
}
