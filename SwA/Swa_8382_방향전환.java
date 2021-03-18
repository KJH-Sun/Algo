import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_8382_방향전환 {
	static int T, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int x1 = stoi(st.nextToken());
			int y1 = stoi(st.nextToken());
			int x2 = stoi(st.nextToken());
			int y2 = stoi(st.nextToken());
			int res = 0;
			if (Math.abs((x2 - x1) - (y2 - y1)) <= 1) {
				res = Math.abs((x2 - x1) + (y2 - y1));
			} else {
				int nx = Math.abs((x2 - x1));
				int ny = Math.abs((y2 - y1));
				int start = (Math.min(nx, ny) * 2);
				int plus1 = (Math.max(nx, ny) - Math.min(nx, ny)) / 2;
				int plus2 = ((Math.max(nx, ny) - Math.min(nx, ny))/2) * 3;
				if ((Math.max(nx, ny) - Math.min(nx, ny)) % 2 == 0) {
					res += start + plus1 + plus2;
				} else {
					res += start + plus1+1 + ((Math.max(nx, ny) - Math.min(nx, ny))/2) * 3;
				}

			}
			System.out.printf("#%d %d\n", tc, res);
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
