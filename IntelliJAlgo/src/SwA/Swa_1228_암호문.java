import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Swa_1228_암호문 {
	static LinkedList<Integer> list = new LinkedList<>();
	static int N, O;
	static int[] oriPw;
	static int[] score;
	static int[] calo;
	static int max;

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		for (int tc = 1; tc <= 10; tc++) {
			N = stoi(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				list.add(stoi(st.nextToken()));
			}
			O = stoi(br.readLine());
			String[] pwOrder = br.readLine().split("I"); // 시작을 I로해서 첫번째 pwOrder[0]은 공백임
			for (int i = 1; i <= O; i++) { 
				st = new StringTokenizer(pwOrder[i], " ");
				int index = stoi(st.nextToken());
				int x = stoi(st.nextToken());
				for (int j = 0; j < x; j++) {
					list.add(index + j, stoi(st.nextToken()));
				}
			}
			sb.append("#" + tc + " ");
			for (int i = 0; i < 10; i++) {
				sb.append(list.get(i) + " ");
			}
			sb.append("\n");
			list.clear();
		}
		System.out.println(sb);

	}

}
