import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class ref implements Comparable<ref> {
	int minT;
	int maxT;

	ref(int minT, int maxT) {
		this.maxT = maxT;
		this.minT = minT;
	}

	@Override
	public int compareTo(ref o) {
		return this.maxT <= o.maxT ? -1 : 1;
	}
}

public class 정올_1829_냉장고 {
	static int N, cx, cy;
	static int max;
	static int cnt;
	static List<ref> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		StringTokenizer st;
		for (int tc = 0; tc < N; tc++) {
			st = new StringTokenizer(br.readLine());
			cx = stoi(st.nextToken());
			cy = stoi(st.nextToken());
			list.add(new ref(cx, cy));
		}
		Collections.sort(list);
		for (ref r : list) {
			System.out.println(r.minT + " " + r.maxT);
		}
//		Collections.sort(list, (a, b) -> a.maxT <= b.maxT ? -1 : 1);
//		list.get(0).maxT = max;
//		cnt = 1;
//		for (int i = 0; i < list.size(); i++) {
//			if (max >= list.get(i).minT) {
//				continue;
//			} else {
//				cnt++;
//				max = list.get(i).maxT;
//			}
//		}
//
//		System.out.println(cnt);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
