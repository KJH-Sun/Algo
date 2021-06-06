import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;



public class BOJ_11650_좌표정렬하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		PriorityQueue<Node> pQ = new PriorityQueue<>();
		int N = stoi(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pQ.add(new Node(stoi(st.nextToken()), stoi(st.nextToken())));
		}
		while (!pQ.isEmpty()) {
			Node n = pQ.poll();
			System.out.println(n.x + " " + n.y);
		}
	}

	static class Node implements Comparable<Node> {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			int diff = this.x - o.x;
			return diff == 0 ? this.y - o.y : diff;
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
