package Programmers;

import java.awt.Robot;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class 블록이동하기 {

    public static void main(String[] args) {
        System.out.println(solution(
            new int[][]{{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}}));
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static int N;

    public static int solution(int[][] board) {
        map = board;
        N = board.length;
        return bfs();
    }

    private static int bfs() {
        Queue<Robot> que = new ArrayDeque<>();
        que.add(new Robot(new Node(0, 0), new Node(0, 1), 0, true));
        Node goal = new Node(N - 1, N - 1);
        Set<Robot> visit = new HashSet<>();
        while (!que.isEmpty()) {
            Robot r = que.poll();
            if (r.left.equals(goal) || r.right.equals(goal)) {
                return r.cnt;
            }
            if (visit.contains(r)) {
                continue;
            }
            visit.add(r);

            for (int i = 0; i < 4; i++) {
                int nx1 = r.left.x + dx[i];
                int ny1 = r.left.y + dy[i];
                int nx2 = r.right.x + dx[i];
                int ny2 = r.right.y + dy[i];
                Robot nR = new Robot(new Node(nx1, ny1), new Node(nx2, ny2), r.cnt + 1,
                    r.horizontal);
                if (!isValid(nx1, ny1) || !isValid(nx2, ny2)) {
                    continue;
                }
                if (!visit.contains(nR)) {
                    que.add(nR);
                }
            }
            Robot[] rotateR = r.rotate();
            for (Robot rR : rotateR) {
                if (rR != null) {
                    if (!visit.contains(rR)) {
                        que.add(rR);
                    }
                }
            }

        }
        return 0;
    }


    public static boolean isValid(int x, int y) {
        return 0 <= x && 0 <= y && x < N && y < N && map[x][y] == 0;
    }

    static class Robot {

        Node left; // 더 왼쪽이거나 더 위쪽 노드
        Node right;
        int cnt;
        boolean horizontal; // 가로이면 true, 아니면 false

        public Robot(Node node1, Node node2, int cnt, boolean horizontal) {
            if (node1.compareTo(node2) < 0) {
                this.left = node1;
                this.right = node2;
            } else {
                this.left = node2;
                this.right = node1;
            }
            this.cnt = cnt;
            this.horizontal = horizontal;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Robot robot = (Robot) o;
            return (Objects.equals(left, robot.left) && Objects
                .equals(right, robot.right)) || (Objects.equals(right, robot.left) && Objects
                .equals(left, robot.right));
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }

        public Robot[] rotate() {
            Robot[] rotateRobot = new Robot[4];
            if (horizontal) { // 가로방향일때
                if (isValid(left.x + 1, left.y) && isValid(left.x + 1,
                    left.y + 1)) { // 아래쪽 양쪽 다 회전 가능한 경우
                    rotateRobot[0] = new Robot(new Node(left.x + 1, left.y + 1),
                        new Node(right.x, right.y), cnt + 1, !horizontal);
                    rotateRobot[1] = new Robot(new Node(left.x, left.y),
                        new Node(left.x + 1, left.y), cnt + 1, !horizontal);
                }
                if (isValid(left.x - 1, left.y) && isValid(left.x - 1,
                    left.y + 1)) { // 위쪽 두칸 다 회전 가능한 경우
                    rotateRobot[2] = new Robot(new Node(left.x - 1, left.y + 1),
                        new Node(right.x, right.y), cnt + 1, !horizontal);
                    rotateRobot[3] = new Robot(new Node(left.x, left.y),
                        new Node(left.x - 1, left.y), cnt + 1, !horizontal);
                }
            } else { // 세로방향일때
                if (isValid(left.x, left.y + 1) && isValid(right.x, left.y + 1)) { // 오른쪽 비어있을 때
                    rotateRobot[0] = new Robot(new Node(right.x, left.y + 1),
                        new Node(right.x, right.y), cnt + 1, !horizontal);
                    rotateRobot[1] = new Robot(new Node(left.x, left.y),
                        new Node(left.x, left.y + 1), cnt + 1, !horizontal);
                }
                if (isValid(left.x, left.y - 1) && isValid(right.x, left.y - 1)) { // 왼쪽 비어있을 때
                    rotateRobot[2] = new Robot(new Node(right.x, left.y - 1),
                        new Node(right.x, right.y), cnt + 1, !horizontal);
                    rotateRobot[3] = new Robot(new Node(left.x, left.y),
                        new Node(left.x, left.y - 1), cnt + 1, !horizontal);
                }
            }
            return rotateRobot;
        }

    }


    static class Node implements Comparable<Node> {

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public int compareTo(Node o) {
            return this.x - o.x == 0 ? this.y - o.y : this.x - o.x;
        }
    }

}
