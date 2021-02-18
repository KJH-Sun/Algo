import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2839_설탕배달 {
	static int N, five, three;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		div();
	}

	private static void div() {
		five = N / 5; // 5kg으로 담을 수 있는 최대치로 시작
		while (five >= 0) { //five가 -1이 되는 시점까지 아래 조건문으로 나가지 못했다면, 두 개의 봉투로 담을 수 없는 무게
			if ((N - five * 5) % 3 != 0) { // 5kg으로 담을만큼 담고 3으로 나눴는데 나머지가 있으면, 5kg하나를 줄이고 다시 체크
				five--;
			} else {
				three = (N - five * 5) / 3; // 나머지가 없으면 딱떨어지므로 three 입력하고 반복문 이탈
				break;
			}
		}
		System.out.println(five + three); // 만약 반복문이 끝까지 돌아서 five가 -1이 된채로 나오면, 두 봉투로 담을 수 없는 무게이므로 -1+0 인 -1 출력
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
