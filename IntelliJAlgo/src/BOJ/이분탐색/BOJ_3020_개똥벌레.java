import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;

/*
 * 쉬운척하는 문제였는데 어려웠습니다...
 * 생각보다 메모리랑 시간이 터지는걸 처리하기 까다로워서,고민하다가 결국 종유석과 석순을 따로 처리하기로 했다.
 * 각 높이에서 
 * 종유석에 걸리는 숫자를 top에 넣고,
 * 석순에 걸리는 숫자를 bot에 넣어
 * 높이별로 수를 합산하여 가장 적게 걸리는 수를 계산
 * 
 * 
 */


public class BOJ_3020_개똥벌레 {
	static Function<String, Integer> stoi = Integer::parseInt;
	static int N, H;
	static int[] top, bot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi.apply(st.nextToken());
		H = stoi.apply(st.nextToken());
		top = new int[H + 2]; // 종유석
		bot = new int[H + 2]; // 석순
		for (int i = 0; i < N / 2; i++) {
			bot[stoi.apply(br.readLine())]++;
			top[H - stoi.apply(br.readLine()) + 1]++;
		}
		for (int i = H; i >= 1; i--) {
			top[i] += top[i + 1];
		}
		for (int i = 1; i <= H; i++) {
			bot[i] += bot[i - 1];
		}
		int min = Integer.MAX_VALUE;
		int now = 0;
		for (int i = 1; i <= H; i++) {
			int obstacle = bot[H] - bot[i - 1] + top[1] - top[i + 1];
			if (min > obstacle) {
				min = obstacle;
				now = 1;
			} else if (min == obstacle) {
				now++;
			}
		}
		System.out.println(min + " " + now);
	}

}