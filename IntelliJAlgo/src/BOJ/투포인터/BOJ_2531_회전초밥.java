import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2531_회전초밥 {
	static int N, d, k, c;
	static Set<Integer> set = new HashSet<>();
	static int[] arr;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		d = stoi(st.nextToken());
		k = stoi(st.nextToken());
		c = stoi(st.nextToken());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = stoi(br.readLine());
		}
		for (int i = 0; i < N; i++) {
			set.clear();
			for (int j = i; j < k + i; j++) {
				set.add(arr[j % N]);
			}
			set.add(c);
			max = Math.max(max, set.size());
		}
		System.out.println(max);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
