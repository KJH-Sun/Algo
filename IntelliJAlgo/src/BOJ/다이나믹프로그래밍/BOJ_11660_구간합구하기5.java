import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 숫자를 받을때 행단위로 미리 sum을 만들어두고, 구해야하는 xy좌표에 따라서 행단위 계산하여 출력
 * 
 * 원래 해야하는 방식
 * 이차원 배열은 두개 잡고, sum 배열은 0,0을 기점으로 i j까지의 합
 * 
 * sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] +arr[i][j]
 *
 * sum[x2][y2] - sum[x1-1][y2] -sum[x2][y1-1] +sum[x1-1][y1-1]
 * 
 * 다시 풀어보자!
 * 
 */

public class BOJ_11660_구간합구하기5 {
	static int N, M;
	static int[][] sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		sum = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				sum[i][j] += stoi(st.nextToken()) + sum[i][j - 1];
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = stoi(st.nextToken());
			int y1 = stoi(st.nextToken());
			int x2 = stoi(st.nextToken());
			int y2 = stoi(st.nextToken());
			int res = 0;
			for (int k = x1; k <= x2; k++) {
				res += (sum[k][y2] - sum[k][y1 - 1]);
			}
			sb.append(res + "\n");

		}
		System.out.println(sb);

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
