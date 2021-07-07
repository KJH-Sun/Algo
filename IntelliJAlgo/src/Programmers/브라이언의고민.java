package Programmers;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 브라이언의고민 {

    public static void main(String[] args) {
        System.out.println(solution("HaEaLaLaObWORLDb"));
    }

    static Map<Character, List<Integer>> Special = new HashMap<>();
    static final String INVALID = "invalid";

    public static String solution(String sentence) {
        try {
            /* 기호 종류, 위치 파악 */
            char[] s = sentence.toCharArray();
            int len = s.length; // l : 문장의 길이 (length)
            List<Character> ls = new ArrayList<>();
            HashMap<Character, List<Integer>> hm = new HashMap<>(); // hm : 기호 종류+위치 (hash map)
            for (int i = 0; i < len; i++) {
                char c = s[i];
                if (c == ' ') {
                    return INVALID; // [X] 공백 존재
                }
                if (c >= 'a' && c <= 'z') { // Character.isLowerCase(cc)
                    if (!hm.containsKey(c)) {
                        ls.add(c);
                        hm.put(c, new ArrayList<>());
                    }
                    hm.get(c).add(i);
                }
            }
            if (hm.size() == 0) {
                return sentence; // [O] 기호 없음
            }
            /* 기호 제거 후 원래 문구로 변환하기 */
            StringBuilder sb = new StringBuilder();
            int str_Start_Index = 0, Word_Start_Index = 0, Word_End_Index = 0, iswp = -1, iewp = -1, isc, iec, iscp = -1, iecp = -1, n, d, r = 0;
            // string start, word start/end cur/pre, char start/end cur/pre, num of a sign, distance, pattern
            List<Integer> ps;
            for (char c : ls) { // c : 현재 기호
                ps = hm.get(c); // ps : 현재 기호의 위치 (positions)
                n = ps.size(); // n : 현재 기호의 개수
                isc = ps.get(0);
                iec = ps.get(n - 1);
                if (iscp != -1 && isc - iscp < 2) {
                    return INVALID; // [X] 서로 다른 두 기호가 연달아 존재
                }
                /* 기호의 규칙 판단 */
                if (n == 1 || n >= 3) {
                    if (n >= 3) {
                        for (int i = 1; i < n; i++) {
                            if (ps.get(i) - ps.get(i - 1) != 2) {
                                return INVALID; // [X] 규칙1 예외 - 간격 2 아님
                            }
                        }
                    }
                    r = 1;
                } else {
                    d = iec - isc;
                    if (d == 2 && (iscp < isc && iec < iecp)) {
                        r = 1;
                    } else if (d >= 2) {
                        r = 2;
                    } else {
                        return INVALID; // [X] 규칙2 예외 - 간격 2보다 작음 (한 기호가 연달아 존재)
                    }
                }
                // else {}
                /* 규칙에 따른 예외 처리 */
                if (r == 1) {
                    Word_Start_Index = isc - 1;
                    Word_End_Index = iec + 1;
                    if (iswp < Word_Start_Index && Word_End_Index < iewp) {
                        if (isc - iscp == 2 && iecp - iec == 2) {
                            continue;
                        } else {
                            return INVALID; // [X] 규칙1 예외 - 간격 안 맞음
                        }
                    }
                } else {
                    Word_Start_Index = isc;
                    Word_End_Index = iec;
                    if (iswp < Word_Start_Index && Word_End_Index < iewp) {
                        return INVALID; // [X] 똑같은 규칙 중복
                    }
                }
                // else {}
                if (iewp >= Word_Start_Index) {
                    return INVALID; // [X] 단어 간격 안 맞음
                }
                /* 단어 단위로 공백 넣어 문자열 생성 */
                if (str_Start_Index < Word_Start_Index) {
                    sb.append(sentence, str_Start_Index, Word_Start_Index).append(" ");
                }
                sb.append(sentence, Word_Start_Index, Word_End_Index + 1).append(" ");
                iswp = Word_Start_Index;
                iewp = Word_End_Index;
                iscp = isc;
                iecp = iec;
                str_Start_Index = Word_End_Index + 1;
            }
            if (str_Start_Index < len) {
                sb.append(sentence, str_Start_Index, len);
            }
            String str = sb.toString();
            for (char c : hm.keySet()) {
                str = str.replace(c + "", "");
            }
            return str.trim();
        } catch (Exception e) {
            return INVALID; // [X] 그 밖의 예외
        }


    }


}
