import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1507_궁금한민호 {
	static int N;
	static int[][] dist;
	static int[][] road;
	static final int INF = (int) 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		N = stoi(br.readLine());
		StringTokenizer st;
		dist = new int[N][N];
		road = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				dist[i][j] = stoi(st.nextToken());
			}
		}
		floyd();
	}

	private static void floyd() {
		deepcopy(dist, road);
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (k == i) {
					continue;
				}
				for (int j = 0; j < N; j++) {
					if (k == j || i == j) {
						continue;
					}
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						System.out.println(-1);
						System.exit(0);
					}
					if (dist[i][j] == dist[i][k] + dist[k][j]) {
						road[i][j] = INF;
					}

				}
			}
		}
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (road[i][j] != INF) {
					sum += road[i][j];
				}
			}
		}
		System.out.println(sum/2);
	}

	private static void deepcopy(int[][] src, int[][] dest) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dest[i][j] = src[i][j];
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
