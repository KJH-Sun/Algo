import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Swa_1247_최적경로 {
	static int T, N;
	static Node com, home;
	static List<Node> client = new ArrayList<>();
	static boolean[] visit;
	static int mindis;

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = stoi(br.readLine());
			st = new StringTokenizer(br.readLine());
			com = new Node(stoi(st.nextToken()), stoi(st.nextToken()));
			home = new Node(stoi(st.nextToken()), stoi(st.nextToken()));
			for (int i = 0; i < N; i++) {
				client.add(new Node(stoi(st.nextToken()), stoi(st.nextToken())));
			}
			visit = new boolean[N];
			mindis = Integer.MAX_VALUE;
			perm(0, com.x, com.y, 0);
			sb.append("#" + tc + " " + mindis + "\n");
			client.clear();
		}
		System.out.println(sb);

	}

	private static void perm(int pos, int x, int y, int dis) {
		if (pos == N) {
			mindis = Math.min(mindis, dis + Math.abs(home.x - x) + Math.abs(home.y - y));
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visit[i]) {
				continue;
			}
			visit[i] = true;
			perm(pos + 1, client.get(i).x, client.get(i).y,
					dis + Math.abs(client.get(i).x - x) + Math.abs(client.get(i).y - y));
			visit[i] = false;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
