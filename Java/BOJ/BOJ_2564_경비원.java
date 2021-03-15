import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2564_경비원 {
	static int W, H, N;
	static int[][] map;
	static List<Node> shoplist = new ArrayList<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	
	/*
	 * 지도 map 배열을 만든다음에, 입력받은 값에 따라 map에 상점들의 위치와 동구의 위치를 표시해주고,
	 * 그 다음에 bfs로 맵의 가장자리로만 bfs를 돌도록해서 최단거리를 구한 이후
	 * 상점들의 위치에 적힌 숫자들을 더해서 출력(1로 시작했기때문에 상점 숫자만큼 빼서 출력)
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = stoi(st.nextToken());
		H = stoi(st.nextToken());

		map = new int[H + 1][W + 1];
		N = stoi(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			createShop(stoi(st.nextToken()), stoi(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		bfs(stoi(st.nextToken()), stoi(st.nextToken()));

		int sum = 0;
		for (Node n : shoplist) {
			sum += map[n.x][n.y];
		}
		System.out.println(sum - N);

	}

	private static void bfs(int fcp, int dis) {
		Queue<Node> que = new LinkedList<>();
		switch (fcp) {
		case 1:
		case 2:
			que.offer(new Node(H * (fcp - 1), dis));
			map[H * (fcp - 1)][dis] = 1;
			break;
		case 3:
		case 4:
			que.offer(new Node(dis, W * (fcp - 3)));
			map[dis][W * (fcp - 3)] = 1;
			break;
		}

		while (!que.isEmpty()) {
			Node n = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (0 <= nx && nx <= H && 0 <= ny && ny <= W) {
					if ((nx == 0 || nx == H || 0 == ny || ny == W) && map[nx][ny] == 0) {
						map[nx][ny] = map[n.x][n.y] + 1;
						que.offer(new Node(nx, ny));
					}
				}
			}
		}

	}

	private static void createShop(int fcp, int dis) {
		switch (fcp) {
		case 1:
		case 2:
			shoplist.add(new Node(H * (fcp - 1), dis));
			break;
		case 3:
		case 4:
			shoplist.add(new Node(dis, W * (fcp - 3)));
			break;
		}

	}

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
