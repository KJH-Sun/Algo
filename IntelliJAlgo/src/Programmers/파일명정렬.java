package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class 파일명정렬 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(
            new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF",
                "img2.JPG"})));
    }

    static Function<String, Integer> stoi = Integer::parseInt;

    public static String[] solution(String[] files) {
        List<File> fileList = new ArrayList<>();
        for (String file : files) {
            fileList.add(new File(file));
        }
        Collections.sort(fileList);
        String[] answer = new String[fileList.size()];
        int idx = 0;
        for (File f : fileList) {
            answer[idx++] = f.getOrigin();
        }
        return answer;
    }

    static class File implements Comparable<File> {

        String origin;
        String head;
        int number;
        String tail;

        public File(String origin) {
            this.origin = origin;
            parse(origin);
        }

        private String getOrigin() {
            return this.origin;
        }

        private void parse(String origin) {
            StringBuilder sh = new StringBuilder();
            StringBuilder sn = new StringBuilder();
            int numIdx = 0;
            int tailIdx = 0;
            int oriLen = origin.length();
            for (int i = 0; i < oriLen; i++) {
                char c = origin.charAt(i);
                if (c - '0' <= 9 && c - '0' >= 0) {
                    numIdx = i;
                    break;
                }
                sh.append(c);
            }
            head = sh.toString().toLowerCase();
            for (int i = numIdx; i < oriLen; i++) {
                char c = origin.charAt(i);
                if (c - '0' <= 9 && c - '0' >= 0) {
                    sn.append(c);
                } else {
                    tailIdx = i;
                    break;
                }
            }
            number = stoi.apply(sn.toString());
            if (tailIdx < oriLen) {
                tail = origin.substring(tailIdx, oriLen);
            } else {
                tail = "";
            }
        }

        @Override
        public int compareTo(File o) {
            return this.head.compareTo(o.head) == 0 ? this.number - o.number
                : this.head.compareTo(o.head);
        }
    }


}
