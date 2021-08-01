import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;

/*
 * 전형적인 유니온 파인드 문제
 * 원래는 Map<String, List<String>> 으로 처리하려고 했는데, 메모리가 터졌습니다.
 * 굉장히 슬퍼하며 Map은 이름과 인덱스를 매핑하고, 그 인덱스를 parent 배열에 넣는 것으로 관리했습니다.
 * 이후 유니온 파인드 알고리즘으로 풀이했습니다
 * 
 */



public class BOJ_4195_친구네트워크 {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int T, F;
	static int[] parent;
	static int[] rank;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = stoi.apply(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			F = stoi.apply(br.readLine());
			parent = new int[F * 2];
			rank = new int[F * 2];
			for (int i = 0; i < F * 2; i++) {
				parent[i] = i;
				rank[i] = 1;
			}
			Map<String, Integer> network = new HashMap<>();
			StringTokenizer st;
			int idx = 0;
			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine());
				String f1 = st.nextToken();
				String f2 = st.nextToken();
				network.putIfAbsent(f1, idx++);
				network.putIfAbsent(f2, idx++);
				System.out.println(union(network.get(f1), network.get(f2)));

			}
		}

	}

	private static int union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) {
			return rank[x];
		} else {
			parent[y] = x;
			rank[x] += rank[y];
		}
		return rank[x];
	}

	private static int find(int x) {
		return parent[x] = parent[x] == x ? x : find(parent[x]);
	}

}