package Programmers.팁스타운2017;

public class 예상대진표 {


    public int solution(int n, int a, int b) {
        int answer = 1;
        int low = Math.min(a, b);
        int high = Math.max(a, b);
        while (true) {
            if (low % 2 == 1 && high % 2 == 0 && high - low == 1) {
                break;
            }

            if (low % 2 == 0) {
                low /= 2;
            } else {
                low /= 2;
                low++;
            }
            if (low == 0) {
                low = 1;
            }
            if (high % 2 == 0) {
                high /= 2;
            } else {
                high /= 2;
                high++;
            }
            answer++;
        }

        return answer;
    }
    /*
    public int solution(int n, int a, int b)
     {
         return Integer.toBinaryString((a-1)^(b-1)).length();
     }
     */
}
