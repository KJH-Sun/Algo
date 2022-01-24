package KAKAO2022BLIND;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 양과늑대 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
            new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5},
                {4, 6}, {8, 9}}));
    }

    static int[] Info;
    static int[][] Edges;
    static int max = 0;
    static List<Tree> trees = new ArrayList<>();

    public static int solution(int[] info, int[][] edges) {
        Info = info;
        Edges = edges;
        for (int i = 0; i < info.length; i++) {
            int left = -1;
            int right = -1;
            for (int[] edge : edges) {
                if (edge[0] == i) {
                    if (left == -1) {
                        left = edge[1];
                    } else {
                        right = edge[1];
                        break;
                    }
                }
            }
            trees.add(new Tree(info[i] == 0, left, right));
        }

        checkMaxSheep(0, 0, 0, new ArrayList<>(), new boolean[info.length]);

        return max;
    }

    private static void checkMaxSheep(int now, int sheep, int wolf, ArrayList<Integer> canMove,
        boolean[] visit) {
        if (Info[now] == 0) {
            sheep++;
        } else {
            wolf++;
        }
        ArrayList<Integer> tmp = new ArrayList<>(canMove);
        boolean[] tmpVisit = Arrays.copyOf(visit, visit.length);
        if (sheep > wolf) {
            max = Math.max(max, sheep);
            tmpVisit[now] = true;
            int left = trees.get(now).left;
            if (left != -1) {
                tmp.add(left);
            }
            int right = trees.get(now).right;
            if (right != -1) {
                tmp.add(right);
            }
            for (int go : tmp) {
                if (tmpVisit[go]) {
                    continue;
                }
                checkMaxSheep(go, sheep, wolf, tmp, tmpVisit);
            }
        }
    }

    static class Tree {

        boolean isSheep;
        int left;
        int right;

        public Tree(boolean isSheep, int left, int right) {
            this.isSheep = isSheep;
            this.left = left;
            this.right = right;
        }
    }

}
