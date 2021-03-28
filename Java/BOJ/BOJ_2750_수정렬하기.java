import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2750_수정렬하기 {
	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = stoi(br.readLine());
		}
		Arrays.sort(arr);
		for(int i = 0; i<N; i++) {
			System.out.println(arr[i]);
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
