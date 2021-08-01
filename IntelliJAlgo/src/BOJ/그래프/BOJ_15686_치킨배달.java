import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;



public class BOJ_15686_치킨배달 {
	static int N, M;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] field;
	static int min = Integer.MAX_VALUE;
	static Queue<Node> que = new LinkedList<>();
	static List<Node> chi = new ArrayList<>();
	static List<Node> house = new ArrayList<>();
	static Stack<Node> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken()); // 지도크기
		M = stoi(st.nextToken()); // 살려둘 치킨집 개수
		field = new int[N][N]; // 0 빈칸 1 집 2 치킨집
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				field[i][j] = stoi(st.nextToken());
				if (field[i][j] == 1) {
					house.add(new Node(i, j, 0));
				}
				if (field[i][j] == 2) {
					chi.add(new Node(i, j, 0));
				}
			}
		}
		comb(0);
		System.out.println(min);
	}

	private static void comb(int pos) {
		if (pos == chi.size()) {
			if (stack.size() == M) {
				min = Math.min(min, solve());
				return;	
			}
			return;
		}
		stack.push(chi.get(pos));
		comb(pos + 1); // 선택
		stack.pop();
		comb(pos + 1); // 비선택
	}

	private static int solve() {
		int sum = 0;
		for (Node n : house) {
			int minDis = Integer.MAX_VALUE;
			for (Node cn : stack) {
				minDis = Math.min(minDis, Math.abs(n.x - cn.x) + Math.abs(n.y - cn.y));
			}
			sum += minDis;
		}
		return sum;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	static class Node {
		int x;
		int y;
		int dist;

		Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}
