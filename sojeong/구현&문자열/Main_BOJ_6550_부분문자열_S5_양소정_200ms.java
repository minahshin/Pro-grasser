import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_6550_부분문자열_S5_양소정_200ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input; 
		StringTokenizer st;
		
		
		//BufferedReader는 EOF 내장함수 없음
		while((input = br.readLine()) != null) {
			st = new StringTokenizer(input);
			
			String s = st.nextToken();
			String t = st.nextToken();
			
			int cnt=0;
			String result = "No"; //기본 No로 설정
			
			for (int i = 0; i < t.length(); i++) {
				if(t.charAt(i)==s.charAt(cnt)) {
					cnt++;
				}
					
				if(cnt==s.length()) { 
					result = "Yes";
					break;
				}		
			}
			sb.append(result).append("\n");
			
		}//end of while
		System.out.println(sb.toString());

	}//end of main	
}//end of class
