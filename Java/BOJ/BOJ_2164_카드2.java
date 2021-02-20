
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2164_카드2 {
	static int N;
	static int[][] field;
	static Queue<Integer> que = new LinkedList<>();
	static StringBuffer sb = new StringBuffer();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		for (int i = 1; i <= N; i++) {
			que.offer(i);
		}
		while (que.size() > 1) {
			que.poll();
			que.offer(que.poll());
		}
		System.out.println(que.peek());
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
