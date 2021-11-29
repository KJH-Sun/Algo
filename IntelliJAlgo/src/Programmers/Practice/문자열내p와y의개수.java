package Programmers.Practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class 문자열내p와y의개수 {

    boolean solution(String s) {
        String str = s.toLowerCase();
        int p = 0;
        int y = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'p') {
                p++;
            } else if (str.charAt(i) == 'y') {
                y++;
            }
        }

        return p == y;
    }
}
