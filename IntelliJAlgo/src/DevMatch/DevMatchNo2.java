package DevMatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DevMatchNo2 {

    public static void main(String[] args) {
        System.out.println(solution(30, "MON", new int[]{1, 2, 3, 4, 28, 29, 30}));
    }

    static int answer = 0;

    public static int solution(int leave, String day, int[] holidays) {
        boolean[] holidayCalender = new boolean[31];
        int workDayCnt = 0;
        for (int holiday : holidays) {
            holidayCalender[holiday] = true;
        }
        findWeekend(day, holidayCalender);
        for (int i = 1; i <= 30; i++) {
            if (!holidayCalender[i]) {
                workDayCnt++;
            }
        }
        if (leave >= workDayCnt) {
            return 30;
        }
        useVacation(leave, holidayCalender, 1);
        return answer;
    }

    private static void useVacation(int leave, boolean[] holidayCalender, int now) {
        if (leave == 0) {
            answer = Math.max(answer, checkLongVacation(holidayCalender));
            return;
        }
        for (int i = now; i <= 30; i++) {
            if (!holidayCalender[i]) {
                holidayCalender[i] = true;
                useVacation(leave - 1, holidayCalender, i + 1);
                holidayCalender[i] = false;
            }
        }
    }

    private static int checkLongVacation(boolean[] holidayCalender) {
        int max = 0;
        int now = 0;
        for (int i = 1; i <= 30; i++) {
            if (holidayCalender[i]) {
                now++;
            } else {
                max = Math.max(max, now);
                now = 0;
            }
        }
        return Math.max(now, max);
    }

    private static void findWeekend(String day, boolean[] holidayList) {
        int startWeekendDay = 0;
        switch (day) {
            case "MON":
                startWeekendDay = 6;
                break;
            case "TUE":
                startWeekendDay = 5;
                break;
            case "WED":
                startWeekendDay = 4;
                break;
            case "THU":
                startWeekendDay = 3;
                break;
            case "FRI":
                startWeekendDay = 2;
                break;
            case "SAT":
                startWeekendDay = 1;
                break;
            case "SUN":
                startWeekendDay = 7;
                holidayList[1] = true;
                break;
        }
        while (startWeekendDay <= 30) {
            holidayList[startWeekendDay] = true;
            startWeekendDay += 1;
            if (startWeekendDay > 30) {
                break;
            }
            holidayList[startWeekendDay] = true;
            startWeekendDay += 6;
        }
    }
}
