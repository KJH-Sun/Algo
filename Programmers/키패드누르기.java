import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 키패드누르기 {

	public static void main(String[] args) throws IOException {
		System.out.println(solution(new int[] {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right"));
	}

	  public static String solution(int[] numbers, String hand) {
	        int left = 10;
	        int right = 12;
	        
	        int[] lNum = {1,4,7};
	        int[] rNum = {3,6,9};
	        int[] dis = {0,1,2,1,2,3,2,3,4,3,4,5};
	        
	        String answer = "";
	        
	        outer: for(int num : numbers){
	            if(num == 0){
	                num = 11;
	            }
	            for(int ln : lNum){
	                if(num == ln){
	                    left = ln;
	                    answer += "L";
	                    continue outer;
	                }
	            }
	            for(int rn : rNum){
	                if(num == rn){
	                    right = rn;
	                    answer += "R";
	                    continue outer;
	                }
	            }
	            int ld = dis[Math.abs(left-num)];
	            int rd = dis[Math.abs(right-num)];
	            if(ld < rd){
	                left = num;
	                answer += "L";
	                continue;
	            }else if(ld > rd){
	                right = num;
	                answer += "R";
	                continue;
	            }else{
	                if(hand.equals("left")){
	                    left = num;
	                    answer += "L";
	                    continue;
	                }else{
	                    right = num;
	                    answer += "R";
	                    continue;
	                }
	            }
	        } 
	        return answer;
	    }
}
