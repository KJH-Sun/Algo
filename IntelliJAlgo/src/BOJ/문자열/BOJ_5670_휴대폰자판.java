package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

/*
 * 전형적인 Trie 알고리즘 문제.
 * 난이도에 비해 어려운 문제는 아니다.
 */

public class BOJ_5670_휴대폰자판 {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int N;
	static Trie root;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		while ((str = br.readLine()) != null) {
			N = stoi.apply(str);
			root = new Trie();
			List<String> words = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				words.add(br.readLine());
				parse(words.get(words.size() - 1));
			}
			float sum = 0;
			for (String word : words) {
				sum += root.find(word);
			}
			System.out.printf("%.2f\n", sum / words.size());
		}
	}

	private static void parse(String s) {
		Node node = root.data;
		for (int i = 0; i < s.length(); i++) {
			node = node.children.computeIfAbsent(s.charAt(i), c -> new Node());
		}
		node.end = true;
	}

	static class Trie {
		Node data;

		Trie() {
			data = new Node();
		}

		int find(String s) {
			int sum = 1;
			Node node = data;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				node = node.children.get(c);
				if (i < s.length() - 1 && (node.end || node.children.size() > 1)) {
					sum++;
				}
			}
			return sum;
		}

	}

	static class Node {
		Map<Character, Node> children;
		boolean end;

		Node() {
			this.children = new HashMap<Character, Node>();
			end = false;
		}
	}
}