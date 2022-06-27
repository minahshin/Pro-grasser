

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_11726_2xn타일링_S3_양소정_84ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[1001];
	        dp[1] = 1;
	        dp[2] = 2;
	        for (int i = 3; i <= n; i++)
	            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;

	        System.out.println(dp[n]);
		
	}

}
