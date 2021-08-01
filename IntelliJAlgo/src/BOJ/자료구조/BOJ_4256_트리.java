import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 전위순회의 결과의 가장 앞쪽에 있는 노드를 통해서 루트 노드를 알 수 있다. 그리고 그 루트노드를 기준점으로
 * 중위순회 문자열을 분할하면, 왼쪽 노드와 오른쪽 노드를 분할할 수 있다. 다시 분할한 노드들 중에서 전위순회 문자열에서 가장
 * 앞에 있는 노드를 고르면 그것이 루트노드이며, 또 그것을 기준으로 분할하는 식으로 계속 분할한다.
 * 마지막에 재귀로 나오면서 각자의 연산시에 나눈 기준 루트노드를 출력하면서 나오면 정답
 * 
 */

public class BOJ_4256_트리 {
	static int T, N;
	static int[] pre;
	static int[] in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = stoi(br.readLine());
			st = new StringTokenizer(br.readLine());
			pre = new int[N];
			in = new int[N];
			for (int i = 0; i < N; i++) {
				pre[i] = stoi(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				in[i] = stoi(st.nextToken());
			}
			postOrder(0, N, 0);
			System.out.println();
		}
	}

	private static void postOrder(int sIdx, int eIdx, int rootIdx) { // s랑 e는 in 배열에서의 시작과 끝 인덱스 기준, root는 pre의 index
		for (int i = sIdx; i < eIdx; i++) {
			if (in[i] == pre[rootIdx]) {
				postOrder(sIdx, i, rootIdx + 1);
				postOrder(i + 1, eIdx, rootIdx + i - sIdx + 1);
				System.out.print(pre[rootIdx] + " ");
			}
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
