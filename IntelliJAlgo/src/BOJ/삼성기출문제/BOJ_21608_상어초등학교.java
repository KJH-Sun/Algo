package samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 로직
 * 1. 주어진 입력을 Student 객체 리스트로 저장한다.
 * 2. 리스트를 순회하면서 자리를 찾는다.
 * 2-1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
   2-2. 2-1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
   2-3. 2-22를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
 * 
 */


public class BOJ_21608_상어초등학교 {
	static int N;
	static List<Student> students = new ArrayList<>();
	static Student[][] map;
	static int[] dx = new int[] { 0, 0, 1, -1 };
	static int[] dy = new int[] { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		map = new Student[N][N];
		StringTokenizer st;
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = stoi(st.nextToken());
			students.add(new Student(num, new int[] { stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()),
					stoi(st.nextToken()) }));
		}
		placement();
		System.out.println(satisfaction());
	}

	private static int satisfaction() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += Math.pow(10, check(i, j, map[i][j].prefer).like - 1);
			}
		}
		return sum;
	}

	private static void placement() {
		for (Student s : students) {
			findSeat(s.num, s.prefer);
		}
	}

	private static void findSeat(int num, int[] prefer) {
		// 사방탐색 하면서 좋아하는 사람 수, 빈자리 수, 행, 열 순으로 정렬하는 리스트 만들기
		List<Seat> seats = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null) {
					continue;
				}
				seats.add(check(i, j, prefer));
			}
		}
		Collections.sort(seats);
		map[seats.get(0).x][seats.get(0).y] = new Student(num, prefer);
	}

	private static Seat check(int x, int y, int[] prefer) {
		Seat seat = new Seat(x, y);
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!isValid(nx, ny)) {
				continue;
			}
			if (map[nx][ny] == null) {
				seat.empty++;
			} else {
				for (int j = 0; j < prefer.length; j++) {
					if (prefer[j] == map[nx][ny].num) {
						seat.like++;
					}
				}
			}
		}
		return seat;
	}

	static class Student {
		int num;
		int[] prefer;

		Student(int num, int[] prefer) {
			this.num = num;
			this.prefer = prefer;
		}
	}

	static class Seat implements Comparable<Seat> {
		int x;
		int y;
		int like;
		int empty;

		public Seat(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Seat o) {
			if (this.like != o.like) {
				return o.like - this.like;
			}
			if (this.empty != o.empty) {
				return o.empty - this.empty;
			}
			if (this.x != o.x) {
				return this.x - o.x;
			}
			return this.y - o.y;
		}

	}

	private static boolean isValid(int x, int y) {
		return 0 <= x && 0 <= y && x < N && y < N;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}