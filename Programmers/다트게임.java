import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class 다트게임 {
	static String area = "-SDT";

	public static void main(String[] args) throws IOException {
		System.out.println(solution("1D2S#10S"));
	}

	public static int solution(String dartResult) {
		int[] score = new int[3];
		int idx = -1;
		for (int i = 0; i < dartResult.length(); i++) {
			String now = dartResult.substring(i, i + 1);
			if (checkNum(now)) {
				if (Integer.parseInt(now)==1) {
					if(dartResult.charAt(i+1)=='0') {
						score[++idx] = 10;
						i++;
						continue;
					}
				}
				++idx;
				score[idx] = Integer.parseInt(now);
				continue;
			} else {
				if (area.indexOf(now.charAt(0)) > 0) {
					score[idx] = (int) Math.pow(score[idx], area.indexOf(now.charAt(0)));
				} else {
					if (now.charAt(0) == '*') {
						if (idx == 0) {
							score[idx] *= 2;
						} else {
							score[idx] *= 2;
							score[idx - 1] *= 2;
						}
					} else {
						score[idx] *= -1;
					}
				}
			}
		}
		return Arrays.stream(score).sum();
	}

	private static boolean checkNum(String s) {
		String pattern = "^[0-9]*$";
		return Pattern.matches(pattern, s);
	}

}
