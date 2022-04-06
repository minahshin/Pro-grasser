package com.ssafy.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1149_RGB거리_S1_윤성도_88ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[2][3];
		int prev = 1, cur = 0;
		StringTokenizer st;
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 3; ++i)
				dp[cur][i] = Math.min(dp[prev][(i + 1) % 3], dp[prev][(i + 2) % 3]) + Integer.parseInt(st.nextToken());
			prev^=1;
			cur^=1;
		}
		System.out.print(Math.min(Math.min(dp[prev][0], dp[prev][1]), dp[prev][2]));
	}
}
