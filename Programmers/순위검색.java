import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 *  info 문자열이 주어졌을 때, 그 info 문자열로 만들 수 있는 모든 경우의 수의 문자열을 String으로 조합한 이후, 그것을 map에 모두 put해둔다.
 *  이후 query를 반복하여 찾고, 만약에 해당하는 곳이 있다면 수들 중에서 이분탐색으로 확인한다.
 */

public class 순위검색 {
	static Map<String, List<Integer>> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String[] query = { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };
		System.out.println(solution(info, query));
	}

	public static int[] solution(String[] info, String[] query) {
		int[] result = new int[query.length];
		StringTokenizer st;
		for (String s : info) {
			st = new StringTokenizer(s);
			String[] values = new String[4];
			values[0] = st.nextToken();
			values[1] = st.nextToken();
			values[2] = st.nextToken();
			values[3] = st.nextToken();
			int score = Integer.parseInt(st.nextToken());
			solve(0, values, score);
		}
		for (List<Integer> list : map.values()) {
			Collections.sort(list);
		}
		int index = 0;
		for (String s : query) {
			String[] values = new String[4];
			st = new StringTokenizer(s);
			for (int i = 0; i < 7; i++) {
				if (i % 2 != 0) {
					st.nextToken();
					continue;
				}
				values[i / 2] = st.nextToken();
			}
			int score = Integer.parseInt(st.nextToken());
			String str = values[0] + values[1] + values[2] + values[3];
			if (!map.containsKey(str)) {
				index++;
				continue;
			}
			List<Integer> list = map.get(str);
			int start = 0;
			int end = list.size() - 1;
			while (start <= end) {
				int mid = (start + end) / 2;
				if (list.get(mid) < score) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			}
			result[index++] += list.size() - start;
		}

		return result;
	}

	private static void solve(int pos, String[] values, int score) {
		if (pos == 4) {
			map.merge(values[0] + values[1] + values[2] + values[3], new ArrayList<Integer>(Arrays.asList(score)),
					(ori, init) -> {
						ori.add(score);
						return ori;
					});
			return;
		}
		String tmp = values[pos];
		solve(pos + 1, values, score);
		values[pos] = "-";
		solve(pos + 1, values, score);
		values[pos] = tmp;
	}

}
