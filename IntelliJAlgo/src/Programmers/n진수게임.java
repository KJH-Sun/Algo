package Programmers;

import java.util.ArrayList;
import java.util.List;

public class n진수게임 {

    public static void main(String[] args) {
        System.out.println(solution(16, 16, 2, 2));
    }

    // n -> radix
    // t -> 말해야할 숫자의 개수
    // m -> 참가 인원
    // p -> 튜브의 첫번째 인덱스
    // size가 m*(t-1)+p 개이면 가능
    public static String solution(int n, int t, int m, int p) {
        List<Character> nums = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int now = 0;
        if (m == p) {
            p = 0;
        }
        outer:
        while (true) {
            String s = Integer.toString(now, n);
            for (int i = 0; i < s.length(); i++) {
                nums.add(s.charAt(i));
                if (nums.size() % m == p) {
                    sb.append(s.charAt(i));
                }
                if (sb.length() == t) {
                    break outer;
                }
            }
            now++;
        }
        return sb.toString().toUpperCase();
    }
}
