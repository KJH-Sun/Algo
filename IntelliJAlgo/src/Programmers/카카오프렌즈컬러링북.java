package Programmers;

import java.util.ArrayDeque;
import java.util.Queue;

/*
    BFS 기초 문제
 */


public class 카카오프렌즈컬러링북 {

    static boolean[][] visit;
    static int[] answer; // 프로그래머스 문제에서 전역변수 초기화를 여기서 하면 틀리지만, 이유를 모르겠습니다.
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int M, N;

    public int[] solution(int m, int n, int[][] picture) {
        M = m;
        N = n;
        visit = new boolean[m][n];
        answer = new int[2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j] && picture[i][j] != 0) {
                    bfs(i, j, picture);
                }
            }
        }
        return answer;
    }

    public void bfs(int x, int y, int[][] picture) {
        answer[0]++;
        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(x, y));
        int num = picture[x][y];
        int cnt = 1;
        visit[x][y] = true;

        while (!que.isEmpty()) {
            Node n = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if (isValid(nx, ny) && !visit[nx][ny] && picture[nx][ny] == num) {
                    que.offer(new Node(nx, ny));
                    visit[nx][ny] = true;
                    cnt++;
                    // System.out.println("현재 그룹 "+ answer[0] + " nx = " + nx + " ny = " + ny);
                }
            }
        }
        answer[1] = Math.max(answer[1], cnt);

    }

    public boolean isValid(int x, int y) {
        return 0 <= x && 0 <= y && x < M && y < N;
    }

    static class Node {

        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
