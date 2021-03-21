
public class 프로그래머스실력체크레벨1 {
	public String solution(String s) {
		if (s.length() % 2 == 1) {
			return String.valueOf(s.charAt(s.length() / 2));
		} else {
			return String.valueOf(s.charAt(s.length() / 2 - 1)) + String.valueOf(s.charAt(s.length() / 2));
		}

	}

	public String solution(String[] seoul) {
		for (int i = 0; i < seoul.length; i++) {
			if (seoul[i].equals("Kim")) {
				return "김서방은 " + i + "에 있다";
			}
		}
		return null;
	}
}
