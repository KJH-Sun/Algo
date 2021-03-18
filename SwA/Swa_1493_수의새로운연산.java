import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swa_1493_수의새로운연산 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = stoi(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= N; tc++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken());
			int y = stoi(st.nextToken());
			sb.append("#" + tc + " " + plusNode(findNum(x), findNum(y)) + "\n");
		}
		System.out.println(sb);

	}

	private static int plusNode(Node a, Node b) { // 노드합산후 값 계산
		int nx = a.x + b.x;
		int ny = a.y + b.y;

		int value = 1;
		for (int i = 1; i < ny; i++) {
			value += i;
		}
		int nny = ny + 1;
		for (int i = 1; i < nx; i++) {
			value += nny++;
		}

		return value;
	}

	private static Node findNum(int value) {
		int n = 1;
		while (value > n * (n + 1) / 2) { // n번째 대각선에 value있음을 파악
			n++;
		}
		if (value == 1) { // 1,1은 예외처리
			n = 1;
		}
		int start = 1;
		for (int i = 1; i < n; i++) { // n번째 대각선의 어디에 숫자가 있는지 파악
			start += i;
		}
		int x = 1;
		int y = n;
		while (value != start) { // 좌표값 조정해서 리턴
			start++;
			x++;
			y--;
		}

		return new Node(x, y);

	}

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
