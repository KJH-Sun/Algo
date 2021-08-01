import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 모든 정점에서 모든 정점으로 향하는 최단거리를 구하는 알고리즘
 * N개의 정점이 있다고 했을때 int[N][N] 배열이 만들어집니다.
 * map[0][3] 0->3번으로 가는 최단거리 
 * map[3][4] 3->4번으로 가는 최단거리 
 * 
 * for문을 세번 돌립니다 O(N^3) 
 * 
 * 다익스트라 (BFS) -> 한 정점을 기준으로 다른 모든 정점과의 최단거리
 * 0번 정점을 기준으로
 * 최단거리를 구한다
 * 0->3 이게 3인걸알아
 * 4->5로 가는 최단거리를 알수는 없다
 * 
 * 
 * 플로이드워셜 -> 모든 정점에서 모든 정점으로 향하는 최단거리
 * 0->3
 * 1->3
 * 2->3
 * 4->5
 * 
 */

public class BOJ_11404_플로이드 {
	static int N, M;
	static int[][] map;
	static final int INF = (int) 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		M = stoi(br.readLine());
		StringTokenizer st;
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(map[i], INF);
		}
		for (int i = 1; i <= N; i++) {
			map[i][i] = 0;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = stoi(st.nextToken());
			int e = stoi(st.nextToken());
			int w = stoi(st.nextToken());
			if (map[s][e] > w) {
				map[s][e] = w;
			}
		}
		// 입력값 받기
		floydWarshall();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j] == INF ? 0 + " " : map[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void floydWarshall() { // 가장 바깥 포문이 중간경유지
		// i(출발점), j(도착점) , k(경유지)
//		i -> j  // 10
//		map[i][j] = map[i][k] + map[k][j];
//		i -> k -> j
		// 5 + 3 = 8
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if(i==k) {
					continue;
				}
				for (int j = 0; j < N; j++) {
					if(i==j || k==j) {
						continue;
					}
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}

//		for (int k = 1; k <= N; k++) { // 경유지
//			for (int i = 1; i <= N; i++) { // 출발지
//				if (i == k) {
//					continue;
//				}
//				for (int j = 1; j <= N; j++) { // 도착지
//					if (j == k || j == i) {
//						continue;
//					}
//					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
//				}
//			}
//		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
