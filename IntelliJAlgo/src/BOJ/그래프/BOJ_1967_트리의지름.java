import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 최대한 플랫하게 접근해서, 
 * 특정 노드에서 그 노드에서 시작해서 리프노드(끝)에 도달할 때까지 가장 가중치의 합이 큰 리프노드를 찾는 함수를 만들었습니다.
 * 2. 가장 유망한 리프 노드를 찾기위해, 루트 노드에서 시작해서 루트노드부터 가장 가중치의 합이 큰 리프노드를 찾고, 거기서 다시 함수를 돌려서
 * 답을 도출
 * 
 */
public class BOJ_1967_트리의지름 {
	static int N;
	static List<Node>[] tree;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		tree = new ArrayList[N + 1];
		if(N==1) {
			System.out.println(0);
			System.exit(0);
		}

		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) { // 간선의 개수는 정점의 개수 -1
			st = new StringTokenizer(br.readLine());
			int s = stoi(st.nextToken());
			int e = stoi(st.nextToken());
			int d = stoi(st.nextToken());

			tree[s].add(new Node(e, d));
			tree[e].add(new Node(s, d));
		}
		visit = new boolean[N + 1];
		int maxDistNode = getTreeDiameter(1).idx;
		visit = new boolean[N + 1];
		System.out.println(getTreeDiameter(maxDistNode).weight);

	}

	private static Node getTreeDiameter(int s) {
		int maxWeight = 0;
		int maxEndNodeIdx = 0;

		Queue<Node> que = new LinkedList<>();
		que.offer(new Node(s, 0));
		visit[s] = true;

		while (!que.isEmpty()) {
			Node n = que.poll();
			for (int i = 0; i < tree[n.idx].size(); i++) {
				Node tmp = tree[n.idx].get(i);
				if (!visit[tmp.idx]) {
					int nw = n.weight + tmp.weight;
					que.offer(new Node(tmp.idx, nw));
					visit[tmp.idx] = true;
					if (maxWeight < nw) {
						maxWeight = nw;
						maxEndNodeIdx = tmp.idx;
					}
				}
			}
		}

		return new Node(maxEndNodeIdx, maxWeight);
	}

	static class Node {
		int idx;
		int weight;

		Node(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
