package Programmers.월간코드챌린지9월;

import java.util.HashSet;
import java.util.Set;

public class No1 {

    public static void main(String[] args) {
    }

    public int solution(int[] numbers) {
        Set<Integer> set = new HashSet<>();
        boolean[] check = new boolean[10];
        for (int number : numbers) {
            check[number] = true;
        }
        int answer = 0;
        for (int i = 0; i < 10; i++) {
            if (!check[i]) {
                answer += i;
            }
        }

        return answer;
    }
}
