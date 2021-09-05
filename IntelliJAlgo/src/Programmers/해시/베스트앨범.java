package Programmers.해시;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 베스트앨범 {

    public static void main(String[] args) {
        System.out.println(
            Arrays.toString(solution(new String[]{"classic", "pop", "classic", "classic", "pop"},
                new int[]{500, 600, 150, 800, 2500})));
    }

    static Map<String, List<Music>> genre = new HashMap<>();
    static List<Genre> genreList = new ArrayList<>();

    public static int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> tmp = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            genre.computeIfAbsent(genres[i], (e) -> new ArrayList<>());
            genre.get(genres[i]).add(new Music(i, plays[i]));
            tmp.putIfAbsent(genres[i], 0);
            tmp.put(genres[i], tmp.get(genres[i]) + plays[i]);
        }
        for (String key : tmp.keySet()) {
            genreList.add(new Genre(key, tmp.get(key)));
        }
        List<Integer> ans = new ArrayList<>();
        Collections.sort(genreList);
        for (Genre g : genreList) {
            List<Music> musics = genre.get(g.name);
            Collections.sort(musics);
            ans.add(musics.get(0).num);
            if (musics.size() >= 2) {
                ans.add(musics.get(1).num);
            }
        }

        return ans.stream().mapToInt(i -> i).toArray();
    }


    static class Genre implements Comparable<Genre> {

        String name;
        int cnt;

        Genre(String name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Genre g) {
            return g.cnt - this.cnt;
        }
    }


    static class Music implements Comparable<Music> {

        int num;
        int playCnt;

        Music(int num, int playCnt) {
            this.num = num;
            this.playCnt = playCnt;
        }

        @Override
        public int compareTo(Music m) {
            return m.playCnt - this.playCnt;
        }
    }
}
