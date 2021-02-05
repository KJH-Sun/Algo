import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_1225_암호생성기 {
	static Queue<Integer> que = new LinkedList<>();
	static int[] numArr = new int[8];

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	private static void createPW() {
		loop: while (true) {
			for (int i = 1; i <= 5; i++) {
				int first = que.poll();
				if (first - i <= 0) {
					que.offer(0);
					break loop;
				}
				que.offer(first - i);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				que.offer(stoi(st.nextToken()));
			}
			createPW();
			sb.append("#" + tc + " ");
			for (int i : que) {
				sb.append(i + " ");
			}
			sb.append("\n");
			que.clear();
		}
		System.out.println(sb);

	}

}
