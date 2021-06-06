import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 받은 시간을 밀리초로 변환

public class 추석트래픽 {
	private static List<Req> reqs = new ArrayList<>();
	private static int ans;

	public static void main(String[] args) {
		String[] l = { "2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s" };
		System.out.println(solution(l));
	}

	private static int solution(String[] lines) {
		parse(lines);
		check();
		return ans;
	}

	private static void check() {
		for (Req r : reqs) {
			ans = Math.max(count(r), ans);
		}
	}

	private static int count(Req window) {
		int s = 0;
		int e = 0;
		for (Req r : reqs) {
			if (isIn(r, window.start.ms)) {
				s++;
			}
			if (isIn(r, window.end.ms)) {
				e++;
			}
		}
		return Math.max(s, e);
	}

	private static boolean isIn(Req r, long startWindow) {
		long endWindow = startWindow + 999; // 처리시간이 시작시간과 끝 시간을 포함하는 1초이기때문에 999초 더해줘야한다(1000초 더하면 1.001)
		return (r.end.ms >= startWindow && r.end.ms <= endWindow)
				|| (r.start.ms >= startWindow && r.start.ms <= endWindow)
				|| (r.start.ms <= startWindow && r.end.ms >= endWindow);
	}

	private static void parse(String[] lines) {
		StringTokenizer st;
		for (String l : lines) {
			st = new StringTokenizer(l);
			st.nextToken(); // 9월 15일 데이터라 앞쪽 로그는 필요없음
			reqs.add(new Req(st.nextToken(), st.nextToken()));
		}
	}

	static class Req {
		Ms start;
		Ms end;
		long process;

		public Req(String end, String process) {
			this.end = new Ms(Time.valueOf(end.substring(0, 8)), end.substring(9));
			this.process = (long) (Double.parseDouble(process.substring(0, process.length() - 1)) * 1000);
			this.start = new Ms(this.end.ms - this.process + 1);
		}
	}

	static class Ms {
		long ms;

		public Ms(Time time, String ms) {
			this.ms = time.getTime() + Long.parseLong(ms);
		}

		public Ms(long ms) {
			this.ms = ms;
		}
	}

}