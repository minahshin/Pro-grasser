

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_9095_123더하기_S3_76ms{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
	
		int dp[] = new int [11];
		dp[1] =1; 
		dp[2]=2;
		dp[3]=4;
		
		for(int j=4;j<=10;j++) { // 4부터 반복
			dp[j] = dp[j-3] + dp[j-2] + dp[j-1]; // 점화식
		}
			
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			System.out.println(dp[N]);
			
		}
		
	}

}
