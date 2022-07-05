package dp0407;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//long조심
public class Main_BOJ_2193_이천수_S3_양소정_80ms{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] dp = new long[91];
		dp[1] = 1;
		dp[2] = 1;
	
	    for (int i = 3; i <=N ; i++) {
			dp[i] = dp[i-1]+dp[i-2];
		}
	        System.out.println(dp[N]);
		
	}

}
