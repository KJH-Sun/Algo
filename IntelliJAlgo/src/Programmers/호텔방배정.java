package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 호텔방배정 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(10, new long[]{1, 3, 4, 1, 3, 1})));
    }

    static HashMap<Long, Long> room = new HashMap<>();

    public static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        for (int i = 0; i < room_number.length; i++) {
            answer[i] = assignRoom(room_number[i]);
        }
        return answer;
    }

    private static long assignRoom(long roomNum) { // 방번호를 주면 빈방을 배정해주는 재귀함수
        if (!room.containsKey(roomNum)) {
            room.put(roomNum, roomNum + 1);
            return roomNum;
        }
        long empty = assignRoom(room.get(roomNum));
        room.put(roomNum, empty);
        return empty;
    }
}
