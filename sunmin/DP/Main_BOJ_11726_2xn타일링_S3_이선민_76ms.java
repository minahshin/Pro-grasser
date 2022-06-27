package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_11726_2xn타일링_S3_이선민_76ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // (1 ≤ n ≤ 1,000)
		final int MOD = 10007;
		int[] dp = new int[1001];
		if (n == 1) {
			System.out.println(1);
			return;
		}
		if (n == 2) {
			System.out.println(2);
			return;
		}

		dp[1] = 1;
		dp[2] = 2;
//		타일을 채울수 있는 최소 단위가 가로1칸(2*1) or 가로 2칸짜리(1*2) 블록.
//		한쪽 끝에 가로1칸 블록을 고정해둘 경우, 남은 칸을 채울 수 있는 방법은 f(n-1)이 되고
//		한쪽 끝에 가로2칸 블록을 고정해둘 경우, 남은 칸을 채울 수 있는 방법은 f(n-2)가 된다.
//		ex)
//		n=2 ; "ll", "="
//		n=3 ; "lll", "=l", "l="
//		n=4 ; "llll", "=ll", "l=l", "ll=", "=="
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] % MOD + dp[i - 2] % MOD;
		}
		System.out.println(dp[n] % 10007);
	} // end of main
}
