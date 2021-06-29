package BOJ.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

public class BOJ_19238_스타트택시 {

    static int N, M, InitFuel;
    static Function<String, Integer> stoi = Integer::parseInt;
    static List<Passenger> passengers = new ArrayList<>();
    static int[][] map;
    static Taxi taxi;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int moveToPassFuel;
    static int moveToDestFuel;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi.apply(st.nextToken());
        M = stoi.apply(st.nextToken());
        InitFuel = stoi.apply(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stoi.apply(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        taxi = new Taxi(new Node(stoi.apply(st.nextToken()) - 1, stoi.apply(st.nextToken()) - 1),
            InitFuel);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = stoi.apply(st.nextToken()) - 1;
            int y = stoi.apply(st.nextToken()) - 1;
            map[x][y] = 2;

            passengers.add(new Passenger(
                new Node(x, y),
                new Node(stoi.apply(st.nextToken()) - 1, stoi.apply(st.nextToken()) - 1)));
        }
        while (checkExistPassenger()) {
            Passenger pass = findNearPassenger();
            if (pass == null || !canCarryPassenger(pass)) {
                canNotMove();
            }
            if (taxi.fuel < moveToPassFuel + moveToDestFuel) {
                canNotMove();
            } else {
                taxi.pos = pass.dest;
                taxi.fuel -= moveToPassFuel;
                taxi.fuel += moveToDestFuel;
                removePassenger(pass);
            }
        }
        System.out.println(taxi.fuel);
    }

    private static boolean checkExistPassenger() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void removePassenger(Passenger pass) {
        for (Passenger p : passengers) {
            if (p.equals(pass)) {
                map[p.pos.x][p.pos.y] = 0;
                passengers.remove(p);
                return;
            }
        }

    }

    private static boolean canCarryPassenger(Passenger pass) {
        Queue<Car> que = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];
        que.offer(new Car(pass.pos, 0));
        Node dest = pass.dest;
        while (!que.isEmpty()) {
            Car c = que.poll();
            if (c.pos.x == dest.x && c.pos.y == dest.y) {
                moveToDestFuel = c.moveCnt;
                return true;
            }
            bfs(que, visit, c);
        }
        return false;
    }

    private static void bfs(Queue<Car> que, boolean[][] visit, Car c) {
        if (visit[c.pos.x][c.pos.y]) {
            return;
        }
        visit[c.pos.x][c.pos.y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = c.pos.x + dx[i];
            int ny = c.pos.y + dy[i];
            if (isValid(nx, ny) && !visit[nx][ny]) {
                que.add(new Car(new Node(nx, ny), c.moveCnt + 1));
            }
        }
    }

    private static void canNotMove() {
        System.out.println(-1);
        System.exit(0);
    }


    private static Passenger findNearPassenger() {
        Queue<Car> que = new ArrayDeque<>();
        que.offer(new Car(taxi.pos, 0));
        boolean[][] visit = new boolean[N][N];
        moveToPassFuel = -1;
        List<Node> nearBy = new ArrayList<>();
        while (!que.isEmpty()) {
            Car c = que.poll();
            if (moveToPassFuel != -1) {
                if (c.moveCnt > moveToPassFuel) {
                    continue;
                }
            }
            if (map[c.pos.x][c.pos.y] == 2) {
                moveToPassFuel = c.moveCnt;
                nearBy.add(c.pos);
                continue;
            }
            bfs(que, visit, c);
        }
        if (nearBy.isEmpty()) {
            return null;
        }
        Collections.sort(nearBy);
        return findPassenger(nearBy.get(0));
    }

    private static Passenger findPassenger(Node node) {
        for (Passenger pass : passengers) {
            if (pass.pos.x == node.x && pass.pos.y == node.y) {
                return pass;
            }
        }
        return null;
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && 0 <= y && x < N && y < N && map[x][y] != 1;
    }

    static class Taxi {

        Node pos;
        int fuel;

        public Taxi(Node pos, int fuel) {
            this.pos = pos;
            this.fuel = fuel;
        }
    }

    static class Passenger {

        Node pos;
        Node dest;

        public Passenger(Node pos, Node dest) {
            this.pos = pos;
            this.dest = dest;
        }
    }

    static class Node implements Comparable<Node> {

        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            return this.x - o.x == 0 ? this.y - o.y : this.x - o.x;
        }
    }

    static class Car {

        Node pos;
        int moveCnt;

        public Car(Node pos, int moveCnt) {
            this.pos = pos;
            this.moveCnt = moveCnt;
        }
    }
}
