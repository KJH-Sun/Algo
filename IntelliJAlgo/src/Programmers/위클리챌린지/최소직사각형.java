package Programmers.위클리챌린지;

import java.util.ArrayList;
import java.util.List;

public class 최소직사각형 {

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{60, 50}, {30, 70}, {60, 30}, {80, 40}}));
    }

    public static int solution(int[][] sizes) {
        int maxX = 0;
        int maxY = 0;
        List<BusinessCard> cards = new ArrayList<>();
        for (int[] size : sizes) {
            cards.add(new BusinessCard(size));
        }
        for (BusinessCard card : cards) {
            maxX = Math.max(card.w, maxX);
            maxY = Math.max(card.h, maxY);
        }
        return maxX * maxY;
    }

    static class BusinessCard {

        int w;
        int h;

        public BusinessCard(int[] size) {
            if (size[0] > size[1]) {
                this.w = size[1];
                this.h = size[0];
            } else {
                this.w = size[0];
                this.h = size[1];
            }
        }
    }
}
