package Programmers.SummerWinterCoding;

public class 스킬트리 {

    public int solution(String skill, String[] skill_trees) {

        int cnt = 0;
        outer:
        for (String tree : skill_trees) {
            int prev = tree.indexOf(skill.charAt(0));
            boolean first = prev != -1;
            for (int i = 1; i < skill.length(); i++) {
                int now = tree.indexOf(skill.charAt(i));
                if (!first) {
                    if (now != -1) {
                        System.out.println(tree + " 이 문자열에서 이게 없음 " + skill.charAt(i));
                        continue outer;
                    }
                }
                if (prev > now) {

                    System.out.println(tree + " 이 문자열에서 이게 빨리나옴 " + skill.charAt(i));
                    if (now != -1) {
                        continue outer;
                    } else {
                        first = false;
                    }
                } else {
                    prev = now;
                }
            }
            System.out.println(tree);
            cnt++;
        }

        return cnt;
    }
}
