import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 
 * 분할정복 풀이법 
 *
 * div(1,N)은 1부터 N까지 중에서 가장 낮은 값(min) * width 값을 max와 비교하여 크면 넣어주는 메서드이다.
 * 그 이후, 가장 작은 값의 인덱스를 저장해뒀다가
 * div(1, index-1) / div(index+1, N)을 다시 재귀호출하는 식으로 전체를 탐색한다
 * 최종적으로 start와 end가 같아지면 자기 자신을 max와 비교하고 return한다.
 * 
 *
 */

public class BOJ_1725_히스토그램_분할정복 {
	static int N;
	static int[] heightArr;
	static Stack<Integer> stack = new Stack<>();
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		heightArr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			heightArr[i] = stoi(br.readLine());
		}
		div(1, N);
		System.out.println(max);

	}

	private static void div(int start, int end) {
		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int i = start; i <= end; i++) {
			if (min > heightArr[i]) {
				min = heightArr[i];
				index = i;
			}
		}
		if (start != end && start != index && end != index) {
			max = Math.max(max, min * (end - start + 1));
			div(start, index - 1);
			div(index + 1, end);
			return;
		} else if (start == index && start != end) {
			max = Math.max(max, min * (end - start + 1));
			div(index + 1, end);
			div(index, index);
			return;
		} else if (end == index && start != end) {
			max = Math.max(max, min * (end - start + 1));
			div(start, index - 1);
			div(index, index);
			return;
		} else {
			max = Math.max(max, heightArr[start]);
			return;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
