import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10989_수정렬하기3 {
	static int N;
	static int[] arr = new int[10001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		N = stoi(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[stoi(br.readLine())]++;
		}
		for (int i = 1; i <= 10000; i++) {
			for (int j = 0; j < arr[i]; j++) {
				sb.append(i + "\n");
			}
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
