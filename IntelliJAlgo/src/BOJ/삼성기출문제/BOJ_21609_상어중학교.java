package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

/*
 * 역시 시뮬레이션 문제지만, 생각보다 까다로운 포인트가 몇개 있다.
 * 첫번째로는 블록 그룹을 지정하는 방식이다.
 * 방문처리를 함에 있어서 무지개 블록은 방문처리에서 제외해야했는데, 여러가지 구현의 방식이 있겠지만 나는 3차원 boolean 배열을 사용하는 방식으로 구현했다.
 * 둘째로는 중력이 적용되는 케이스에서, 검은색 블록은 중력의 영향을 받지않는다는 것이다.
 * 이것도 생각보다 구현을 까다롭게 만드는 요소였다.
 * 이 포인트들을 생각한다면, 나머지는 문제에서 주어진 순서대로 구현만 올바르게 하면 풀리는 문제이다.
 *
 * 
 */

public class BOJ_21609_상어중학교 {
	static int N, M, sbx, sby, blockCnt, rainBowCnt, answer;
	static int[][] map, temp;
	static boolean[][][] visit;
	static List<Block> blocks;
	static Queue<Integer> q;

	static int[][] dir = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		map = new int[N][N];
		temp = new int[N][N];
		visit = new boolean[6][N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				map[i][j] = stoi(st.nextToken());
			}
		}

		blocks = new ArrayList<>();
		q = new ArrayDeque<>();

		while (true) {
			findBlockGroup();
			if (blocks.isEmpty())
				break;
			Collections.sort(blocks);
			Block standard = blocks.get(0);
			answer += Math.pow(standard.cnt, 2);
			blocks.clear();
			remove(standard);
			gravity();
			rotate();
			gravity();
			init();
		}
		System.out.println(answer);
	}

	private static void findBlockGroup() {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (map[i][j] <= 0 || visit[map[i][j]][i][j]) {
					continue;
				}
				sbx = i;
				sby = j;
				blockCnt = 0;
				rainBowCnt = 0;
				dfs(i, j, map[i][j]);
				if (blockCnt + rainBowCnt >= 2) {
					blocks.add(new Block(sbx, sby, blockCnt, rainBowCnt));
				}
			}
		}
	}

	public static void init() {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				for (int k = 1; k <= M; ++k) {
					visit[k][i][j] = false;
				}
			}
		}
	}

	public static void dfs(int x, int y, int v) {
		blockCnt++;
		visit[v][x][y] = true;
		
		// 무지개 블록인 경우 카운트 증가 , 일반 블록인 경우 기준블록과 행,열 비교해서 기준블록을 변경할지 말지 결정
		if (map[x][y] == 0) {
			rainBowCnt++;
		} else {
			if (x <= sbx) {
				if (x < sbx) {
					sbx = x;
					sby = y;
				} else {
					if (y < sby) {
						sbx = x;
						sby = y;
					}
				}
			}
		}

		for (int d = 0; d < 4; ++d) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];

			if (ny < 0 || nx < 0 || ny >= N || nx >= N)
				continue;
			if (map[nx][ny] <= -1 || visit[v][nx][ny])
				continue;
			if (map[nx][ny] == 0 || map[nx][ny] == v) {
				dfs(nx, ny, v);
			}
		}
	}

	public static void remove(Block node) {
		int v = map[node.x][node.y];

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				visit[v][i][j] = false;
			}
		}

		dfs(node.x, node.y, v);

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (visit[v][i][j]) {
					map[i][j] = -2;
				}
			}
		}
	}

	public static void gravity() {
		for (int j = 0; j < N; ++j) {
			int x = N - 1;
			for (int i = N - 1; i >= 0; --i) {
				if (map[i][j] == -1) {
					while (!q.isEmpty()) {
						map[x--][j] = q.poll();
					}
					while (x > i) {
						map[x--][j] = -2;
					}
					x--;
				} else {
					if (map[i][j] != -2) {
						q.offer(map[i][j]);
					}
				}
			}

			while (!q.isEmpty()) {
				map[x--][j] = q.poll();
			}
			while (x >= 0) {
				map[x--][j] = -2;
			}
		}
	}

	public static void rotate() {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				temp[N - j - 1][i] = map[i][j];
			}
		}

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				map[i][j] = temp[i][j];
			}
		}
	}

	static class Block implements Comparable<Block> {
		int x, y, cnt, rainBowCnt;

		public Block(int x, int y, int cnt, int rainBowCnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.rainBowCnt = rainBowCnt;
		}

		@Override
		public int compareTo(Block o) {
			if (this.cnt == o.cnt) {
				if (this.rainBowCnt == o.rainBowCnt) {
					if (this.x == o.x) {
						return o.y - this.y;
					}
					return o.x - this.x;
				}
				return o.rainBowCnt - this.rainBowCnt;
			}
			return o.cnt - this.cnt;
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}