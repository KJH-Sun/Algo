import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Swa_1219_길찾기 {
	static int T, N;
	static HashMap<Integer, TreeSet<Integer>> graph = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		for (int tc = 1; tc <= 10; tc++) {
			for (int i = 0; i < 100; i++) {
				graph.put(i, new TreeSet<Integer>());
			}
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			N = stoi(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int x = stoi(st.nextToken());
				int y = stoi(st.nextToken());
				graph.get(x).add(y);
			}
			if (bfs()) {
				sb.append("#" + tc + " " + 1 + "\n");
			} else {
				sb.append("#" + tc + " " + 0 + "\n");
			}
		}
		System.out.println(sb);

	}

	private static boolean bfs() {
		Queue<Integer> que = new LinkedList<>();
		que.offer(0);

		while (!que.isEmpty()) {
			for (int i : graph.get(que.poll())) {
				if (i == 99) {
					return true;
				}
				que.offer(i);
			}
		}
		return false;

	}

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
