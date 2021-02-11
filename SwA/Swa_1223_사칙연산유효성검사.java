import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Swa_1223_사칙연산유효성검사 {
	static LinkedList<Integer> list = new LinkedList<>();
	static int N;
	static char[] cArr;
	static boolean[] buy;
	static int max;

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 1; tc <= 10; tc++) {
			N = stoi(br.readLine());
			cArr = new char[N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				st.nextToken();
				cArr[i] = st.nextToken().charAt(0);
			}
			int res = (solve(1) ? 1 : 0);
			System.out.println("#" + tc + " " + res);
		}

	}

	private static boolean solve(int i) {
		if ('0' <= cArr[i] && cArr[i] <= '9') {
			if ((i * 2 > N) && (i * 2 + 1 > N)) { // 자기가 마지막 노드인경우
				return true;
			} else {
				return false;
			}
		} else {
			return solve(i * 2) & solve(i * 2 + 1);
		}
	}

}
