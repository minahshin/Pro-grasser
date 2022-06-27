package boj_0405;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_9095_123더하기_S3_신민아 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// basis 설정
		int[] dp = new int[11];
		dp[0] = 1; // 0을 만드는 경우의 수는 1
		dp[1] = 1; // 1을 만드는 경우의 수는 1
		dp[2] = 2; // 2를 만드는 경우의 수는 2
		
		for(int i=3;i<dp.length;i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int t=0;t<TC;t++) {			
			sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
		}
		
		
		System.out.print(sb.toString());
	}

}
