import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1068_트리 {
	static int Nodecnt, DeleteNodeNum;
	static int root;
	static int cnt;
	static Map<Integer, Node> tree = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Nodecnt = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Nodecnt; i++) {
			tree.put(i, new Node());
		}
		for (int i = 0; i < Nodecnt; i++) {
			int idx = stoi(st.nextToken());
			if (idx == -1) {
				root = i;
				continue;
			} else {
				tree.get(idx).child.add(new Node(i));
			}
		}
		DeleteNodeNum = stoi(br.readLine());
		if (DeleteNodeNum == root) {
			System.out.println(0);
			System.exit(0);
		}
		bfs();
		dfs(root);
		System.out.println(cnt);

	}

	private static void dfs(int num) {
		if (tree.get(num).child.size() == 0) {
			cnt++;
			return;
		}
		for (int i = 0; i < tree.get(num).child.size(); i++) {
			dfs(tree.get(num).child.get(i).idx);
		}
	}

	private static void bfs() {
		Queue<Integer> que = new LinkedList<>();
		que.offer(root);
		while (!que.isEmpty()) {
			int v = que.poll();
			for (Node n : tree.get(v).child) {
				if (n.idx == DeleteNodeNum) {
					tree.get(v).child.remove(n);
					que.clear();
					break;
				} else {
					que.offer(n.idx);
				}
			}
		}
	}

	static class Node {
		int idx;
		List<Node> child;

		Node() {
			child = new ArrayList<>();
		}

		Node(int idx) {
			this.idx = idx;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
