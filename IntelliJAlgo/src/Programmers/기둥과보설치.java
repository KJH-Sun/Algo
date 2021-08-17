package Programmers;


/*
    기둥 세우는 조건
    1. y 좌표가 0인 경우
    2. (x, y) | (x-1, y) 에 보가 있는지 확인
    3. (x,y-1)에 다른 기둥이 있는지 확인

    보 세우는 조건
    1. (x, y-1) | (x, y) 기둥
    2. (x-1, y) & (x+1, y) 보

    1. 명령 받았을 때 지을 수 있는지 확인
    2. 삭제 명령 시에는, 삭제하고 유효성 체크한다음에 롤백?

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 기둥과보설치 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(solution(5, new int[][]
            {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1},
                {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}}
        )));
    }

    static boolean[][] pillars, beams;
    static int N;

    public static int[][] solution(int n, int[][] build_frame) {
        N = n;
        pillars = new boolean[n + 1][n + 1];
        beams = new boolean[n + 1][n + 1];

        for (int[] build : build_frame) {
            if (build[3] == 1) { // 설치
                if (build[2] == 0) {// 기둥
                    if (canPillar(build[0], build[1])) {
                        pillars[build[0]][build[1]] = true;
                    }
                } else { // 보
                    if (canBeam(build[0], build[1])) {
                        beams[build[0]][build[1]] = true;
                    }
                }
            } else {
                delete(build);
                if (!isOk()) {
                    unDo(build);
                }
            }

        }

        return chkMap();
    }

    private static int[][] chkMap() {
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (pillars[i][j]) {
                    res.add(new int[]{i, j, 0});
                }
                if (beams[i][j]) {
                    res.add(new int[]{i, j, 1});
                }
            }
        }
        int[][] ans = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private static void unDo(int[] build) {
        // 되돌리기
        if (build[2] == 0) {
            pillars[build[0]][build[1]] = true;
        } else {
            beams[build[0]][build[1]] = true;
        }
    }

    private static boolean isOk() {
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (pillars[i][j]) {
                    if (!canPillar(i, j)) {
                        return false;
                    }
                }
                if (beams[i][j]) {
                    if (!canBeam(i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static void delete(int[] build) {
        if (build[2] == 0) {
            pillars[build[0]][build[1]] = false;
        } else {
            beams[build[0]][build[1]] = false;
        }
    }

    private static boolean canBeam(int x, int y) {
        if (isValid(x, y - 1) && pillars[x][y - 1]) {
            return true;
        }
        if (isValid(x + 1, y - 1) && pillars[x + 1][y - 1]) {
            return true;
        }

        return isValid(x - 1, y) && isValid(x + 1, y) && beams[x - 1][y] && beams[x + 1][y];

    }

    private static boolean canPillar(int x, int y) {
        if (y == 0) {
            return true;
        }
        if (beams[x][y]) {
            return true;
        }
        if (isValid(x - 1, y) && beams[x - 1][y]) {
            return true;
        }
        return isValid(x, y - 1) && pillars[x][y - 1];
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && 0 <= y && x <= N && y <= N;
    }


}
