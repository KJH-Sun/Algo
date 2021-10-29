package Programmers.위클리챌린지;

public class 부족한금액계산하기 {

    public static void main(String[] args) {
        System.out.println(solution(3, 20, 4));
    }

    public static long solution(int price, int money, int count) {
        long right = (long) count * (count + 1) / 2;
        return (long) price * right - money >= 0 ? (long) price * right - money : 0;
    }
}
