import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 분할정복
 */

public class BOJ_1780_종이의개수 {
	static int N;
	static int[][] map;
	static int[] res = new int[3];

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
		
		// N이 1일때에 대한 예외처리
		if (N == 1) {
			res[map[0][0] + 1]++;
		} else {
			int result = solve(0, 0, N);
			if (result != -2) {
				res[result + 1]++;
			}
		}
		for (int i : res) {
			System.out.println(i);
		}

	}

	// 분할
	private static int solve(int x, int y, int side) {
		// 3까지 좁혔을때에 대한 처리(1까지 줄였으면 코드가 더 간단해질텐데 짜고 나서 깨달아서..ㅠㅠ)
		// 리스트를 통해 각각의 값을 받고, 모두 같은 숫자인지 아닌지는 list를 set로 변환해서 그 size가 1이면 체크해주는 식으로 확인
		// 모두 같으면 그 숫자를 리턴하고, 다르면 각각의 숫자를 res 배열에 추가해주고 -2를 리턴
		if (side == 3) {
			List<Integer> list = new ArrayList<>();
			for (int i = x; i < x + 3; i++) {
				for (int j = y; j < y + 3; j++) {
					list.add(map[i][j]);
				}
			}
			Set<Integer> set = new HashSet<>(list);
			if (set.size() == 1) {
				return list.get(0);
			} else {
				for (int i = 0; i < list.size(); i++) {
					res[list.get(i) + 1]++;
				}
				return -2;
			}
		}
		
		//여길 기점으로
		// 위와 처리 방식은 같지만, 리턴되어 온것들 중에 하나라도 -2가 있다면 -2를 제외하고 res 배열에 추가처리를 해주고 -2를 리턴한다
		// 리턴되어 온 것중 -2가 하나도 없다면 그 숫자를 리턴한다
		List<Integer> list = new ArrayList<>();
		
		int cut = side / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				list.add(solve(x + i * cut, y + j * cut, cut));
			}
		}
		
		Set<Integer> set = new HashSet<>(list);
		if (set.size() == 1 && list.get(0) != -2) {
			return list.get(0);
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == -2) {
					continue;
				} else {
					res[list.get(i) + 1]++;
				}
			}
			return -2;
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
