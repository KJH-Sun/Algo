package Programmers.팁스타운2017;

import java.util.Stack;

/*
    Stack에 한글자씩 넣고, 다음 글자가 Stack의 peek과 같으면 둘 다 빼고 아니면 넣는 방식
    최종적으로 Stack에 남은 char 가 존재하면 0, 아니면 1
 */

public class 짝지어제거하기 {

    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty()) {
                if (stack.peek() == s.charAt(i)) {
                    stack.pop();
                    continue;
                }
            }
            stack.push(s.charAt(i));
        }
        return stack.isEmpty() ? 1 : 0;
    }
}
