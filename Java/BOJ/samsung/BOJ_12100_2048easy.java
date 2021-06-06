package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;



/*
 * 블록을 이동하는 경우의 수가 5이며, 맵의 크기가 크지않으므로 완탐으로 접근한다.
 * 1. 선택 비선택(조합)을 통해서 상하좌우 중 하나를 선택해 블록을 이동한다.
 * 2. 이 횟수가 5번이 되면, 가장 큰 숫자를 비교한 이후 리턴한다.
 */


public class BOJ_12100_2048easy {
	static int N, map[][];
	static int max = Integer.MIN_VALUE;

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
		solve(0);
		System.out.println(max);
	}

	private static void solve(int pos) {
		if (pos == 5) {
			checkMax();
			return;
		}
		int[][] tmp = deepCopy(map);
		for (int i = 0; i < 4; i++) {
			move(i);
			solve(pos + 1);
			map = deepCopy(tmp);
		}
	}

	public static void move(int dir) {
		switch (dir) {
		// 위로 몰기
		case 0:
			for (int i = 0; i < N; i++) {
				int index = 0;
				int block = 0;
				for (int j = 0; j < N; j++) {
					if (map[j][i] != 0) {
						if (block == map[j][i]) {
							map[index - 1][i] = block * 2;
							block = 0;
							map[j][i] = 0;
						} else {
							block = map[j][i];
							map[j][i] = 0;
							map[index][i] = block;
							index++;
						}
					}
				}
			}
			break;
		// 왼쪽으로 몰기
		case 1:
			for (int i = 0; i < N; i++) {
				int index = N - 1;
				int block = 0;
				for (int j = N - 1; j >= 0; j--) {
					if (map[j][i] != 0) {
						if (block == map[j][i]) {
							map[index + 1][i] = block * 2;
							block = 0;
							map[j][i] = 0;
						} else {
							block = map[j][i];
							map[j][i] = 0;
							map[index][i] = block;
							index--;
						}
					}
				}
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				int index = 0;
				int block = 0;
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0) {
						if (block == map[i][j]) {
							map[i][index - 1] = block * 2;
							block = 0;
							map[i][j] = 0;
						} else {
							block = map[i][j];
							map[i][j] = 0;
							map[i][index] = block;
							index++;
						}
					}
				}
			}
			break;
		// 오른쪽으로 몰기
		case 3:
			for (int i = 0; i < N; i++) {
				int index = N - 1;
				int block = 0;
				for (int j = N - 1; j >= 0; j--) {
					if (map[i][j] != 0) {
						if (block == map[i][j]) {
							map[i][index + 1] = block * 2;
							block = 0;
							map[i][j] = 0;
						} else {
							block = map[i][j];
							map[i][j] = 0;
							map[i][index] = block;
							index--;
						}
					}
				}
			}
			break;
		}
	}

	private static void checkMax() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, map[i][j]);
			}
		}
	}
	
	private static int[][] deepCopy(int[][] src) {
		int[][] result = new int[src.length][src[0].length];
		for (int i = 0; i < src.length; i++) {
			System.arraycopy(src[i], 0, result[i], 0, src[0].length);
		}
		return result;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
