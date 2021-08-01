import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 문자열 N개를 입력받아 set에 저장하고
 * 그 이후로 입력받는 문자열마다 대조하여 포함여부 확인
 * 
 */

public class BOJ_14425_문자열집합 {

	static Set<String> p = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cnt = 0;
		int N = stoi(st.nextToken());
		int M = stoi(st.nextToken());
		for (int i = 0; i < N; i++) {
			p.add(br.readLine());
		}
		for (int i = 0; i < M; i++) {
			if (p.contains(br.readLine())) {
				cnt++;
			}
		}
		System.out.println(cnt);

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
