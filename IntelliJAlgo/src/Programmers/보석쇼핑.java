package Programmers;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class 보석쇼핑 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(
            new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
    }

    public static int[] solution(String[] gems) {
        Set<String> gemKinds = new HashSet<>(Arrays.asList(gems));
        Map<String, Integer> hj = new HashMap<>();
        Queue<String> que = new ArrayDeque<>();
        int start = 0;
        int nowPoint = 0;
        int end = gems.length;

        for (String gem : gems) {
            que.add(gem);
            hj.merge(gem, 1, (ori, init) -> ++ori);

            while (true) {
                String now = que.peek();
                if (hj.get(now) > 1) {
                    que.poll();
                    hj.put(now, hj.get(now) - 1);
                    nowPoint++;
                } else {
                    break;
                }
            }
            if (hj.size() == gemKinds.size() && end > que.size()) {
                end = que.size();
                start = nowPoint;
            }
        }

        return new int[]{start + 1, start + end};
    }

}
