import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1074_Z {
	static int N, r, c;
	static PriorityQueue<Integer> pQ = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		r = stoi(st.nextToken());
		c = stoi(st.nextToken());
		System.out.println(div(r, c, N));
	}

	private static int div(int x, int y, int hw) {
		// n/2*사분면번호(0,1,2,3)
		// x y 가 둘다 짝수이면 자기 네모에서 첫번째 인수 , y만 홀수이면 첫번째 인수, x만 홀수이면 3번째 인수, x,y둘다 홀수이면 네번째
		// 인수
		if (hw == 1) {
			if (r % 2 == 0 && c % 2 == 0) {
				return 0;
			} else if (r % 2 == 0 && c % 2 == 1) {
				return 1;
			} else if (r % 2 == 1 && c % 2 == 0) {
				return 2;
			} else {
				return 3;
			}
		}
		
		int Boxhw = 1;
		for (int i = 0; i < hw - 1; i++) {
			Boxhw *= 2;
		}
		
		if (x < Boxhw && y < Boxhw) {
			return div(x, y, hw - 1);
		} else if (x < Boxhw && y >= Boxhw) {
			return div(x, y - Boxhw, hw - 1) + Boxhw * Boxhw;
		} else if (x >= Boxhw && y < Boxhw) {
			return div(x - Boxhw, y, hw - 1) + Boxhw * Boxhw * 2;
		} else {
			return div(x - Boxhw, y - Boxhw, hw - 1) + Boxhw * Boxhw * 3;
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
