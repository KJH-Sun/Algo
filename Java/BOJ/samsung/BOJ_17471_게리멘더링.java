package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17471_게리멘더링 {
	static int N, min = Integer.MAX_VALUE;
	static int[] popus;
	static int[][] road;
	static int[] area;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		popus = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			popus[i] = stoi(st.nextToken());
		}
		road = new int[N + 1][N + 1];
		area = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = stoi(st.nextToken());
			for (int j = 1; j <= cnt; j++) {
				road[i][stoi(st.nextToken())] = 1;
			}
		}
		solve(1);
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);

	}

	private static void solve(int pos) { // 선택 비선택
		if (pos == N + 1) { // 끝까지 왔을때
			visit = new boolean[N + 1];
			int rs = 0;
			for (int i = 1; i <= N; i++) {
				if (!visit[i]) {
					checkArea(i, area[i]);
					rs++;
				}
			}
			if (rs == 2) {
				int area1 = 0, area2 = 0;
				for (int i = 1; i <= N; i++) {
					if (area[i] == 1) {
						area1 += popus[i];
					} else {
						area2 += popus[i];
					}
				}
				if (min > Math.abs(area1 - area2))
					min = Math.abs(area1 - area2);
			}
			return;
		}

		area[pos] = 1;
		solve(pos + 1);

		area[pos] = 2;
		solve(pos + 1);
	}

	private static void checkArea(int index, int num) {
		// TODO Auto-generated method stub
		visit[index] = true;
		for (int i = 1; i <= N; i++) {
			if (road[index][i] == 1 && !visit[i] && area[i] == num)
				checkArea(i, num);
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
