package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2193_이친수_S3_이선민_80ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // (1 ≤ N ≤ 90)
		if(n==1) {
			System.out.println(1);
			return;
		}
		
		if(n==2) {
			System.out.println(1);
			return;
		}
		
		long[] dp = new long[n + 1];
		dp[1] = 1;
		dp[2] = 1;
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		System.out.println(dp[n]);
	} // end of main

}	// end of class
