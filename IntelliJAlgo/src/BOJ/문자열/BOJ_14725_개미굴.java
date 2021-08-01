package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.function.Function;

/*
 * 전형적인 Trie 알고리즘 문제.
 */

public class BOJ_14725_개미굴 {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int N;
	static Trie root;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = stoi.apply(br.readLine());
		root = new Trie();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int k = stoi.apply(st.nextToken());
			Node node = root.node;
			for (int j = 0; j < k; j++) {
				node = node.down.computeIfAbsent(st.nextToken(), s -> new Node());
			}
		}
		root.node.print(0);
	}

	static class Trie {
		Node node;

		Trie() {
			node = new Node();
		}

	}

	static class Node {
		Map<String, Node> down;

		Node() {
			down = new TreeMap<String, Node>();
		}

		public void print(int depth) {
			for (String s : down.keySet()) {
				for (int i = 0; i < depth; i++) {
					System.out.print("--");
				}
				System.out.println(s);
				down.get(s).print(depth + 1);
			}
		}
	}

}