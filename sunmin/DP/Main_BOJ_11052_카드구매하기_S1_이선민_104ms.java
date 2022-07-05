package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11052_카드구매하기_S1_이선민_104ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] p = new int[N + 1];	// 카드팩 가격
		int[] dp = new int[N + 1];	// 해당 인덱스 개수의 카드를 살때 최대비용 저장
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i] = Math.max(dp[i], p[j] + dp[i - j]);
			}
		}

		System.out.println(dp[N]);
	} // end of main

} // end of class
