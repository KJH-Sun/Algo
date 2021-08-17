package Programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class 방금그곡 {

    public static void main(String[] args) {
        System.out.println(solution("CC#BCC#BCC#BCC#B",
            new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
    }

    static Function<String, Integer> stoi = Integer::parseInt;

    public static String solution(String m, String[] musicinfos) {
        m = changeMelody(m);
        int mLen = m.length();
        List<Music> musics = new ArrayList<>();
        for (String musicInfo : musicinfos) {
            String[] info = musicInfo.split(",");
            int playTime = calcTime(info[0], info[1]);
            musics.add(new Music(playTime, info[2], createMelody(playTime, changeMelody(info[3]))));
        }
        List<Music> result = new ArrayList<>();
        for (Music music : musics) {
            int nowLen = music.melody.length();
            if (nowLen < mLen) {
                continue;
            }
            for (int i = 0; i < nowLen - mLen; i++) {
                if (music.melody.substring(i, mLen + i).equals(m)) {
                    result.add(music);
                    break;
                }
            }
        }
        if (result.isEmpty()) {
            return "(None)";
        }
        result.sort((o1, o2) -> o2.playTime - o1.playTime);
        return result.get(0).musicName;
    }

    private static int calcTime(String start, String end) {
        String[] sTime = start.split(":");
        String[] eTime = end.split(":");
        int h = stoi.apply(eTime[0]) - stoi.apply(sTime[0]);
        int m = stoi.apply(eTime[1]) - stoi.apply(sTime[1]);
        return h * 60 + m;
    }

    private static String createMelody(int playTime, String melody) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < playTime; i++) {
            char c = melody.charAt(i % melody.length());
            sb.append(c);
        }
        return sb.toString();
    }

    private static String changeMelody(String oldMelody) {
        oldMelody = oldMelody.replaceAll("C#", "H");
        oldMelody = oldMelody.replaceAll("D#", "I");
        oldMelody = oldMelody.replaceAll("F#", "J");
        oldMelody = oldMelody.replaceAll("G#", "K");

        return oldMelody.replaceAll("A#", "L");
    }

    static class Music {

        int playTime;
        String musicName;
        String melody;

        public Music(int playTime, String musicName, String melody) {
            this.playTime = playTime;
            this.musicName = musicName;
            this.melody = melody;
        }
    }
}
