import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1991_트리순회 {
	static int N;
	static Map<String, Node> tree = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		StringTokenizer st;
		Tree t = new Tree();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			t.add(st.nextToken(), st.nextToken(), st.nextToken());
		}
		t.preorder(t.root);
		System.out.println();
		t.inorder(t.root);
		System.out.println();
		t.postorder(t.root);
		System.out.println();
	}

	static class Node {
		String data;
		Node left;
		Node right;

		Node(String data) {
			this.data = data;
		}
	}

	static class Tree {
		Node root;

		public void add(String data, String leftData, String rightData) {
			if (root == null) {
				if (!data.equals(".")) {
					root = new Node(data);
				}
				if (!leftData.equals(".")) {
					root.left = new Node(leftData);
				}
				if (!rightData.equals(".")) {
					root.right = new Node(rightData);
				}
			} else {
				search(root, data, leftData, rightData);
			}

		}

		private void search(Node root, String data, String leftData, String rightData) {
			if (root == null) {
				return; // 탐색실패시
			} else if (root.data.equals(data)) {
				if (!leftData.equals(".")) {
					root.left = new Node(leftData);
				}
				if (!rightData.equals(".")) {
					root.right = new Node(rightData);
				}
			} else {
				search(root.left, data, leftData, rightData);
				search(root.right, data, leftData, rightData);
			}
		}

		public void preorder(Node root) {
			System.out.print(root.data);
			if (root.left != null) {
				preorder(root.left);
			}
			if (root.right != null) {
				preorder(root.right);
			}

		}

		public void inorder(Node root) {
			if (root.left != null) {
				inorder(root.left);
			}
			System.out.print(root.data);
			if (root.right != null) {
				inorder(root.right);
			}
		}

		public void postorder(Node root) {
			if (root.left != null) {
				postorder(root.left);
			}
			if (root.right != null) {
				postorder(root.right);
			}
			System.out.print(root.data);
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
