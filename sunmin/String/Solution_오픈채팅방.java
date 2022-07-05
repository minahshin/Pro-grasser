package String;

import java.util.*;

public class Solution_오픈채팅방 {
	public static String[] solution(String[] record) {
		String[] answer;
		StringTokenizer st;
		Map<String, String> user = new HashMap<String, String>();
		List<Pair> inout = new ArrayList<>();
		for (int i = 0; i < record.length; i++) {
			st = new StringTokenizer(record[i]);
			String act = st.nextToken(); // 유저의 행동
			String id = st.nextToken(); // 유저아이디
			if (act.equals("Enter")) {
				inout.add(new Pair(id, 1));
			} else if (act.equals("Leave")) {
				inout.add(new Pair(id, 2));
				continue;
			}
			String nickname = st.nextToken(); // 닉네임
			user.put(id, nickname);
		}

		int size = inout.size();
		answer = new String[size];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(user.get(inout.get(i).id)).append("님이 ");
			if (inout.get(i).inout == 1) { // 입장
				sb.append("들어왔습니다.");
			} else { // 퇴장
				sb.append("나갔습니다.");
			}

			answer[i] = sb.toString();
			sb.setLength(0);
		}
		return answer;
	}

	public static void main(String[] args) {
		String[] record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" };
		for (String string : solution(record)) {
			System.out.println(string);
		}
//		System.out.println(solution(record));
	}

}

class Pair {
	String id; // 유저 아이디
	int inout; // 입장:1, 퇴장:2

	public Pair(String id, int inout) {
		super();
		this.id = id;
		this.inout = inout;
	}
}
