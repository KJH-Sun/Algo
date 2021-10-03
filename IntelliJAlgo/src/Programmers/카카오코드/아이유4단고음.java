package Programmers.카카오코드;

public class 아이유4단고음 {

    public int solution(int n) {
        return solve(n, 0);
    }

    public int solve(int n, int plusCnt) {
        if (n == 3 && plusCnt == 2) {
            return 1;
        } else if (n == 2 || n == 3) {
            return 0;
        }

        if (Math.pow(3, plusCnt / 2) > n) {
            return 0;
        }

        int ans = 0;

        if (n % 3 == 0) {
            if (plusCnt >= 2) {
                ans += solve(n / 3, plusCnt - 2);
            }
            ans += solve(n - 1, plusCnt + 1);
        } else {
            ans += solve(n - 1, plusCnt + 1);
        }
        return ans;

    }
}
