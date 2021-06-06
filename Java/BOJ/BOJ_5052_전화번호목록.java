import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;


public class BOJ_5052_전화번호목록 {
	static List<String> str = new ArrayList<>();
	static Set<String> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = stoi(br.readLine());
		loop: for (int tc = 1; tc <= T; tc++) {
			str.clear();
			set.clear();
			int n = stoi(br.readLine());
			for (int i = 0; i < n; i++) {
				str.add(br.readLine());
			}
			str.sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o2.length() - o1.length();
				}
			});
			for (String s : str) {
				if (!set.isEmpty()) {
					if (set.contains(s)) {
						System.out.println("NO");
						continue loop;
					}
				}
				for (int i = 0; i < s.length(); i++) {
					set.add(s.substring(0, i));
				}
			}

			System.out.println("YES");
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
