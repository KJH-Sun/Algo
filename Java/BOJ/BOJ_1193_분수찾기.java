import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1193_분수찾기 {
	static int X;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		X = stoi(br.readLine());

		int cnt = 0;
		int k = 0;
		while (true) {
			int x = k;
			int y = 0;
			for (int i = k; 0 <= i; i--) {
				cnt++;
				if (cnt == X) {
					System.out.print((x + 1) + "/" + (y + 1));
					System.exit(0);
				}
				x--;
				y++;
			}
			k++;
			x = 0;
			y = k;
			for (int i = 0; i <= k; i++) {
				cnt++;
				if (cnt == X) {
					System.out.print((x + 1) + "/" + (y + 1));
					System.exit(0);
				}
				x++;
				y--;
			}
			k++;
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
