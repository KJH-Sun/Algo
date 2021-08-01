import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1759_암호만들기 {
	static String co = "aeiou";
	static int L, C;
	static char[] cArr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = stoi(st.nextToken());
		C = stoi(st.nextToken());
		cArr = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			cArr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(cArr);
		solve(0, 0, 0, 0, "");
		System.out.println(sb);

	}

	private static void solve(int pos, int cnt, int mCnt, int jCnt, String tmp) {
		if (pos == C) {
			if (mCnt >= 1 && jCnt >= 2 && cnt == L) {
				sb.append(tmp + "\n");
			}
			return;
		}
		if (co.indexOf(cArr[pos]) < 0) {
			solve(pos + 1, cnt + 1, mCnt, jCnt + 1, tmp + cArr[pos]);
			solve(pos + 1, cnt, mCnt, jCnt, tmp);
		} else {
			solve(pos + 1, cnt + 1, mCnt + 1, jCnt, tmp + cArr[pos]);
			solve(pos + 1, cnt, mCnt, jCnt, tmp);
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
