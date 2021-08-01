//package Baekjoon;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class BOJ_1244_스위치켜고끄기 {
//	// 성별, 부여받은 번호, 스위치 켜지고 꺼짐을 표시하는 boolean배열 입력받기
//	boolean[] manipulate(int sex, int num, boolean[] light) {
//		// light 배열이 0번부터 시작하기때문에 nowNum은 num에서 1 빼서 시작합니다.
//		int nowNum = num - 1;
//		// 성별 남자인 경우
//		if (sex == 1) {
//			while (nowNum < light.length) {
//				light[nowNum] = (light[nowNum] ? false : true);
//				nowNum += num;
//			}
//			// 성별 여자인 경우
//		} else if (sex == 2) {
//			light[nowNum] = (light[nowNum] ? false : true);
//			int standard = 1;
//			while (true) {
//				if (nowNum + standard > light.length - 1 || nowNum - standard < 0) {
//					break;
//				}
//				if (light[nowNum + standard] == light[nowNum - standard]) {
//					light[nowNum + standard] = (light[nowNum + standard] ? false : true);
//					light[nowNum - standard] = (light[nowNum - standard] ? false : true);
//				} else {
//					break;
//				}
//				standard++;
//			}
//		}
//		return light;
//	}
//
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		Main m = new Main();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuffer sb = new StringBuffer();
//		int T = Integer.parseInt(br.readLine());
//		boolean[] light = new boolean[T];
//
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		for (int i = 0; i < T; i++) {
//			light[i] = (Integer.parseInt(st.nextToken()) == 1) ? true : false;
//		}
//		//학생 숫자 받기
//		int stuNum = Integer.parseInt(br.readLine());
//		for (int i = 0; i < stuNum; i++) {
//			st = new StringTokenizer(br.readLine());
//			int sex = Integer.parseInt(st.nextToken());
//			int num = Integer.parseInt(st.nextToken());
//			light = m.manipulate(sex, num, light);
//		}
//
//		int count = 0;
//		// 스위치 개수 20개 넘으면 줄바꿈
//		for (boolean a : light) {
//			if (a) {
//				sb.append("1 ");
//				count++;
//			} else {
//				sb.append("0 ");
//				count++;
//			}
//			if (count % 20 == 0) {
//				sb.append("\n");
//			}
//		}
//		System.out.println(sb);
//
//	}
//
//}
