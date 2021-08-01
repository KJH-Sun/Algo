import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_12907_동물원 {
	static int N;
	static int[] arr = new int[41];
	static int two;
	static int one;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[stoi(st.nextToken())]++;
		}
		boolean flag = true;
		int overlap = 2;
		for (int i = 0; i <= 40; i++) {
			if (overlap < arr[i]) {
				flag = false;
				break;
			}
			overlap = arr[i];
			if (arr[i] == 2) {
				two++;
			} else if (arr[i] == 1) {
				one = 1;
			}
		}
		if (flag) {
			System.out.println((int) (Math.pow(2, two + one)));
		} else {
			System.out.println(0);
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
