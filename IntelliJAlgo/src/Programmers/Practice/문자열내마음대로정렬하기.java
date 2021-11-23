package Programmers.Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class 문자열내마음대로정렬하기 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{}, 1)));
    }

    public static String[] solution(String[] strings, int n) {
        List<String> strs = new ArrayList<>(Arrays.asList(strings));
        strs.sort(Comparator.comparingInt((String o) -> o.charAt(n)).thenComparing(o -> o));
        return strs.toArray(new String[0]);
    }
}
