import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 주어진 숫자 배열을 toBinaryString 으로 변경한다(length에 맞게 앞쪽에 0을 추가해준다)
 * 2. 주어진 두개의 map을 대조하며 한쪽이라도 벽이라면 무조건 벽을, 아니라면 빈공간을 채워넣어준다.
 */

public class 비밀지도 {

	public static void main(String[] args) throws IOException {
		int n = 5;
		int[] arr1 = { 9, 20, 28, 18, 11 };
		int[] arr2 = { 30, 1, 21, 17, 28 };
		System.out.println(solution(n, arr1, arr2));
	}

	public static String[] solution(int n, int[] arr1, int[] arr2) {
		char[][] map = new char[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = itob(arr1[i], n);
		}
		for (int i = 0; i < n; i++) {
			char[] c = itob(arr2[i], n);
			for (int j = 0; j < n; j++) {
				if (map[i][j] == c[j]) {
					if (map[i][j] == '1') {
						map[i][j] = '#';
					} else {
						map[i][j] = ' ';
					}
				} else {
					map[i][j] = '#';
				}
			}
		}
		String[] answer = new String[n];
		for (int i = 0; i < n; i++) {
			answer[i] = "";
			for (int j = 0; j < n; j++) {
				answer[i] += map[i][j];
			}
		}
		return answer;
	}

	private static char[] itob(int num, int length) {
		StringBuffer sb = new StringBuffer();
		String s = Integer.toBinaryString(num);
		int size = length - s.length();
		while (size-- > 0) {
			sb.append("0");
		}
		sb.append(s);
		return sb.toString().toCharArray();
	}

}
