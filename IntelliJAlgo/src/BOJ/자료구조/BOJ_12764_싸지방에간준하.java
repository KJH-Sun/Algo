import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 처음 입력받은 순서쌍은 사람 class로 입력받아 pQ에 저장하고, 시작시간을 기준으로 오름차순으로 정렬
 * Computer class를 만든 다음, 이 클래스는 두개의 정렬기준을 만듭니다(useComlist 종료시간 기준, emptyCom하나는 컴퓨터번호기준)
 * 
 * 반복문 : pQ에서 하나씩 뽑아 반복문 시작 / 빌때까지
 * 만약 useComList 우선순위 큐가 비어있지 않다면, 현재 pQ에서 뽑아놓은 Human 객체의 시작시간과 useComList 객체의 종료시간을 비교하여, 시작시간이 종료시간보다 크다면
 * useComList의 원소를 emptyCom으로 넘깁니다
 * 이후 emptyCom에서 (번호가 가장빠른) 원소를 뱉어 그 컴퓨터 번호에 해당하는 comUser 리스트의 원소 값을 1증가시킵니다
 * 이후 그 객체의 종료시간을 현재 pQ에서 뽑아왔던 h.end로 변환한 이후, 그 객체를 useComlist에 반환합니다
 * 만약 emptyCom이 비어있었다면, comUser 객체에 1을 추가하고(원소번호가 곧 컴퓨터 번호) useComlist에 h.end값과 컴퓨터 번호를 추가합니다.
 * 
 */

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
