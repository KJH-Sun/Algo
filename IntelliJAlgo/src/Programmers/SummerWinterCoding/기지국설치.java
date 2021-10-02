package Programmers.SummerWinterCoding;

public class 기지국설치 {

    public static void main(String[] args) {
        System.out.println(solution(11, new int[]{4, 11}, 1));
    }


    public static int solution(int n, int[] stations, int w) {
        int answer = 0;
        int idx = 0;
        int pos = 1;
        int coverage = (w*2) + 1;
        int len = stations.length;
        while(pos <= n) {
            if(idx < len && pos >= stations[idx] - w) {
                pos = stations[idx] + w + 1;
                idx ++;
            }
            else {
                answer += 1;
                pos += coverage;
            }
        }
        return answer;
    }

}
