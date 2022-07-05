package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_11727_2xn타일링2_S3_이선민_76ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[1001];
		dp[1] = 1;
		dp[2] = 3;
//		2x2 타일이 추가됐으므로
//		오른쪽에 l를 고정하는 방법 -> f(i-1)
//		오른쪽에 =를 고정하는 방법 -> f(i-2)
//		오른쪽에 ㅁ를 고정하는 방법 -> f(i-2)
//		--> f(i-1) + f(i-2) * 2
		for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007;
		}
		System.out.println(dp[n]);
	} // end of main

}
