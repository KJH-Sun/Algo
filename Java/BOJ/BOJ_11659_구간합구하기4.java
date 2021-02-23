import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11659_구간합구하기4 {
	static int N, M;
	static int[] numArr;
	static int[] sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		sum = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			sum[i] += stoi(st.nextToken()) + sum[i - 1];
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken());
			int y = stoi(st.nextToken());
			sb.append(sum[y] - sum[x - 1] + "\n");
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
