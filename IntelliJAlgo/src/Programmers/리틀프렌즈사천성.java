package Programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
    순열 + BFS로 풀이했었는데, 시간초과가 발생했다.
    시간을 줄일 방법을 찾지 못해 타인의 풀이를 참고하였고, BFS를 사용하지 않는 것 + 알파벳 순으로 확인한다면 순열도 사용하지 않아도 된다는 점을 알고 코드를 재구성하였다.
 */

public class 리틀프렌즈사천성 {

    public static void main(String[] args) {
        System.out.println(solution(4, 4, new String[]{".ZI.", "M.**", "MZU.", ".IU."}));
    }

    static int M, N;
    static char[][] map;
    static List<Character> cards;
    static Map<Character, List<Node>> cardPos;

    public static String solution(int m, int n, String[] board) {
        N = m; // 세로
        M = n; // 가로
        cards = new ArrayList<>();
        cardPos = new HashMap<>();
        map = new char[N][M];
        int idx = 0;
        for (String b : board) {
            map[idx++] = b.toCharArray();
        }
        checkCard();
        idx = 0;
        StringBuilder sb = new StringBuilder();
        while (cards.size() != 0) {
            if (canRemove(cards.get(idx))) {
                sb.append(cards.get(idx));
                deleteChar(cards.get(idx));
                cards.remove(idx);
                idx = 0;
            } else {
                idx++;
                if (idx == cards.size()) {
                    return "IMPOSSIBLE";
                }
            }
        }

        return sb.toString();
    }

    private static void deleteChar(char c) {
        Node start = cardPos.get(c).get(0);
        Node end = cardPos.get(c).get(1);
        int r1 = start.x;
        int c1 = start.y;
        int r2 = end.x;
        int c2 = end.y;
        map[r1][c1] = '.';
        map[r2][c2] = '.';
    }

    private static boolean canRemove(char a) {
        Node start = cardPos.get(a).get(0);
        Node end = cardPos.get(a).get(1);
        int r1 = start.x;
        int c1 = start.y;
        int r2 = end.x;
        int c2 = end.y;
        if (c1 < c2) {
            if (linearColumnCheck(c1, c2, r1, a) && linearRowCheck(r1, r2, c2, a)) {
                return true;
            }
            return linearRowCheck(r1, r2, c1, a) && linearColumnCheck(c1, c2, r2, a);
        } else {
            if (linearRowCheck(r1, r2, c2, a) && linearColumnCheck(c2, c1, r1, a)) {
                return true;
            }
            return linearColumnCheck(c2, c1, r2, a) && linearRowCheck(r1, r2, c1, a);
        }
    }

    static boolean linearRowCheck(int r1, int r2, int c, char a) {
        for (int i = r1; i < r2 + 1; i++) {
            if (map[i][c] != '.' && map[i][c] != a) {
                return false;
            }
        }
        return true;
    }

    static boolean linearColumnCheck(int c1, int c2, int r, char a) {
        for (int i = c1; i < c2 + 1; i++) {
            if (map[r][i] != '.' && map[r][i] != a) {
                return false;
            }
        }
        return true;
    }


    private static void checkCard() {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != '.' && map[i][j] != '*') {
                    set.add(map[i][j]);
                    cardPos.computeIfAbsent(map[i][j], (e) -> new ArrayList<>());
                    cardPos.get(map[i][j]).add(new Node(i, j));
                }
            }
        }
        cards.addAll(set);
        Collections.sort(cards);
    }

    static class Node {

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
