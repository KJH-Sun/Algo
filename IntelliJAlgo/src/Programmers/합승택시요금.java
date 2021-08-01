import java.io.IOException;
import java.util.Arrays;

public class 합승택시요금 {
	static int[][] map;
	static final int INF = (int) 1e9;

	public static void main(String[] args) throws IOException {
		System.out.println(solution(6, 4, 5, 6, new int[][] {{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}}));
	}

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(map[i], INF);
			map[i][i] = 0;
		}
		for (int i = 0; i < fares.length; i++) {
			map[fares[i][0]][fares[i][1]] = fares[i][2];
			map[fares[i][1]][fares[i][0]] = fares[i][2];
		}
		floyd();
		int answer = map[s][a] + map[s][b];
		for (int mid = 1; mid <= n; mid++) {
			if (map[s][mid] == INF || map[mid][a] == INF || map[mid][b] == INF) {
				continue;
			}
			answer = Math.min(answer, map[s][mid] + map[mid][a] + map[mid][b]);
		}

		return answer;
	}

	private static void floyd() {
		for (int k = 1; k < map.length; k++) {
			for (int i = 1; i < map.length; i++) {
				if (i == k) {
					continue;
				}
				for (int j = 1; j < map.length; j++) {
					if (i == j || k == j) {
						continue;
					}
					if (map[i][k] == INF || map[k][j] == INF) {
						continue;
					}
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}

				}
			}
		}

	}

}
