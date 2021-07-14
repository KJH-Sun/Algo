package Programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    1. 모든 x,y 좌표를 돌면서 직사각형(2x3, 3x2)모양을 확인하여 블록 체크
    2. 확인한 6개의 칸에서 같은 숫자가 4번이상 나오면 블록으로 판정
    3. List<Block>에 블록의 번호, 가장 좌측 상단의 x,y 좌표, 비어있는 칸(까만 블록으로 채워야하는 칸)을 저장
    4. 리스트를 순회하며 빈칸이 까만블록으로 채울수있는지 확인하고, 둘 다 가능하다면 그 블록을 리스트에서 삭제
    5. 리스트 전체를 확인했는데도 아무 블록도 삭제할 수 없다면 반복문에서 나가고, 그동안 삭제한 블록의 개수를 출력
 */


public class 블록게임 {

    public static void main(String[] args) {
        System.out.println(solution(
            new int[][]{{0, 0, 1, 1, 1}, {0, 0, 0, 1, 0}, {3, 0, 0, 2, 0}, {3, 2, 2, 2, 0},
                {3, 3, 0, 0, 0}}));
    }

    static int[][] map;
    static List<Block> blocks = new ArrayList<>();
    static int N;

    public static int solution(int[][] board) {
        N = board.length;
        map = board;
        findBlock(board);
        int initSize = blocks.size();
        while (true) {
            List<Block> deleteBlock = new ArrayList<>();
            for (Block block : blocks) {
                if (canBomb(block)) {
                    deleteBlock.add(block);
                }
            }
            if (deleteBlock.size() == 0) {
                break;
            }
            blocks.removeAll(deleteBlock);
        }
        return initSize - blocks.size();
    }

    private static boolean canBomb(Block block) {
        for (Node n : block.emptyNode) {
            if (!checkTop(n, block.num)) {
                return false;
            }
        }
        deleteNum(block.num);
        return true;
    }

    private static void deleteNum(int num) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == num) {
                    map[i][j] = 0;
                }
            }
        }
    }

    private static boolean checkTop(Node n, int num) {
        if (map[n.x][n.y] != 0 && map[n.x][n.y] != num) {
            return false;
        }
        int nx = n.x;
        while (true) {
            nx -= 1;
            if (isValid(nx, n.y) && map[nx][n.y] != 0) {
                return false;
            } else if (!isValid(nx, n.y)) {
                return true;
            }
        }
    }

    private static void findBlock(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                checkBlock(i, j);
            }
        }
    }

    private static void checkBlock(int x, int y) {
        if (isValid(x + 2, y + 1)) {
            Map<Integer, Integer> nums = new HashMap<>();
            for (int i = x; i < x + 3; i++) {
                for (int j = y; j < y + 2; j++) {
                    if (map[i][j] != 0) {
                        nums.merge(map[i][j], 1, (ori, init) -> ++ori);
                    }
                }
            }
            for (int key : nums.keySet()) {
                if (nums.get(key) == 4) {
                    Node[] empty = new Node[2];
                    int idx = 0;
                    for (int i = x; i < x + 3; i++) {
                        for (int j = y; j < y + 2; j++) {
                            if (map[i][j] != key) {
                                empty[idx++] = new Node(i, j);
                            }
                        }
                    }
                    blocks.add(new Block(new Node(x, y), empty, key));
                }
            }
        }
        if (isValid(x + 1, y + 2)) {
            Map<Integer, Integer> nums = new HashMap<>();
            for (int i = x; i < x + 2; i++) {
                for (int j = y; j < y + 3; j++) {
                    if (map[i][j] != 0) {
                        nums.merge(map[i][j], 1, (ori, init) -> ++ori);
                    }
                }
            }
            for (int key : nums.keySet()) {
                if (nums.get(key) == 4) {
                    Node[] empty = new Node[2];
                    int idx = 0;
                    for (int i = x; i < x + 2; i++) {
                        for (int j = y; j < y + 3; j++) {
                            if (map[i][j] != key) {
                                empty[idx++] = new Node(i, j);
                            }
                        }
                    }
                    blocks.add(new Block(new Node(x, y), empty, key));
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && 0 <= y && x < N && y < N;
    }

    static class Block {

        int num;
        Node headNode;
        Node[] emptyNode;

        public Block(Node headNode, Node[] emptyNode, int num) {
            this.headNode = headNode;
            this.emptyNode = emptyNode;
            this.num = num;
        }
    }

    static class Node {

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
