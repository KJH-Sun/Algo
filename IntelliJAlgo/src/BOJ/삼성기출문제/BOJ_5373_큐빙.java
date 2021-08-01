package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

/*
 극한의 시뮬레이션 문제.
 1. U, D, F, B, L, R을 상수로 선언
 2. 큐브의 각 면을 2차원 배열로 선언 cube[6][3][3]
 3. 큐브에 초기값 세팅
 4. 명령에 따라 큐브 돌리기
 5. 큐브를 돌릴 때, 면의 움직임을 일반화할 수 있다.
	5-1. 돌리는 대상 면(들어온 명령이 R이면, 오른쪽 면)을 반시계 방향으로 회전시킨다.
	5-2. 돌아가는 면 기준 가장 앞에 있는 면을 백업한다
	5-3. 면을 돌린다.
 6. 명령을 모두 수행하고, 윗면 출력
 */

public class BOJ_5373_큐빙 {
	static Function<String, Integer> stoi = Integer::parseInt;
	static final int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;
	static int T, N, cnt;
	static char[][][] cube;
	static char[] colors = new char[] { 'w', 'y', 'r', 'o', 'g', 'b' };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = stoi.apply(br.readLine());
		for (int i = 0; i < T; i++) {
			init();
			N = stoi.apply(br.readLine());
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				turn(st.nextToken());
			}
			printUp();
		}
	}

	private static void printUp() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(cube[U][j][2 - i]);
			}
			System.out.println();
		}
	}

	static void init() {
		cube = new char[6][3][3];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					cube[i][j][k] = colors[i];
				}
			}
		}
	}

	static void alter(int f, int u, int l, int d, int r, boolean clk) { // (u,l,d,r 순서는 상관없음, 움직여지는 순서만 맞으면 됨)
		char[][] tmp = new char[3][3];
		char[] tmp2 = new char[3];

		if (clk) {
			for (int i = 0; i < 3; ++i) // 돌리는 대상 면을 반시계방향으로 돌림
				for (int j = 0; j < 3; ++j) {
					tmp[i][j] = cube[f][2 - j][i];
				}
			for (int i = 0; i < 3; ++i)
				tmp2[i] = cube[u][i][0];
			for (int i = 0; i < 3; ++i)
				cube[u][i][0] = cube[l][0][2 - i];
			for (int i = 0; i < 3; ++i)
				cube[l][0][2 - i] = cube[d][2][i];
			for (int i = 0; i < 3; ++i)
				cube[d][2][i] = cube[r][2 - i][2];
			for (int i = 0; i < 3; ++i)
				cube[r][2 - i][2] = tmp2[i];
		} else {
			for (int i = 0; i < 3; ++i) // 돌리는 대상 면을 반시계방향으로 돌림
				for (int j = 0; j < 3; ++j) {
					tmp[i][j] = cube[f][j][2 - i];
				}
			for (int i = 0; i < 3; ++i) // 돌아가는 면 기준 가장 앞에 있는 (u)를 백업한다.
				tmp2[i] = cube[u][i][0];
			for (int i = 0; i < 3; ++i) //
				cube[u][i][0] = cube[r][2 - i][2];
			for (int i = 0; i < 3; ++i)
				cube[r][2 - i][2] = cube[d][2][i];
			for (int i = 0; i < 3; ++i)
				cube[d][2][i] = cube[l][0][2 - i];
			for (int i = 0; i < 3; ++i)
				cube[l][0][2 - i] = tmp2[i];
		}
		cube[f] = tmp;
	}

	static void turn(String s) {
		boolean flag = s.charAt(1) == '+';

		switch (s.charAt(0)) {
		case 'U':
			alter(U, L, F, R, B, flag);
			break;
		case 'D':
			alter(D, B, R, F, L, flag);
			break;
		case 'F':
			alter(F, U, L, D, R, flag);
			break;
		case 'B':
			alter(B, R, D, L, U, flag);
			break;
		case 'L':
			alter(L, F, U, B, D, flag);
			break;
		case 'R':
			alter(R, D, B, U, F, flag);
			break;
		}

	}
}