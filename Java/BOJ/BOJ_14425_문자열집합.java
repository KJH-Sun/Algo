import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14425_문자열집합 {
	static int N, M;
	static String[] strArr;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		strArr = new String[N];
		for (int i = 0; i < N; i++) {
			strArr[i] = br.readLine();
		}
		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			for (String a : strArr) {
				if (s.equals(a)) {
					res++;
				}
			}
		}
		System.out.println(res);

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		
//		int N = Integer.parseInt(st.nextToken());
//		int M = Integer.parseInt(st.nextToken());
//		
//		Set<String> set = new HashSet<>();
//		int answer = 0;
//		
//		for(int i = 0; i < N ; i++) {
//			set.add(br.readLine());
//		}
//		
//		for(int i = 0; i < M ; i++) {
//			if(set.contains(br.readLine())) {
//				answer ++;
//			}
//		}
//		
//		System.out.println(answer);
//		
//	}
}
