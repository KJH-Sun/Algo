import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 스택 풀이법
 * 스택의 양쪽 끝에 0을 넣어두고, stack의 top보다 숫자가 크면 stack에 넣고 낮으면, stack에서 팝하면서 그 높이 * 지금 현재 보고있는 i 를 Math.max하여 넣음
 * 끝에 0을 넣어줘야 가장 마지막 요소를 체크해줄 수 있어서 넣었음
 * 첫 0은 넣지않으면 2424 
 * 
 * 7 3 2 1 3 2 1 1
	answer = 7
	wrong = 5
    
	5 5 3 0 3 2
	answer = 6
	wrong = 5
 */

public class BOJ_1725_히스토그램Stack {
	static int N;
	static int[] heightArr;
	static Stack<Integer> stack = new Stack<>();
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		heightArr = new int[N + 2];
		for (int i = 1; i <= N; i++) {
			heightArr[i] = stoi(br.readLine());
		}
		stack.push(0);
		for (int i = 1; i <= N+1; i++) {
			while (!stack.isEmpty() && heightArr[stack.peek()] > heightArr[i]) {
				int h = heightArr[stack.peek()];
				stack.pop();
				int w = i - stack.peek()-1;
				max = Math.max(max, h * w);
			}
			stack.push(i);
		}
		System.out.println(max);

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
