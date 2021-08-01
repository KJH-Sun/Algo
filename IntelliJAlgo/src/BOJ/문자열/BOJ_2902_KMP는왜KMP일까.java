import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


/*
 * 문자열의 첫글자를 StringBuffer에 넣고 출력
 * 
 */

public class BOJ_2902_KMP는왜KMP일까 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine(), "-");
		while (st.hasMoreTokens()) {
			sb.append(st.nextToken().charAt(0));
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
