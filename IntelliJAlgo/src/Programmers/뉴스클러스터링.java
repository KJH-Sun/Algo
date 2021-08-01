import java.util.*;
import java.util.regex.Pattern;

public class 뉴스클러스터링 {
	static boolean[][] check;
	static char[][] map;
	static Queue<Character> que = new ArrayDeque<>();
	static int inter, union;

	public static void main(String[] args) {
//		String str1 = "FRANCE";
//		String str2 = "french";
//		String str1 = "handshake";
//		String str2 = "shake hands";
		String str1 = "AAbbaa_AA";
		String str2 = "BBB";
		System.out.println(solution(str1, str2));
	}

	public static int solution(String str1, String str2) {
		Map<String, Integer> s1 = new HashMap<>();
		Map<String, Integer> s2 = new HashMap<>();
		str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();

		for (int i = 0; i < str1.length() - 1; i++) {
			String s = str1.substring(i, i + 2);
			if (!isAlpabet(s)) {
				continue;
			}
			s1.merge(s, 1, (ori, init) -> ++ori);
		}

		for (int i = 0; i < str2.length() - 1; i++) {
			String s = str2.substring(i, i + 2);
			if (!isAlpabet(s)) {
				continue;
			}
			s2.merge(s, 1, (ori, init) -> ++ori);
		}

		for (String s : s1.keySet()) {
			if (s2.containsKey(s)) {
				int big = 0;
				int small = 0;
				if (s1.get(s) > s2.get(s)) {
					big = s1.get(s);
					small = s2.get(s);
				} else {
					small = s1.get(s);
					big = s2.get(s);
				}
				inter += small;
				union += big;
				s2.remove(s);
			} else {
				union += s1.get(s);
			}
		}

		for (int i : s2.values()) {
			union += i;
		}
		if (union == 0) {
			return 65536;
		}

		return (int) (inter / (float) union * 65536);
	}

	// 숫자 ^[0-9]*$
	// ^ 문자열 시작
	// $ 문자열 종료
	// * 앞 문자가 많을수도 있고 무한정 많을수도있음 
	// [] 문자 집합, 범위
	// 시간복잡도는 O(N)

	private static boolean isAlpabet(String s) {
		return Pattern.matches("^[A-Z]*$", s);
	}
}
