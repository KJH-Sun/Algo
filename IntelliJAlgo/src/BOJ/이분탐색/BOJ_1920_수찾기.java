import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * 수를 입력받아서 정렬해주고,
 * Arrays.binarySearch 메서드를 통해서 그 배열에 숫자가 있는지 체크합니다
 * 있다면 양수, 없다면 음수가 나오므로 그에 따라서 0과 1을 출력합니다.
 * 
 */
public class BOJ_1920_수찾기 {
	static int N, M;
	static int[] numArr;
	static int[] exist;
	static int[] res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		numArr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numArr[i] = stoi(st.nextToken());
		}
		Arrays.sort(numArr);
		M = stoi(br.readLine());
		res = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			res[i] = Arrays.binarySearch(numArr, stoi(st.nextToken()));
		}
		for (int i : res) {
			if (i < 0) {
				System.out.println(0);
				continue;
			} else {
				System.out.println(1);
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
