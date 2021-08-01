import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_3499_퍼펙트셔플 {
	static Queue<String> first = new LinkedList<>();
	static Queue<String> second = new LinkedList<>();
	static int N;

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		int T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = stoi(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			int middle = 0;
			if (N % 2 == 0) {
				middle = N / 2;
			} else {
				middle = N / 2 + 1;
			}
			for (int i = 0; i < N; i++) {
				if (i < middle) {
					first.offer(st.nextToken());
				} else {
					second.offer(st.nextToken());
				}
			}

			sb.append("#" + tc + " ");
			for (int i = 0; i < N; i++) {
				if (i % 2 == 0) {
					sb.append(first.poll() + " ");
				} else {
					sb.append(second.poll() + " ");
				}
			}
			sb.append("\n");
			first.clear();
			second.clear();
		}
		System.out.println(sb);

	}

}