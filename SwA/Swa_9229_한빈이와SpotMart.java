import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Swa_9229_한빈이와SpotMart {
	static LinkedList<Integer> list = new LinkedList<>();
	static int T, N, M;
	static int[] wei;
	static boolean[] buy;
	static int max;

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			max = -1;
			wei = new int[N];
			buy = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				wei[i] = stoi(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				int sum = 0;
				buy[i] = true;
				sum += wei[i];
				for (int j = 0; j < N; j++) {
					if (!buy[j]) {
						sum += wei[j];
						if (sum <= M) {
							max = Math.max(max, sum);
						}
						sum -= wei[j];
					}
				}
			}
			sb.append("#" + tc + " " + max);
			sb.append("\n");
			list.clear();
		}
		System.out.println(sb);

	}

}
