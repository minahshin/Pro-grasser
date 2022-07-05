package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_11057_오르막수_S1_이선민_76ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()) - 1;
		int[] dp = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		while (N-- > 0) {
			for (int i = 1; i < 10; i++) {
				dp[i] += dp[i - 1];
				dp[i] %= 10007;
			}
		}
		int ans = 0;
		for (int i = 0; i < dp.length; i++) {
			ans += dp[i];
		}
		System.out.println(ans % 10007);
	} // end of main

}
