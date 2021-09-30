package Programmers.SummerWinterCoding;

import java.util.Arrays;

public class 예산 {

    public int solution(int[] d, int budget) {
        Arrays.sort(d);

        int sum = 0;
        int ans = 0;
        for (int i = 0; i < d.length; i++) {
            sum += d[i];
            System.out.println("sum : " + sum);
            if (budget < sum) {
                ans = i;
                break;
            }
            if (i == d.length - 1) {
                ans = d.length;
            }
        }
        return ans;
    }
}
