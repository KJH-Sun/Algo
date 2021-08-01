import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_1592_영식이와친구들 {
	static int N, M, L;
	static Deque<Friend> deque = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		L = stoi(st.nextToken());

		for (int i = 1; i <= N; i++) {
			deque.add(new Friend(i, 1));
		}
		int res = 0;
		while (deque.peek().cnt < M) {
			res++;
			if (deque.peek().cnt % 2 == 1) {
				Friend f = deque.poll();
				f.cnt++;
				deque.addLast(f);
				for (int i = 0; i < L - 1; i++) {
					deque.addLast(deque.poll());
				}
			} else {
				Friend f = deque.poll();
				f.cnt++;
				deque.addFirst(f);
				for (int i = 0; i < L; i++) {
					deque.addFirst(deque.pollLast());
				}
			}
		}
		

		System.out.println(res);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static class Friend {
		int idx;
		int cnt;

		Friend(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
	}

}
