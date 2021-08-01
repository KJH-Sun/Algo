import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11399_ATM {
	static int N;
	static Queue<Integer> que = new PriorityQueue<>();
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			que.offer(stoi(st.nextToken()));
		}
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += que.poll();
			res += sum;
		}
		System.out.println(res);

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
