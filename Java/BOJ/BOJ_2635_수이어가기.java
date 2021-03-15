import java.util.Scanner;

public class BOJ_2635_수이어가기 {
	static StringBuilder tmp = new StringBuilder();
	static StringBuilder res = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		solve(num);
	}

	private static void solve(int num) {
		int max = Integer.MIN_VALUE;
		for (int i = num / 2; i <= num; i++) {
			int calNum = calc(num, i);
			if(max < calNum) {
				max = calNum;
				res.setLength(0);
				res.append(tmp);
			}
			tmp.setLength(0);
		}
		System.out.println(max); // 카운트 수 출력
		System.out.println(res);
	}

	private static int calc(int a, int b) {
		int c = a - b;
		if(c < 0) {
			tmp.append(a + " " + b + " ");
			return 2;
		}
		tmp.append(a + " ");
		
		return calc(b, c) + 1;
	}
}
