package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17472_다리만들기2 {
	static int N, M;
	static int[][] map;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static List<List<Node>> isLs;
	static List<Edge>[] bridges;
	static int isCnt;
	static int tCh = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		isLs = new ArrayList<List<Node>>();
		divisL();
		bridges = new ArrayList[isCnt];
		for (int i = 0; i < isCnt; i++) {
			bridges[i] = new ArrayList<Edge>();
		}
		BuildBridge(); // 각 섬들을 잇는 간선을 추가하는 함수
		System.out.println(prim());

	}

	private static int prim() {
		PriorityQueue<Edge> pQ = new PriorityQueue<>();
		Queue<Integer> vQ = new ArrayDeque<>();
		boolean[] visit = new boolean[isCnt];
		vQ.offer(0);
		int sum = 0;
		int edgeCnt = 0;
		while (!vQ.isEmpty()) {
			int idx = vQ.poll();
			visit[idx] = true;
			for (Edge e : bridges[idx]) {
				if (!visit[e.v]) {
					pQ.offer(e);
				}
			}
			while (!pQ.isEmpty()) {
				Edge e = pQ.poll();
				if (!visit[e.v]) {
					vQ.offer(e.v);
					edgeCnt++;
					sum += e.w;
					break;
				}
			}
		}
		if (edgeCnt == isCnt - 1) {
			return sum;
		} else {
			return -1;
		}
	}

	private static void BuildBridge() {
		for (int i = 0; i < isLs.size(); i++) {
			for (int j = i + 1; j < isLs.size(); j++) {
				int wei = connect(i, j);
				if (wei != -1) { // 다리가 지어지면
					bridges[i].add(new Edge(j, wei));
					bridges[j].add(new Edge(i, wei));
				} else {
					continue;
				}
			}
		}
	}

	private static int connect(int u, int v) {
		int min = Integer.MAX_VALUE;
		// isLs.get(i) 에서 isLs.get(j)의 x,y좌표 대조해서 같은지 체크

		// v+2 인 애를 bfs로 찾아서 return하자

		for (Node un : isLs.get(u)) {
			loop: for (Node vn : isLs.get(v)) {
				if (un.x == vn.x) {
					for (int i = Math.min(un.y, vn.y) + 1; i < Math.max(un.y, vn.y); i++) {
						if (map[un.x][i] != 0) {
							continue loop;
						}
					}
					int dis = Math.abs(un.y - vn.y) - 1;
					if (dis >= 2) {
						min = Math.min(min, dis);
					}
				} else if (un.y == vn.y) {
					for (int i = Math.min(un.x, vn.x) + 1; i < Math.max(un.x, vn.x); i++) {
						if (map[i][un.y] != 0) {
							continue loop;
						}
					}
					int dis = Math.abs(un.x - vn.x) - 1;
					if (dis >= 2) {
						min = Math.min(min, dis);
					}
				}
			}
		}
		if (min == Integer.MAX_VALUE) {
			return -1;
		} else {
			return min;
		}
	}

	private static void divisL() { // 섬별로 분할해서 isLs에 넣기 위한 함수
		int idx = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					bfs(i, j, idx++);
				}
			}
		}
	}

	private static void bfs(int x, int y, int idx) { // 섬별로 분할해서 isLs에 넣기 위한 함수
		isLs.add(new ArrayList<Node>());
		Queue<Node> que = new ArrayDeque<>();
		que.offer(new Node(x, y));
		isLs.get(isCnt).add(new Node(x, y));
		map[x][y] = idx;
		while (!que.isEmpty()) {
			Node n = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] == 1) {
					map[nx][ny] = idx;
					que.offer(new Node(nx, ny));
					isLs.get(isCnt).add(new Node(nx, ny));
				}
			}
		}

		isCnt++;
	}

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Edge implements Comparable<Edge> {
		int v;
		int w;

		Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
