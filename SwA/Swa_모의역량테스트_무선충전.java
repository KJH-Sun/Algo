import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swa_모의역량테스트_무선충전 {
	static List<AP> bc = new ArrayList<>();
	static int M, A;
	static Queue<Integer> user[] = new ArrayDeque[2];
	static int[] dx = new int[] { 0, 0, 1, 0, -1 };
	static int[] dy = new int[] { 0, -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		user[0] = new ArrayDeque<>();
		user[1] = new ArrayDeque<>();
		int T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = stoi(st.nextToken()); // 총 이동 시간
			A = stoi(st.nextToken()); // 배터리 개수
			user[0].add(0);
			user[1].add(0);
			st = new StringTokenizer(br.readLine()); // 사용자 A 동선
			while (st.hasMoreTokens()) {
				user[0].offer(stoi(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				user[1].offer(stoi(st.nextToken()));
			}
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				bc.add(new AP(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken())));
			}
			sb.append("#" + tc + " " + solve() + "\n");
			bc.clear();
		}

		System.out.println(sb);
	}

	private static int solve() {
		AP users[] = new AP[2];
		users[0] = new AP(1, 1, 0, 0);
		users[1] = new AP(10, 10, 0, 0);
		PriorityQueue<Node> pQ[] = new PriorityQueue[2];
		pQ[0] = new PriorityQueue<>(Collections.reverseOrder());
		pQ[1] = new PriorityQueue<>(Collections.reverseOrder());
		int sum = 0;
		for (int i = 0; i <= M; i++) {
			for (int j = 0; j < 2; j++) {
				int n = user[j].poll();
				users[j] = new AP(users[j].x + dx[n], users[j].y + dy[n], 0, 0);
				for (int k = 0; k < bc.size(); k++) {
					if (bc.get(k).equals(users[j])) {
						pQ[j].add(new Node(k, bc.get(k).p));
					}
				}
			}
			if (!pQ[0].isEmpty() && !pQ[1].isEmpty()) {
				Node p1 = pQ[0].poll();
				Node p2 = pQ[1].poll();
				if (p1.idx != p2.idx) {
					sum += p1.p + p2.p;
				} else {
					if (pQ[0].isEmpty() && pQ[1].isEmpty()) {
						sum += p1.p;
					} else if (pQ[0].isEmpty() && !pQ[1].isEmpty()) {
						sum += p1.p + pQ[1].poll().p;
					} else if (!pQ[0].isEmpty() && pQ[1].isEmpty()) {
						sum += p2.p + pQ[0].poll().p;
					} else {
						sum += (pQ[0].peek().p >= pQ[1].peek().p ? p2.p + pQ[0].poll().p : p1.p + pQ[1].poll().p);
					}
				}
			} else {
				if (!pQ[0].isEmpty()) {
					sum += pQ[0].poll().p;
				}
				if (!pQ[1].isEmpty()) {
					sum += pQ[1].poll().p;
				}
			}

			pQ[0].clear();
			pQ[1].clear();

		}
		return sum;

	}

	static class AP {
		int x; 
		int y; 
		int c;
		int p;

		AP(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

		@Override
		public boolean equals(Object obj) {
			AP ap = (AP) obj;
			return (Math.abs(this.x - ap.x) + Math.abs(this.y - ap.y)) <= this.c;
		}
	}

	static class Node implements Comparable<Node> {
		int idx;
		int p;

		Node(int idx, int p) {
			this.idx = idx;
			this.p = p;
		}

		@Override
		public int compareTo(Node o) {
			return this.p - o.p;
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
