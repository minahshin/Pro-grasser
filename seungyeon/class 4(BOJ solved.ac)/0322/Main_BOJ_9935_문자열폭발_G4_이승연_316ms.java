import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_9935_문자열폭발_G4_이승연_316ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input_str = br.readLine();
		String explode_str = br.readLine();
		StringBuilder sb = new StringBuilder();
		
		int input_str_length=input_str.length();
		int explode_str_length = explode_str.length();
		
		
		for(int i=0; i<input_str_length; i++) {
			sb.append(input_str.charAt(i));
			
			if(sb.length() >= explode_str_length) {
				if(sb.substring(sb.length()-explode_str_length, sb.length()).equals(explode_str)) {
					sb.delete(sb.length()-explode_str_length, sb.length());
				}
			}
		}
		
		if(sb.length() == 0) {
			System.out.println("FRULA");
		} else {
			System.out.print(sb.toString());
		}
	}
}
