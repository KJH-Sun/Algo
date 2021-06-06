import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 무지의먹방라이브 {
	static Comparator<Food> left = new Comparator<Food>() {
		@Override
		public int compare(Food o1, Food o2) {
			return o1.quan - o2.quan;
		}
	};
	static Comparator<Food> idx = new Comparator<Food>() {
		@Override
		public int compare(Food o1, Food o2) {
			return o1.idx - o2.idx;
		}
	};

	public static void main(String[] args) throws IOException {
		System.out.println(solution(new int[] { 3, 1, 2 }, 5));
	}

	public static int solution(int[] food_times, long k) {
//		if (Arrays.stream(food_times).sum() <= k) {
//			return -1;
//		} //reduce 작업을 거치기때문에 이게 시간 더 오래걸림
		long food_sum = 0; 
        for (int i = 0; i < food_times.length; i++) {
            food_sum += food_times[i];
        }
        if (food_sum <= k) return -1;
        
		PriorityQueue<Food> foods = new PriorityQueue<>(left);
		for (int i = 0; i < food_times.length; i++) {
			foods.add(new Food(i + 1, food_times[i]));
		}

		long total = 0; // 먹기 위해 사용한 시간
		long previous = 0; // 직전에 다 먹은 음식 시간
		long length = food_times.length; // 남은 음식 개수

		while (total + ((foods.peek().quan - previous) * length) <= k) {
			int now = foods.poll().quan;
			total += (now - previous) * length;
			length -= 1;
			previous = now;
		}

		ArrayList<Food> result = new ArrayList<>(foods);
		result.sort(idx);

		return result.get((int) ((k - total) % length)).idx;

	}

	static class Food {
		int idx;
		int quan;

		Food(int idx, int quan) {
			this.idx = idx;
			this.quan = quan;
		}
	}

}
