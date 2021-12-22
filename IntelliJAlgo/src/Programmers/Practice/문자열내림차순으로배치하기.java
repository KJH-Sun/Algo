package Programmers.Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class 문자열내림차순으로배치하기 {

    public static void main(String[] args) {
        System.out.println(solution("Zbcdefg"));
    }

    public static String solution(String s) {
        List<Character> str = s.chars().mapToObj(e -> (char) e).sorted(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o2 - o1;
            }
        }).collect(Collectors.toList());
        return str.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
