package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2293_동전1_G5_이선민_100ms {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()); // 동전의 종류
		int k = Integer.parseInt(st.nextToken()); // 목표 금액
		int[] dp = new int[k + 1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			int coin = Integer.parseInt(br.readLine());
			for (int j = coin; j < dp.length; j++) {
				dp[j] += dp[j - coin];
			}
		}

		System.out.println(dp[k]);

	} // end of main

}	// end of class
