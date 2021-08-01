import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_13168_내일로여행 {
	static int N, R, M, K; // 도시의 수, 내일로 티켓 가격, 여행할 도시의 수
	static Set<String> cityName = new HashSet<>();
	static Map<String, Integer> cityNum = new HashMap<>();
	static List<Integer> travel = new ArrayList<>();
	static List<Edge> road[];
	static final double INF = (double) 1e12;
	static Set<String> free = new HashSet<>(Arrays.asList("Mugunghwa", "ITX-Saemaeul", "ITX-Cheongchun"));
	static Set<String> half = new HashSet<>(Arrays.asList("S-Train", "V-Train"));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		R = stoi(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			String s = st.nextToken();
			if (!cityName.contains(s)) {
				cityNum.put(s, cnt++);
				cityName.add(s);
			}
		}
		double[][] dist = new double[cnt][cnt];
		road = new ArrayList[cnt];
		for (int i = 1; i < cnt; i++) {
			road[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < dist[0].length; i++) {
			Arrays.fill(dist[i], INF);
		}
		M = stoi(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			travel.add(cityNum.get(st.nextToken()));
		}
		K = stoi(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			String type = st.nextToken();
			int scn = cityNum.get(st.nextToken());
			int ecn = cityNum.get(st.nextToken());
			long payment = Long.parseLong(st.nextToken());
			if (dist[scn][ecn] > payment) {
				dist[scn][ecn] = payment;
				dist[ecn][scn] = payment;
			}
			road[scn].add(new Edge(type, ecn, payment));
		}
		double noTomo = floyd(dist);
		for (int i = 0; i < dist[0].length; i++) {
			Arrays.fill(dist[i], INF);
		}
		for (int i = 1; i < cnt; i++) {
			for (Edge e : road[i]) {
				if (free.contains(e.type)) {
					dist[i][e.e] = 0;
					dist[e.e][i] = 0;
				} else if (half.contains(e.type)) {
					if (dist[i][e.e] > e.w / 2) {
						dist[i][e.e] = (e.w / 2L);
						dist[e.e][i] = (e.w / 2L);
					}
				} else {
					if (dist[i][e.e] > e.w) {
						dist[i][e.e] = (long) e.w;
						dist[e.e][i] = (long) e.w;
					}
				}
			}
		}
		double yesTomo = floyd(dist);
		System.out.println(noTomo > yesTomo + R ? "Yes" : "No");
	}

	private static double floyd(double[][] dist) {

		for (int g = 1; g <= cityName.size(); g++) {
			for (int i = 1; i <= cityName.size(); i++) {
				for (int j = 1; j <= cityName.size(); j++) {
					if (dist[i][j] > dist[i][g] + dist[g][j]) {
						dist[i][j] = dist[i][g] + dist[g][j];
					}
				}
			}
		}
		double sum = 0;
		for (int i = 0; i < travel.size() - 1; i++) {
			sum += dist[travel.get(i)][travel.get(i + 1)];
		}
		return sum;
	}

	static class Edge {
		String type;
		int e;
		double w;

		Edge(String type, int e, double w) {
			this.type = type;
			this.e = e;
			this.w = w;
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
