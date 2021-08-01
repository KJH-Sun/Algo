import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 친구 만드는거 너무 슬프다...
 * 입력받은 간선대로 쭉 유니온 파인드하고
 * 
 * 
 */

public class BOJ_16562_친구비 {
	static int N, M, K;
	static int[] map; // root노드를 unionfind할 배열
	static int[] fp; // 친구비를 저장할 int배열
	static Set<Integer> friend = new HashSet<>(); // 방문한 노드

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		st = new StringTokenizer(br.readLine());
		fp = new int[N + 1];
		map = new int[N + 1];
		for (int i = 1; i <= N; i++) { 
			fp[i] = stoi(st.nextToken());
			map[i] = i;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = stoi(st.nextToken());
			int v = stoi(st.nextToken());
			union(u, v);
		}
		friend.add(find(map[1])); // 1번 친구와 연결된 루트 친구를 방문처리
		for (int i = 2; i <= N; i++) {
			if (!friend.contains((Integer) find(map[i]))) { // 이미 방문하지 않은 친구(루트)가 있으면
				friend.add(find(map[i])); // 추가해줌
			}
		}
		int sum = 0;
		for (int i : friend) { // 루트친구들만 추가된(친구비가 적은 친구가 루트입니다) 것들의 친구비 더하기
			sum += fp[i];
		}
		if (sum > K) { // 최종 친구비가 내가 가진 돈보다 적으면
			System.out.println("Oh no"); // 친구없음
		} else {
			System.out.println(sum); // 아니면 생김
		}

	}

	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u == v) {
			return;
		}
		if (fp[u] >= fp[v]) {
			map[u] = v;
		} else {
			map[v] = u;
		}
	}

	private static int find(int u) {
		return map[u] = map[u] == u ? u : find(map[u]);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
