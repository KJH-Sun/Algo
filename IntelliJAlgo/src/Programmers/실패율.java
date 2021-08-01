import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 1. stages 배열을 N번 반복하면서 스테이지 번호보다 그 숫자가 크거나 같은 경우 total을 늘리고, 
 * 2. 같은 경우 challenger의 숫자를 1 증가시킨다.
 * 3. stages 배열의 순회가 완료되면, sList에 Stage객체를 계산하여 넣는다.
 * 4. 정렬하여 출력한다.
 * 
 */


public class 실패율 {

	public static void main(String[] args) throws IOException {
		int[] stages = { 4, 4, 4, 4, 4 };
		System.out.println(solution(4, stages));
	}

	public static int[] solution(int N, int[] stages) {
		List<Stage> sList = new ArrayList<>();
		for (int i = 1; i < N + 1; i++) {
			int challenger = 0;
			int total = 0;
			for (int s : stages) {
				if (i <= s) {
					total++;
				}
				if (i == s) {
					challenger++;
				}
			}
			if (total != 0) {
				sList.add(new Stage(i, (float)challenger / total));
			} else {
				sList.add(new Stage(i, 0));
			}
		}
		Collections.sort(sList);
		int[] answer = new int[N];
		int index = 0;
		for (Stage s : sList) {
			answer[index++] = s.idx;
		}
		
		return sList.stream().mapToInt(Stage::getIdx).toArray();
	}

	static class Stage implements Comparable<Stage> {
		int idx;
		float fail;

		public int getIdx() {
			return idx;
		}

		Stage(int idx, float fail) {
			this.idx = idx;
			this.fail = fail;
		}

		@Override
		public int compareTo(Stage o) {
			return Float.compare(o.fail, this.fail) != 0 ? Float.compare(o.fail, this.fail) : this.idx - o.idx;
		}

	}

}
