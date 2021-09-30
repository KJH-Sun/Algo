package Programmers.SummerWinterCoding;

public class 점프와순간이동 {

    static int min = Integer.MAX_VALUE;


    public int solution(int n) {
        int dest = n;
        int cnt = 1;
        while (dest != 1) {
            if (dest % 2 == 0) {
                dest /= 2;
            } else {
                cnt++;
                dest -= 1;
            }
        }

        return cnt;
    }

}
