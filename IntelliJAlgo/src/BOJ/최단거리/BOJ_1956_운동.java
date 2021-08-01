import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 플로이드 워셜인데 자기자신한테 돌아오는 케이스만 확인해서 최소값 출력하면 됩니다
 * 
 */

public class BOJ_1956_운동 {
	static final long INF = (long) 1e18;
	static int V, E;
	static long[][] map;
	static long min = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = stoi(st.nextToken());
		E = stoi(st.nextToken());
		map = new long[V + 1][V + 1];
		for (int i = 1; i <= V; i++) {
			Arrays.fill(map[i], INF);
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			map[stoi(st.nextToken())][stoi(st.nextToken())] = stoi(st.nextToken());
		}
		fw();
		for (int i = 1; i <= V; i++) {
			min = Math.min(map[i][i], min);
		}
		System.out.println(min == INF ? -1 : min);

	}

	private static void fw() {
		for (int k = 1; k <= V; k++) { // 경유지
			for (int i = 1; i <= V; i++) { // 출발지
				for (int j = 1; j <= V; j++) { // 목적지
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
