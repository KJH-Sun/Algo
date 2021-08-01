import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * timetable의 시간들을 pQ로 순서대로 정렬한 이후
 * 태워갈 수 있는 사람의 수가 토탈 수보다 적다면 마지막 버스시간에 도착하고,
 * 아니라면 마지막 버스에 타는 사람보다 1분 일찍 도착
 */



public class 카카오1차셔틀버스 {
	static StringTokenizer st;
	static StringBuffer sb;
	static PriorityQueue<Time> pQ = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
//		int n = 1;
//		int t = 1;
//		int m = 5;
//		String[] timetable = { "08:00", "08:01", "08:02", "08:03" };
//		int n = 2;
//		int t = 10;
//		int m = 2;
//		String[] timetable = { "09:10", "09:09", "08:00" };
//		int n = 2;
//		int t = 1;
//		int m = 2;
//		String[] timetable = { "09:00", "09:00", "09:00", "09:00" };
		int n = 2;
		int t = 10;
		int m = 3;
		String[] timetable = { "09:05", "09:09", "09:13" };
		System.out.println(solution(n, t, m, timetable));
	}

	public static String solution(int n, int t, int m, String[] timetable) {
		for (String s : timetable) {
			pQ.offer(new Time(s));
		}
		Time bus = new Time(9, 0);
		Time corn = new Time(9, 0);
		for (int i = 0; i < n; i++) {
			int busIn = 0;
			for (int j = 0; j < m; j++) {
				if(!pQ.isEmpty()) {
					Time tmp = pQ.peek();
					// 버스시간보다 사람이 일찍왔으면
					if (bus.compareTo(tmp) >= 0) {
						// 사람 태우고, corn이 와야하는 시간 조정
						corn = pQ.poll();
						busIn++;
					}
				}
				// 버스가 태워가는 사람보다 토탈 사람수가 많은경우
				if (i == n - 1 && busIn == m) {
					corn = new Time(corn.hour, corn.minute - 1); // 마지막으로 탄사람보다 1분 빨리타면 됩니다
				} else if (i == n - 1 && busIn < m) { // 버스에 자리있으면
					corn = new Time(bus.t); // 마지막으로 버스 오는 시간에 타면 됨
				}
			}
			bus = new Time(bus.hour, bus.minute + t);
		}
		return corn.t;
	}

	static class Time implements Comparable<Time> {
		String t;
		int hour;
		int minute;

		Time(String t) {
			this.t = t;
			st = new StringTokenizer(t, ":");
			hour = Integer.parseInt(st.nextToken());
			minute = Integer.parseInt(st.nextToken());
		}

		Time(int hour, int minute) {
			if (minute < 0) {
				minute += 60;
				hour--;
			}
			if (minute >= 60) {
				minute -= 60;
				hour++;
			}
			this.hour = hour;
			this.minute = minute;
			sb = new StringBuffer();
			if (hour < 10) {
				sb.append(0);
			}
			sb.append(hour);
			sb.append(":");
			if (minute < 10) {
				sb.append(0);
			}
			sb.append(minute);
			this.t = sb.toString();
		}

		@Override
		public int compareTo(Time o) {
			return this.hour - o.hour == 0 ? this.minute - o.minute : this.hour - o.hour;
		}
	}

}
