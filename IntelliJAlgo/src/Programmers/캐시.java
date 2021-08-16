package Programmers;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

public class 캐시 {

    public static void main(String[] args) {
        System.out.println(solution(3,
            new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul",
                "NewYork", "LA"}));
    }

    public static int solution(int cacheSize, String[] cities) {
        ArrayList<String> caches = new ArrayList<>();

        int answer = 0;
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        for (String c : cities) {
            String city = c.toLowerCase(Locale.ROOT);
            if (caches.contains(city)) {
                answer += 1;
                caches.remove(city);
                caches.add(city);
            } else {
                if (caches.size() < cacheSize) {
                    caches.add(city);
                    answer += 5;
                } else {
                    caches.remove(0);
                    caches.add(city);
                    answer += 5;
                }
            }

        }
        return answer;
    }

}
