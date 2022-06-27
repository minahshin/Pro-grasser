package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_9095_123더하기_S3_이선민_84ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] dp = new int[11];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
//		ex) n=4
//		4를 만드는 방법은
//		1+3 -> 1+f(3) ; 1에 3을 만드는 방법의 수를 더해줌
//		2+2 -> 2+f(2) ; 2에 2를 만드는 방법의 수를 더해줌
//		3+1 -> 3+f(1) ; 3에 1을 만드는 방법의 수를 더해줌
//		--> dp[4] = f(3)+f(2)+f(1) = 7
//		
//		ex) n=5
//		5를 만드는 방법은
//		1+4 -> 1+f(4)
//		2+3 -> 2+f(3)
//		3+2 -> 3+f(2) 
//		4+1 ; 1, 2, 3으로만 합을 만들어야 하므로 이 경우는 따질 수 없음
//		--> dp[5] = f(4)+f(3)+f(2) = 13
		for (int i = 4; i < 11; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}

		for (int test_case = 0; test_case < TC; test_case++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n]).append("\n");
		} // end of test_case
		System.out.println(sb.toString());
	}
}
