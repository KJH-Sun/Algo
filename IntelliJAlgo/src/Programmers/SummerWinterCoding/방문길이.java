package Programmers.SummerWinterCoding;

import java.util.HashSet;
import java.util.Set;

public class 방문길이 {

    static int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static final int U = 0, D = 1, R = 2, L = 3;

    public static int solution(String dirs) {
        int x = 0;
        int y = 0;
        Set<Edge> moves = new HashSet<>();
        for (int i = 0; i < dirs.length(); i++) {
            char c = dirs.charAt(i);
            int nx = x + dir[move(c)][0];
            int ny = y + dir[move(c)][1];
            if (isValid(nx, ny)) {
                Node start = new Node(x, y);
                x = nx;
                y = ny;
                Node end = new Node(x, y);
                moves.add(new Edge(start, end));
                moves.add(new Edge(end, start));
                System.out.println("x :" + end.x + "y :" + end.y);
            }
        }
        return moves.size() / 2;
    }

    public static int move(char c) {
        int num = -1;
        switch (c) {
            case 'U':
                num = 0;
                break;
            case 'D':
                num = 1;
                break;
            case 'R':
                num = 2;
                break;
            case 'L':
                num = 3;
                break;
        }
        return num;
    }

    public static boolean isValid(int x, int y) {
        return -5 <= x && -5 <= y && x <= 5 && y <= 5;
    }

    static class Node {

        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return this.x * 31 + this.y * 13;
        }

        @Override
        public boolean equals(Object o) {
            Node n = (Node) o;
            return n.x == x && n.y == y;
        }
    }

    static class Edge {

        Node start;
        Node end;

        public Edge(Node start, Node end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((end == null) ? 0 : end.hashCode());
            result = prime * result + ((start == null) ? 0 : start.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            Edge other = (Edge) obj;
            if (end == null) {
                if (other.end != null) {
                    return false;
                }
            } else if (!end.equals(other.end)) {
                return false;
            }
            if (start == null) {
                if (other.start != null) {
                    return false;
                }
            } else if (!start.equals(other.start)) {
                return false;
            }
            return true;
        }
    }
}
