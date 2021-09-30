package Programmers.SummerWinterCoding;

public class 배달 {

    static int INF = 500001;

    public int solution(int N, int[][] road, int K) {
        int[][] map = new int[N][N];                                        //각 정점별 최단거리 배열.

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (i == j) {                                                //i==j일땐 0;
                    map[i][j] = 0;
                    continue;
                }
                map[i][j] = INF;
            }
        }

        for (int i = 0; i < road.length; i++) { // road배열 적용
            if (map[road[i][0] - 1][road[i][1] - 1] < road[i][2]) {
                continue;   //원래 있는 길이 더 적은 길이면 무시.
            }
            map[road[i][0] - 1][road[i][1] - 1] = road[i][2];                  //양쪽으로 연결.
            map[road[i][1] - 1][road[i][0] - 1] = road[i][2];
        }

        for (int k = 0; k < N; k++) {                                           //플로이드 와샬 알고리즘
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        int count = 0;                                                        //1번 도시에 K이하만큼 연결돼있는 도시 개수

        for (int i = 0; i < map[0].length; i++) {
            if (map[0][i] <= K) {
                count++;
            }
        }

        return count;

    }
}
