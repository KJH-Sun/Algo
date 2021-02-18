import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1780_종이의개수 {
	static int N;
	static int[][] map;
	static boolean success;
	static int[] dx = { -1, 0, 1 };
	static int[] res = new int[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = stoi(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		div(0, 0, N);
		for (int i : res) {
			System.out.println(i);
		}

	}

	private static int div(int x, int y, int side) {
		if (side == 1) {
			res[map[x][y] + 1]++;
			return -2;
		}
		if (side == 3) {
			List<Integer> paper = new ArrayList<>();
			HashSet<Integer> temp = new HashSet<>();
			for (int i = x; i < x + 3; i++) {
				for (int j = y; j < y + 3; j++) {
					paper.add(map[i][j]);
					temp.add(map[i][j]);
				}
			}
			if (temp.size() == 1) {
				for (int i : temp) {
					if (N == 3) {
						res[i + 1]++;
						return i;
					}
					return i;
				}
			} else {
				for (int i : paper) {
					res[i + 1]++;
				}
				return 3;
			}
		}

		int mid = side / 3;
		Set<Integer> temp = new HashSet<>();
		List<Integer> paper = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				paper.add(div(x + i * mid, y + j * mid, mid));
				temp.add(paper.get(paper.size() - 1));
			}
		}

		if (temp.size() == 1) {
			if (side != N) {
				for (int i : temp) {
					return i;
				}
			} else {
				if (paper.get(0) == 3) {
					return -2;
				}
				res[paper.get(0) + 1]++;
				return -2;
			}
		} else {
			for (int i : paper) {
				if (i == 3) {
					continue;
				}
				res[i + 1]++;
			}
			return 3;
		}
		return -2;

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
