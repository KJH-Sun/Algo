package KAKAO2022BLIND;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

public class 주차요금계산 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(solution(new int[]{120, 0, 60, 591},
            new String[]{"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"})));
    }

    static int BaseTime, BaseFee, UnitTime, UnitFee;
    static Function<String, Integer> stoi = Integer::parseInt;

    public static int[] solution(int[] fees, String[] records) {
        BaseTime = fees[0];
        BaseFee = fees[1];
        UnitTime = fees[2];
        UnitFee = fees[3];
        StringTokenizer st;
        Queue<Record> inList = new ArrayDeque<>();
        List<Record> outList = new ArrayList<>();
        for (String record : records) {
            st = new StringTokenizer(record);
            int minute = chHtoM(st.nextToken());
            String carNum = st.nextToken();
            String InOut = st.nextToken();
            if (InOut.equals("IN")) {
                inList.add(new Record(carNum, minute));
            } else {
                outList.add(new Record(carNum, minute));
            }
        }
        Map<String, Integer> sum = new HashMap<>();
        while (!inList.isEmpty()) {
            Record in = inList.poll();
            Record out = null;
            for (Record o : outList) {
                if (in.carNum.equals(o.carNum)) {
                    out = o;
                    break;
                }
            }
            outList.remove(out);
            if (out != null) {
                int time = out.m - in.m;
                sum.merge(in.carNum, time, (ori, init) -> time + ori); //computeIfAbsent , merge
            } else {
                int time = 1439 - in.m;
                sum.merge(in.carNum, time, (ori, init) -> time + ori);
            }
        }
        List<Fee> answer = new ArrayList<>();
        for (String key : sum.keySet()) {
            int fee = 0;
            int acc = sum.get(key);
            if (acc <= BaseTime) {
                fee = BaseFee;
            } else {
                fee = (int) (BaseFee + Math.ceil((float) (acc - BaseTime) / UnitTime) * UnitFee);
            }
            answer.add(new Fee(Integer.parseInt(key), fee));
        }
        Collections.sort(answer);
        int[] ans = new int[answer.size()];
        int idx = 0;
        for (Fee f : answer) {
            ans[idx++] = f.cost;
        }
        return ans;
    }

    private static int chHtoM(String s) {
        String[] time = s.split(":");
        int h = stoi.apply(time[0]);
        int m = stoi.apply(time[1]);
        return h * 60 + m;
    }

    static class Record implements Comparable<Record> {

        String carNum;
        int m;

        public Record(String carNum, int m) {
            this.carNum = carNum;
            this.m = m;
        }

        @Override
        public int compareTo(Record o) {
            return this.m - o.m;
        }
    }

    static class Fee implements Comparable<Fee> {

        int num;
        int cost;

        public Fee(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Fee o) {
            return this.num - o.num;
        }
    }

}
