import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


/*
 * 리스트를 한번 순회하면서 Enter나 Change로 들어온 문장의 경우에는 Map에 아이디와 닉네임을 변경하여 넣는다.
 * Map에서 닉네임을 꺼내와서 문자열 구성
 */

public class 오픈채팅방 {
	public static void main(String[] args) {
		String[] l = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" };
		System.out.println(solution(l));
	}

	public static String[] solution(String[] record) {
		Map<String, String> nickName = new HashMap<>();
		StringTokenizer st;
		for (String s : record) {
			st = new StringTokenizer(s);
			String action = st.nextToken();
			if (action.equals("Enter") || action.equals("Change")) {
				nickName.merge(st.nextToken(), st.nextToken(), (exist, init) -> init);
			}
		}
		List<String> result = new ArrayList<>();
		for (String s : record) {
			st = new StringTokenizer(s);
			String action = st.nextToken();
			if (action.equals("Enter")) {
				result.add(nickName.get(st.nextToken()) + "님이 들어왔습니다.");
			} else if (action.equals("Change")) {
				continue;
			} else {
				result.add(nickName.get(st.nextToken()) + "님이 나갔습니다.");
			}
		}

		return result.toArray(new String[result.size()]);
	}

}