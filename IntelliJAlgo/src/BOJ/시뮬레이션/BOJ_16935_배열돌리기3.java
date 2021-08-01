import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16935_배열돌리기3 {
	static int N, M, R;
	static int[][] field;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		R = stoi(st.nextToken());
		field = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				field[i][j] = stoi(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			play(stoi(st.nextToken()));
		}

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length; j++) {
				System.out.print(field[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void play(int cal) {
		switch (cal) {
		case 1:
			for (int i = 0; i < field.length / 2; i++) {
				vswap(i, field.length - 1 - i);
			}
			break;
		case 2:
			hswap();
			break;
		case 3:
			rRotate();
			break;
		case 4:
			lRotate();
			break;
		case 5:
			shuffle();
			break;
		case 6:
			shuffle2();
			break;
		}
	}

	private static void shuffle() {
		int temp[][] = new int[field.length][field[0].length];
		// 1->2
		for (int i = 0; i < field.length / 2; i++) {
			for (int j = 0; j < field[0].length / 2; j++) {
				temp[i][j + field[0].length / 2] = field[i][j];
			}
		}
		// 2->3
		for (int i = 0; i < field.length / 2; i++) {
			for (int j = field[0].length / 2; j < field[0].length; j++) {
				temp[i + field.length / 2][j] = field[i][j];
			}
		}
		// 3-> 4
		for (int i = field.length / 2; i < field.length; i++) {
			for (int j = field[0].length / 2; j < field[0].length; j++) {
				temp[i][j - field[0].length / 2] = field[i][j];
			}
		}
		// 4->1
		for (int i = field.length / 2; i < field.length; i++) {
			for (int j = 0; j < field[0].length / 2; j++) {
				temp[i - field.length / 2][j] = field[i][j];
			}
		}
		field = temp;
	}

	private static void shuffle2() {
		int temp[][] = new int[field.length][field[0].length];
		// 1->4
		for (int i = 0; i < field.length / 2; i++) {
			for (int j = 0; j < field[0].length / 2; j++) {
				temp[i + field.length / 2][j] = field[i][j];
			}
		}
		// 4->3
		for (int i = field.length / 2; i < field.length; i++) {
			for (int j = 0; j < field[0].length / 2; j++) {
				temp[i][j + field[0].length / 2] = field[i][j];
			}
		}
		// 3->2
		for (int i = field.length / 2; i < field.length; i++) {
			for (int j = field[0].length / 2; j < field[0].length; j++) {
				temp[i - field.length / 2][j] = field[i][j];
			}
		}
		// 2->1
		for (int i = 0; i < field.length / 2; i++) {
			for (int j = field[0].length / 2; j < field[0].length; j++) {
				temp[i][j - field[0].length / 2] = field[i][j];
			}
		}
		field = temp;
	}

	private static void lRotate() {
		int temp[][] = new int[field[0].length][field.length];
		for (int i = 0; i < field[0].length; i++) {
			for (int j = 0; j < field.length; j++) {
				temp[i][j] = field[j][field[0].length - 1 - i];
			}
		}
		field = temp;
	}

	private static void rRotate() {
		int temp[][] = new int[field[0].length][field.length];

		for (int i = 0; i < field[0].length; i++) {
			for (int j = 0; j < field.length; j++) {
				temp[i][j] = field[field.length - 1 - j][i];
			}
		}
		field = temp;

	}

	private static void vswap(int a, int b) {
		int[] temp = new int[field[0].length];
		temp = field[a];
		field[a] = field[b];
		field[b] = temp;
	}

	private static void hswap() {
		int temp = 0;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length / 2; j++) {
				temp = field[i][j];
				field[i][j] = field[i][field[0].length - 1 - j];
				field[i][field[0].length - 1 - j] = temp;
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
