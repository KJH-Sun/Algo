import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

public class BOJ_4358_생태학 {
	static Map<String, Integer> hg = new TreeMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 0;
		while (true) {
			String s = br.readLine();
			if (s == null || s.length() == 0) {
				break;
			}
			if (hg.containsKey(s)) {
				cnt++;
				hg.put(s, hg.get(s) + 1);
			} else {
				cnt++;
				hg.put(s, 1);
			}
		}
		for (String s : hg.keySet()) {
			System.out.printf("%s %.4f%n", s, hg.get(s)/(float)cnt*100);
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
