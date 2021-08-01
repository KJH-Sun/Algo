import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 2021 네이버 웹툰 코테 3번
 * 최대한 심플하게 구현해봤다.
 */


public class BOJ_9935_문자열폭발 {
	static StringBuilder sb = new StringBuilder();
	static String bomb, str;
	static int bomblen;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		bomb = br.readLine();
		bomblen = bomb.length();
		for (int i = 0; i < str.length(); i++) {
			sb.append(str.charAt(i));
			if (sb.length() >= bomblen) {
				if (sb.substring(sb.length() - bomblen, sb.length()).equals(bomb)) {
					sb.delete(sb.length() - bomblen, sb.length());
				}
			}
		}
		System.out.println(sb.length() == 0 ? "FRULA" : sb);
	}
}
