import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * V개의 노드가 있고 e개의 간선이 있다면, [V-1]번 e개의 간선에 대해 dist[]를 갱신해준다.
 * 그러고나서 1번 e개의 간선에 대해 dist[]를 갱신하려할때 갱신된다면 음의 사이클이 존재한다는 뜻.(영구히 갱신된다는 뜻)
 * 만약 dist의 값이 INF라면, 해당 경로가 없다는 것을 의미한다.
 * 출력초과나면 INF를 long으로 변경하자.
 * 
 */

public class BOJ_11657_타임머신 {
	static int N, M;
	static long[] dist;
	static final long INF = (long) 1e18;
	static Bus[] line;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		dist = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			dist[i] = INF;
		}
		line = new Bus[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			line[i] = new Bus(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
		}
		if (bellmanFord()) {
			for (int i = 2; i <= N; i++) {
				if (dist[i] == INF) {
					System.out.println(-1);
				} else {
					System.out.println(dist[i]);
				}
			}
		} else {
			System.out.println(-1);
		}

	}

	private static boolean bellmanFord() {
		dist[1] = 0;

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Bus bus = line[j];
				if (dist[bus.s] != INF && dist[bus.e] > dist[bus.s] + bus.t) {
					dist[bus.e] = dist[bus.s] + bus.t;
				}
			}
		}
		for (int i = 0; i < M; i++) {
			Bus bus = line[i];
			if (dist[bus.s] != INF && dist[bus.e] > dist[bus.s] + bus.t) {
				return false;
			}
		}

		return true;
	}

	static class Bus {
		int s;
		int e;
		int t;

		Bus(int s, int e, int t) {
			this.s = s;
			this.e = e;
			this.t = t;
		}

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
