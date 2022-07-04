package String;

class Solution_문자열압축 {
	public int solution(String s) {
		int len = s.length();
		int answer = len;
		for (int i = 1; i <= len / 2; i++) {
			String temp = s.substring(0, i); // 현재 문자열
			String res = ""; // 압축한 문자열
			int cnt = 1; // 압축한 문자 개수

			for (int j = i; j <= len; j += i) {
				// 비교할 다음 문자열
				String next = s.substring(j, j + i > len ? len : j + i);
				if (temp.equals(next)) {
					cnt++;
					continue;
				} else {
					if (cnt == 1) {
						res += temp;
					} else {
						res += Integer.toString(cnt) + temp;
					}
					cnt = 1;
					temp = next;
				}
			}

			res += temp; // 마지막 문자열 추가
//			System.out.println(i + ": " + res);
			if (res.length() < answer) {
				answer = res.length();
			}
		}

		return answer;
	}
}