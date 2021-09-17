package Programmers.DevMatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Node;

public class 다단계칫솔판매 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(
            new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
            new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
            new String[]{"young", "john", "tod", "emily", "mary"}, new int[]{12, 4, 2, 5, 10})));
    }

    static int nodeCnt = 0;
    static Map<String, Node> nameNode = new HashMap<>();
    static Map<String, List<Integer>> amounts = new HashMap<>();

    public static int[] solution(String[] enroll, String[] referral, String[] seller,
        int[] amount) {
        nodeCnt = enroll.length;
        nameNode.put("-", new Node("center", null, 0));
        for (int i = 0; i < nodeCnt; i++) {
            nameNode.put(enroll[i], new Node(enroll[i], null, 0));
        }
        for (int i = 0; i < nodeCnt; i++) {
            nameNode.get(enroll[i]).parent = nameNode.get(referral[i]);
        }
        for (int i = 0; i < seller.length; i++) {
            calProfit(seller[i], amount[i] * 100);
        }
        List<Integer> ans = new ArrayList<>();
        for (String roll : enroll) {
            ans.add(nameNode.get(roll).profit);
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

    private static void calProfit(String seller, int pay) {
        if (seller.equals("center") || pay < 1) {
            return;
        }
        Node now = nameNode.get(seller);
        int fees = (int) (pay * 0.1);
        int left = pay - fees;
        now.profit += left;
        calProfit(now.parent.name, fees);
    }


    static class Node {

        String name;
        Node parent;
        int profit;

        public Node(String name, Node parent, int profit) {
            this.name = name;
            this.parent = parent;
            this.profit = profit;
        }
    }
}
