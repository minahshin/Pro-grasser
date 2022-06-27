package boj_0405;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_11726_2xn타일링_S3_신민아_80ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = 10007;
		
		int[] dp = new int[Integer.parseInt(br.readLine()) + 1];
		dp[0] = 1;
		dp[1] = 1;
		
		for(int i=2;i<dp.length;i++) {
			dp[i] = ((dp[i-2] % m) + (dp[i-1] % m)) % m;
		}
		
		System.out.print(dp[dp.length-1]);
	}

}
