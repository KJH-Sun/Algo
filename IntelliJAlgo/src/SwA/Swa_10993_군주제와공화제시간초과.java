import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Swa_10993_군주제와공화제시간초과 {
	static List<Node> list = new LinkedList<>();
	static char[] res;
	static HashMap<Integer, MaxEf> effect = new HashMap<>();
	static int T, N;

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		T = stoi(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = stoi(br.readLine());
			res = new char[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				list.add(new Node(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()), i + 1));
			}
			Collections.sort(list);
			for (int i = 1; i <= N; i++) {
				effect.put(i, new MaxEf(0, 0));
			}
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					double power = (double) list.get(i).s
							/ (pow((list.get(j).x - list.get(i).x), 2) + (pow((list.get(j).y - list.get(i).y), 2)));
					if (power > list.get(j).s) {
						if (effect.get(list.get(j).num).idx == 0 || effect.get(list.get(j).num).p < power) {
							effect.put(list.get(j).num, new MaxEf(list.get(i).num, power));
						} else if (effect.get(list.get(j).num).p == power) {
							effect.put(list.get(j).num, new MaxEf(-1, power));
						}
					}
				}
			}
			sb.append("#" + tc);
			for (int i = 1; i <= N; i++) {
				if (effect.get(i).idx == 0) {
					sb.append(" " + 'K');
				} else if (effect.get(i).idx == -1) {
					sb.append(" " + 'D');
				} else {
					int temp = effect.get(i).idx;
					while (true) {
						int num = effect.get(temp).idx;
						if (num > 0) {
							temp = num;
						} else {
							break;
						}
					}
					sb.append(" " + temp);
				}
			}
			sb.append("\n");
			list.clear();
			effect.clear();
		}
		System.out.println(sb);
	}

	private static double pow(int a, int num) {
		double answer = 1;
		for (int i = 0; i < num; i++) {
			answer *= a;
		}
		return answer;
	}

	static class MaxEf {
		int idx;
		double p;

		MaxEf(int idx, double p) {
			this.idx = idx;
			this.p = p;
		}
	}

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int s; // 군사력
		int num;

		Node(int x, int y, int s, int num) {
			this.x = x;
			this.y = y;
			this.s = s;
			this.num = num;
		}

		@Override
		public int compareTo(Node o) {
			return o.s - this.s;
		}

	}

}