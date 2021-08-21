package Programmers;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import org.w3c.dom.Node;

public class 미로탈출 {

    public static void main(String[] args) {
        System.out.println(
            solution(4, 1, 4, new int[][]{{1, 2, 1}, {3, 2, 1}, {2, 4, 1}}, new int[]{2, 3}));
    }

    /*
        한쪽만 트랩이 활성화되어있는 경우 역방향
        양쪽 다 트랩이 활성화된 경우 정방향
     */
    static List<Node>[] list;
    static List<Node>[] rList;
    static Map<Integer, Integer> bitTrap;
    static int[][] dist;
    static final int INF = (int) 1e9;
    static int answer = INF;

    public static int solution(int n, int start, int end, int[][] roads, int[] traps) {
        list = new ArrayList[n + 1];
        rList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
            rList[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            list[road[0]].add(new Node(road[1], road[2], 0));
            rList[road[1]].add(new Node(road[0], road[2], 0));
        }
        int idx = 1;
        bitTrap = new HashMap<>();
        for (int i = 0; i < traps.length; i++) {
            bitTrap.put(traps[i], 1 << (i + 1));
        }
        dist = new int[n + 1][1 << traps.length + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
        }

        dijk(start, end);
        for (int res : dist[end]) {
            answer = Math.min(res, answer);
        }
        return answer;
    }

    private static void dijk(int start, int end) {
        Queue<Node> que = new PriorityQueue<>();
        que.offer(new Node(start, 0, 0));
        dist[start][0] = 0;

        while (!que.isEmpty()) {
            Node n = que.poll();
            if (n.to == end) {
                break;
            }
            boolean fromActive = false;
            if (bitTrap.containsKey(n.to)) {
                if ((n.status & bitTrap.get(n.to)) == bitTrap.get(n.to)) {
                    fromActive = true;
                }
            }

            boolean toActive = false;
            for (Node next : list[n.to]) {
                int nStatus = n.status;
                if (bitTrap.containsKey(next.to)) {
                    toActive = ((n.status & bitTrap.get(next.to)) != 0);
                    nStatus = bitFlip(toActive, nStatus, bitTrap.get(next.to));

                    if (fromActive & toActive || (!fromActive & !toActive)) {
                        if (dist[next.to][n.status] > n.cost + next.cost) {
                            dist[next.to][n.status] = n.cost + next.cost;
                            que.offer(new Node(next.to, dist[next.to][n.status], nStatus));
                        }
                    }
                } else {
                    if (!fromActive) {
                        if (dist[next.to][n.status] > n.cost + next.cost) {
                            dist[next.to][n.status] = n.cost + next.cost;
                            que.offer(new Node(next.to, dist[next.to][n.status], nStatus));
                        }
                    }
                }
            }

            toActive = false;
            for (Node next : rList[n.to]) {
                int nStatus = n.status;
                if (bitTrap.containsKey(next.to)) {
                    toActive = ((n.status & bitTrap.get(next.to)) != 0);
                    nStatus = bitFlip(toActive, nStatus, bitTrap.get(next.to));

                }
                if (fromActive ^ toActive) {
                    if (dist[next.to][n.status] > n.cost + next.cost) {
                        dist[next.to][n.status] = n.cost + next.cost;
                        que.offer(new Node(next.to, dist[next.to][n.status], nStatus));
                    }
                }
            }


        }
    }

    private static int bitFlip(boolean flag, int now, int node) {
        // 다음 노드가 trap인데 활성화 되어있는 경우
        if (flag) {
            // 1110
            // 0010
            // 1100
            return now ^ node;
        }
        // 다음 노드가 trap인데 활성화 되어있지 않은 경우
        else {
            // 1100
            // 0010
            // 1110
            return now | node;
        }
    }

    static class Node implements Comparable<Node> {

        int to;
        int cost;
        int status;

        public Node(int to, int cost, int status) {
            this.to = to;
            this.cost = cost;
            this.status = status;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }


}
