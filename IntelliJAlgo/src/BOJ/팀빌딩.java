package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;

public class 팀빌딩 {

    static int N;
    static Function<String, Integer> stoi = Integer::parseInt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int max = 0;
        N = stoi.apply(br.readLine());
        List<Developer> developers = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            developers.add(new Developer(i, stoi.apply(st.nextToken())));
        }
        Collections.sort(developers);
        for (int i = 0; i < developers.size(); i++) {
            Developer d = developers.get(i);
            for (int j = i + 1; j < developers.size(); j++) {
                Developer d2 = developers.get(j);
                int ans = (Math.abs(d.idx - d2.idx) - 1) * d.capa;
                max = Math.max(max, ans);
            }
        }

        System.out.println(max);
    }

    static class Developer implements Comparable<Developer> {

        int idx;
        int capa;

        public Developer(int idx, int capa) {
            this.idx = idx;
            this.capa = capa;
        }

        @Override
        public int compareTo(Developer o) {
            return this.capa - o.capa;
        }
    }

}
