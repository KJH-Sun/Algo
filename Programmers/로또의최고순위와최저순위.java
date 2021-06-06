import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class 로또의최고순위와최저순위 {

	public static void main(String[] args) throws IOException {
		System.out.println(solution(new int[] { 44, 1, 0, 0, 31, 25 }, new int[] { 31, 10, 45, 1, 6, 19 }));
	}

	public static int[] solution(int[] lottos, int[] win_nums) {
		int[] rank = new int[] { 6, 6, 5, 4, 3, 2, 1 };
		Set<Integer> win = new HashSet<>();
		int zero = 0;
		for (int i : win_nums) {
			win.add(i);
		}
		int low = 0;
		for (int l : lottos) {
			if (l == 0) {
				zero++;
				continue;
			}
			if (win.contains((Integer) l)) {
				low++;
			}
		}
		int[] answer = new int[2];
		answer[0] = rank[low + zero];
		answer[1] = rank[low];
		return answer;
	}
}
