import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Human implements Comparable<Human> {
	int start;
	int end;

	Human(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Human o) {
		return this.start - o.start;
	}

}

class Computer {
	int num;
	int end;

	Computer(int start, int end) {
		this.num = start;
		this.end = end;
	}

}

public class BOJ_12764_싸지방에간준하 {
	static int N, cx, cy;
	static int comCnt = 1;
	static PriorityQueue<Human> pQ = new PriorityQueue<>();
	static Queue<Computer> useComList = new PriorityQueue<>(new Comparator<Computer>() {
		@Override
		public int compare(Computer o1, Computer o2) {
			return o1.end - o2.end;
		}
	});
	static Queue<Computer> emptyCom = new PriorityQueue<>(new Comparator<Computer>() {
		@Override
		public int compare(Computer o1, Computer o2) {
			return o1.num - o2.num;
		}
	});

	static List<Integer> comUser = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = stoi(st.nextToken());
			int e = stoi(st.nextToken());
			pQ.offer(new Human(s, e)); // 시작시간, 종료시간
		}

		for (int i = 0; i < N; i++) {
			Human h = pQ.poll();
			while (!useComList.isEmpty()) {
				if (h.start > useComList.peek().end) {
					emptyCom.offer(useComList.poll());
				} else {
					break;
				}
			}
			if (emptyCom.isEmpty()) {
				comUser.add(1);
				useComList.offer(new Computer(comUser.size() - 1, h.end));
			} else {
				Computer c = emptyCom.poll();
				comUser.set(c.num, comUser.get(c.num) + 1);
				c.end = h.end;
				useComList.offer(c);
			}
		}

		System.out.println(comUser.size());
		for (int i : comUser) {
			System.out.print(i + " ");
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
