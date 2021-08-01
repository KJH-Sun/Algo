package string;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;

/*
 * 알파벳 길이만큼의 visit 배열을 만든다음,
 * anta tica -> a, n, t, i, c 이 다섯글자는 무조건 들어가야하므로, 이들에 해당하는 visit을 true로 바꿔준다.
 * 1. 만약 가르칠 수 있는 단어가 5 이하라면 읽을 수 있는 단어가 없으므로 0을 출력하도록 예외처리
 * 2. a, n, t, i, c visit true 처리
 * 3. 조합으로 읽을 수 있는 만큼의 글자 처리하고, 갯수 확인
 * 
 * 
 */


public class BOJ_1062_가르침 {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int N, K;
	static List<String> words = new ArrayList<>();
	static boolean[] used = new boolean[26];
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi.apply(st.nextToken());
		K = stoi.apply(st.nextToken());
		if (K < 5) {
			System.out.println(0);
			System.exit(0);
		} else if (K == 26) {
			System.out.println(N);
			System.exit(0);
		}
		alpaInit();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			words.add(s.substring(4, s.length() - 4));
		}
		solve(0, 0);
		System.out.println(max);
	}

	private static void alpaInit() {
		K -= 5;
		used['a' - 'a'] = true;
		used['n' - 'a'] = true;
		used['t' - 'a'] = true;
		used['i' - 'a'] = true;
		used['c' - 'a'] = true;
	}

	private static void solve(int prev, int resCnt) {
		if (resCnt == K) {
			int cnt = 0;
			outer: for (String word : words) {
				for (int i = 0; i < word.length(); i++) {
					int c = word.charAt(i) - 'a';
					if (!used[c]) {
						continue outer;
					}
				}
				cnt++;
			}
			max = Math.max(cnt, max);
			return;
		}

		for (int i = prev; i < 26; i++) {
			if (!used[i]) {
				used[i] = true;
				solve(i, resCnt + 1);
				used[i] = false;
			}
		}
	}

}