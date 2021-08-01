import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_2751_수정렬하기2 {
	static int N;
	static List<Integer> arr = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		N = stoi(br.readLine());
		for (int i = 0; i < N; i++) {
			arr.add(stoi(br.readLine()));
		}
		Collections.sort(arr);
		for (int i = 0; i < N; i++) {
			sb.append(arr.get(i) + "\n");
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
