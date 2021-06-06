import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class 크레인인형뽑기게임 {
	static Queue<Integer> que[];
	static Stack<Integer> basket = new Stack<>();

	public static void main(String[] args) throws IOException {
		int[][] board = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 },
				{ 3, 5, 1, 3, 1 } };
		int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };
		System.out.println(solution(board, moves));
	}

	public static int solution(int[][] board, int[] moves) {
		int answer = 0;
		que = new ArrayDeque[board[0].length];
		for (int i = 0; i < board[0].length; i++) {
			que[i] = new ArrayDeque<Integer>();
		}
		for (int i = 0; i < board[0].length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[j][i] != 0) {
					que[i].offer(board[j][i]);
				}
			}
		}
		for (int i = 0; i < moves.length; i++) {
			if (que[moves[i] - 1].isEmpty()) {
				continue;
			}
			int now = que[moves[i] - 1].poll();
			if (basket.isEmpty() || basket.peek() != now) {
				basket.add(now);
			} else {
				int cnt = 1;
				while (!basket.isEmpty()) {
					if (basket.peek() != now) {
						break;
					}
					basket.pop();
					cnt++;
				}
				answer += cnt;
			}
		}

		return answer;
	}

}
