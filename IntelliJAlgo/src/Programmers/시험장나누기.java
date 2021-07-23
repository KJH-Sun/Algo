package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 시험장나누기 {

    public static void main(String[] args) {
        System.out.println(solution(3, new int[]{12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1},
            new int[][]{{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {8, 5}, {2, 10}, {3, 0}, {6, 1},
                {11, -1}, {7, 4}, {-1, -1}, {-1, -1}}));
    }

    static final int INF = (int) 1e9;
    static final int MAX_SECTION = 10001;
    static Node[] list;
    static int[][] cost;

    public static int solution(int k, int[] num, int[][] links) {
        int size = num.length; // 시험장의 개수
        list = new Node[size];
        int sum = 0;
        boolean[] check = new boolean[size];
        for (int i = 0; i < size; i++) {
            int left = links[i][0];
            int right = links[i][1];
            if (left != -1) {
                check[left] = true;
            }
            if (right != -1) {
                check[right] = true;
            }
            list[i] = new Node(num[i], left, right);
            sum += num[i];
        }
        // links 에 나오지 않은 num이 루트노드
        int root = -1;
        for (int i = 0; i < size; i++) {
            if (!check[i]) {
                root = i;
            }
        }

        // 시험장 각 그룹의 최대 인원은 최소값으로 잡아도 전체 크기를 k로 나눈 것 이하로 내려갈 수 없다. 즉, 최대 인원의 최소값은 sum/k 이상이어야 한다.
        int bot = sum / k;
        // 각 그룹의 최대 값은 전체 크기를 넘을 수 없다.
        int top = sum;
        if (bot != top) {
            while (bot + 1 < top) {
                int mid = (bot + top) / 2;
                // cost[현재노드][0] : 현재 노드에 최대 그룹 인원 이하가 되도록 하기 위한 최소 그룹 인원의 최소 크기
                // cost[현재노드][1] : 현재 노드를 포함한 서브 트리의 최솟값
                cost = new int[size][2]; // dp 배열
                postOrder(root, mid); // 트리 순회
                if (cost[root][0] <= k) {
                    top = mid;
                } else {
                    bot = mid;
                }
            }
        }
        return top;
    }

    private static void postOrder(int pos, int w) {
        Node cur = list[pos];
        int data = cur.data;
        int left = cur.left;
        int right = cur.right;

        // 리프노드 먼저 탐색
        if (left != -1) {
            postOrder(left, w);
        }
        if (right != -1) {
            postOrder(right, w);
        }

        if (left == -1 && right == -1) { // leaf 노드
            if (data <= w) {
                cost[pos][0] = 1;
                cost[pos][1] = data;
            } else {
                cost[pos][0] = MAX_SECTION;
                cost[pos][1] = INF;
            }
        } else if (left != -1 && right != -1) { // full 노드
            // 1) pos + 왼쪽 트리 + 오른쪽 트리 <= L
            // 양쪽의 트리 그룹을 하나로 합치는 것이므로 -1 해준다.
            if (data + cost[left][1] + cost[right][1] <= w) {
                cost[pos][0] = cost[left][0] + cost[right][0] - 1;
                cost[pos][1] = data + cost[left][1] + cost[right][1];
            }
            // 2) pos + min(왼쪽, 오른쪽) <= L
            // section Left + Right 양쪽 날개중 하나만 병합하는 것이므로 -1은 해줄 필요 없다.
            else if (data + Math.min(cost[left][1], cost[right][1]) <= w) {
                cost[pos][0] = cost[left][0] + cost[right][0];
                cost[pos][1] = data + Math.min(cost[left][1], cost[right][1]);
            }
            // 3) pos <= L
            // section Left+Right + 1 하위 섹션과 병합할 수 없으므로 섹션의 개수 1개 증가
            else if (data <= w) {
                cost[pos][0] = cost[left][0] + cost[right][0] + 1;
                cost[pos][1] = data;
            } else { // 이 케이스에 들어온다는 것은, 이 탐색의 DP 데이터가 무의미하다는 뜻이다. L값을 높여서 다시 탐색한다.
                cost[pos][0] = MAX_SECTION;
                cost[pos][1] = INF;
            }
        } else { // 자식이 한쪽 밖에 없는 경우
            if (right == -1) {
                // 1) pos + 왼쪽 <= L
                if (data + cost[left][1] <= w) {
                    cost[pos][0] = cost[left][0];
                    cost[pos][1] = data + cost[left][1];
                }
                // 2) pos <= L
                else if (data <= w) {
                    cost[pos][0] = cost[left][0] + 1;
                    cost[pos][1] = data;
                } else {
                    cost[pos][0] = MAX_SECTION;
                    cost[pos][1] = INF;
                }
            }
            if (left == -1) {
                // 1) pos + 오른쪽 트리 <= L
                // section r
                if (data + cost[right][1] <= w) {
                    cost[pos][0] = cost[right][0];
                    cost[pos][1] = data + cost[right][1];
                }
                // 1) pos <= L
                // section r +1
                else if (data <= w) {
                    cost[pos][0] = cost[right][0] + 1;
                    cost[pos][1] = data;
                } else {
                    cost[pos][0] = MAX_SECTION;
                    cost[pos][1] = INF;
                }
            }
        }
    }


    static class Node {

        int data;
        int left, right;

        public Node(int data, int left, int right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

}
