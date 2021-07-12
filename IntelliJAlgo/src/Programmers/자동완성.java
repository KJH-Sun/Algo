package Programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 자동완성 {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{
            "word", "war", "warrior", "world"
        }));
    }

    static Trie root = new Trie(new Node());

    public static int solution(String[] words) {
        int answer = 0;
        for (String word : words) {
            root.insert(word);
        }
        for (String word : words) {
            answer += root.count(word);
        }
        return answer;
    }


    static class Trie {

        Node node;

        public Trie(Node node) {
            this.node = node;
        }

        public void insert(String word) {
            Node node = this.node;

            for (int i = 0; i < word.length(); i++) {
                node.children.computeIfAbsent(word.charAt(i), (e) -> new Node());
                node.count++;
                node = node.children.get(word.charAt(i));
            }
            node.children.computeIfAbsent('*', (e) -> new Node());
            node.count++;
        }

        public int count(String word) {
            Node node = this.node;
            int cnt = 0;
            for (int i = 0; i < word.length(); i++) {
                cnt++;
                if (node.children.get(word.charAt(i)).count > 1) {
                    node = node.children.get(word.charAt(i));
                } else {
                    break;
                }
            }
            return cnt;

        }
    }

    static class Node {

        Map<Character, Node> children;
        int count;

        public Node() {
            this.children = new HashMap<>();
            this.count = 0;

        }
    }


}
