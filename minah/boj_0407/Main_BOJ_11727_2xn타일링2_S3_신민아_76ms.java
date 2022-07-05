package boj_0407;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_11727_2xn타일링2_S3_신민아_76ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = 10007;
		
		int[] dp = new int[Integer.parseInt(br.readLine()) + 1];
		dp[0] = 1;
		dp[1] = 1;

		for(int i=2;i<dp.length;i++) {
			dp[i] = (dp[i-1] + 2*dp[i-2]) % m;
		}
		
		System.out.print(dp[dp.length-1]);
	}

}
