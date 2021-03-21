import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class 전화번호목록 {
	static String[] phone_book = { "119", "97674223", "1195524421" };

	public static void main(String[] args) throws IOException {
		System.out.println(solution(phone_book));
	}

	public static boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });

        HashMap<String, String> hm = new HashMap<>();
        for (String str : phone_book) {
            if (hm.get(str) != null) {
                return false;
            }
            for (int i = 1, len = str.length() ; i <= len; i++) {
                hm.put(str.substring(0, i), "");
            }
        };
        return true;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
