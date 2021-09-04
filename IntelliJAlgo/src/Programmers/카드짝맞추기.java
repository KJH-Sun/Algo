package Programmers;


/*
    2021 카카오 블라인드 채용
    카드 짝 맞추기
    BFS

    로직을 세울 때, 현재 위치에서 가장 가까운 카드를 선택하는 형태로 로직을 세우면 통과하지 못한다.
    순열로 모든 카드를 뒤집는 경우의 수를 확인한 이후,
    두 카드 중 어떤 카드를 뒤집을 지 또한 모두 고려하여 결정해야한다.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 카드짝맞추기 {

    public static void main(String[] args) {
        System.out.println(
            solution(new int[][]{{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}}, 1, 0));
    }

    static int move = Integer.MAX_VALUE;
    static int pair = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static List<int[]> cardOrder = new ArrayList<>();

    public static int solution(int[][] board, int r, int c) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    pair++;
                }
            }
        }
        pair /= 2;
        perm(new ArrayList<>(), 0);

        for (int[] order : cardOrder) {
            int total = 0;
            int[] nowPoint = new int[2]; // 0 x 1 y
            nowPoint[0] = r;
            nowPoint[1] = c;
            int[][] tmpBoard = new int[4][4];
            deepCopy(tmpBoard, board);
            for (int card : order) {
                int cnt = 0;
                cnt += moveCard(nowPoint, card, tmpBoard) + 1;
                System.out.println(nowPoint[0] + "," + nowPoint[1]);
                cnt += moveCard(nowPoint, card, tmpBoard) + 1;
                System.out.println(nowPoint[0] + "," + nowPoint[1]);
                total += cnt;
            }
            System.out.println("total : " + total);
            move = Math.min(move, total);
        }

        return move;
    }

    private static int moveCard(int[] nowPoint, int cardNum, int[][] board) {
        Queue<Node> que = new ArrayDeque<>();
        que.add(new Node(nowPoint[0], nowPoint[1], 0));
        boolean[][] visit = new boolean[4][4];
        int res = 0;
        while (!que.isEmpty()) {
            Node n = que.poll();
            if (visit[n.x][n.y]) {
                continue;
            }
            visit[n.x][n.y] = true;
            if (board[n.x][n.y] == cardNum) {
                board[n.x][n.y] = 0;
                nowPoint[0] = n.x;
                nowPoint[1] = n.y;
                res = n.cnt;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if (isValid(nx, ny) && !visit[nx][ny]) {
                    que.add(new Node(nx, ny, n.cnt + 1));
                }
                que.add(findWay(dx[i], dy[i], board, n));
            }
        }
        return res;
    }

    private static Node findWay(int dirX, int dirY, int[][] board, Node n) {
        int nx = n.x;
        int ny = n.y;
        do {
            nx += dirX;
            ny += dirY;
            if (isValid(nx, ny) && board[nx][ny] != 0) {
                return new Node(nx, ny, n.cnt + 1);
            }
        } while (isValid(nx, ny));
        return new Node(nx - dirX, ny - dirY, n.cnt + 1);
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && 0 <= y && x < 4 && y < 4;
    }

    private static void deepCopy(int[][] tmpBoard, int[][] board) {
        for (int i = 0; i < 4; i++) {
            System.arraycopy(board[i], 0, tmpBoard[i], 0, 4);
        }
    }

    private static void perm(List<Integer> res, int flag) {
        if (res.size() == pair) {
            cardOrder.add(res.stream().mapToInt(i -> i).toArray());
            return;
        }
        for (int i = 1; i <= pair; i++) {
            if ((flag & 1 << i) == 0) {
                res.add(i);
                perm(res, flag + (1 << i));
                res.remove(res.size() - 1);
            }
        }
    }

    static class Node {

        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }


}
