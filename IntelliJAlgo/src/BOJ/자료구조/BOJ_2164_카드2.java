
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 큐로 입력받은 N까지의 카드 번호를 넣은 뒤, 큐에서 하나를 poll하고 다음 poll한걸 다시 offer한다. 
 * 그걸 que.size가 1이 될때까지 반복한 이후 마지막 남은 카드를 출력
 */
public class BOJ_2164_카드2 {
	static int N;
	static Queue<Integer> que = new LinkedList<>();
	static StringBuffer sb = new StringBuffer();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		for (int i = 1; i <= N; i++) {
			que.offer(i);
		}
		while (que.size() > 1) {
			que.poll();
			que.offer(que.poll());
		}
		System.out.println(que.peek());
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
