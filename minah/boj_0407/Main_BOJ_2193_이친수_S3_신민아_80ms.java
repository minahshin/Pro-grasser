package boj_0407;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_2193_이친수_S3_신민아_80ms {
	
	// 점화식 : f(n-1) + f(n-2)
	/*
	 * 규칙 : 이친수는 반드시 10으로 시작하므로 10 뒤에있는 숫자들을 정해주면 됨
	 * ex) f(4)일 때 10--(뒤에 2자리)를 정해주면 됨
	 * 이 때 들어올 수 있는 경우의 수는 00, 01, 10으로
	 * f(2) : 10, f(3) : 100, 101이며 n-2, n-1의 오른쪽으로부터 2자리를 떼어주면 동일
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long[] dp = new long[Integer.parseInt(br.readLine()) + 1];
		dp[1] = 1;

		for(int i=2;i<dp.length;i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		System.out.print(dp[dp.length-1]);
	}

}
