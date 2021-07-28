package Programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 매칭점수 {

    public static void main(String[] args) {
        System.out.println(solution("Muzi", new String[]{
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n"
                + "  <meta charset=\"utf-8\">\n"
                + "  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n"
                + "</head>  \n"
                + "<body>\n"
                + "<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n"
                + "\n"
                + "</body>\n"
                + "</html>",
            "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n"
                + "  <meta charset=\"utf-8\">\n"
                + "  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n"
                + "</head>  \n"
                + "<body>\n"
                + "con%    muzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n"
                + "\n"
                + "    ^\n"
                + "</body>\n"
                + "</html>"
        }));
    }

    static Map<String, Page> map = new HashMap<>();

    public static int solution(String word, String[] pages) {
        int idx = 0;

        for (String html : pages) {
            Page page = new Page(idx++, html.toLowerCase());
            page.setBaseScore(word.toLowerCase());
            page.setLinkCount();
            map.put(page.url, page);
        }
        for (Page page : map.values()) {
            page.setLinkScore();
        }
        List<Page> list = new ArrayList<>(map.values());
        Collections.sort(list);
        return list.get(0).idx;
    }

    static class Page implements Comparable<Page> {

        int idx;
        int baseScore = 0;
        int linkCnt = 0;
        double linkScore = 0;
        String html;
        String url;

        Page(int idx, String html) {
            this.idx = idx;
            this.html = html;
            findURL();
        }

        private void findURL() {
            Pattern pattern = Pattern
                .compile("<meta property=\"og:url\" content=\"https://(.+?)\"/>");
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                this.url = matcher
                    .group(1); // () 로 그룹 지정, 이 경우에는 https://과 " 사이에 있는 임의의 문자열을 (.+?) 뽑음.
            }
        }

        public void setBaseScore(String word) {
            int idx = html.indexOf(word); // 존재하지 않으면 -1 반환
            while (idx != -1) {
                char pre = html.charAt(idx - 1); // 단어 앞뒤로 Character인지 체크
                char post = html.charAt(idx + word.length());

                if (!Character.isLowerCase(pre) && !Character
                    .isLowerCase(post)) {// 앞뒤로 알파벳 소문자인지 확인, page 객체 생성시에 lowercase로 넣어서 대문자는 없음
                    baseScore++;
                }
                idx = html.indexOf(word, idx + 1); // 그 다음 인덱스부터 찾기
            }
        }

        public void setLinkCount() {
            int idx = html.indexOf("<a href="); // 존재하지 않으면 -1 반환
            while (idx != -1) {
                linkCnt++;
                idx = html.indexOf("<a href=", idx + 1);
            }
        }

        public void setLinkScore() {
            Pattern pattern = Pattern.compile("<a href=\"https://(.+?)\">");
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                String hyperLink = matcher.group(1);
                if (map.containsKey(hyperLink)) {
                    map.get(hyperLink).linkScore += (double) baseScore / linkCnt;
                }
            }
        }

        @Override
        public int compareTo(Page o) {

            return Double.compare(o.baseScore + o.linkScore, this.baseScore + this.linkScore);
        }
    }


}
