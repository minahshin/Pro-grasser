package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17404_RGB거리2_G4_이선민_88ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][3];
		int[][] dp = new int[N][3];
		final int INF = 10000;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = Integer.MAX_VALUE; // 집을 칠하는 최소 비용
		for (int k = 0; k < 3; k++) { // RGB 돌아가면서 1번집의 색 고정
			// 1. 1번집 색 설정
			for (int i = 0; i < 3; i++) {
				if (k == i) {
					dp[0][i] = arr[0][i]; // 1번 집의 색
				} else {
					dp[0][i] = INF; // 다른색은 1번집의 색으로 사용되지 못하도록 최대값 대입
				}
			}

			// 2. 2 ~ 마지막집 색 설정
			for (int i = 1; i < dp.length; i++) {
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
				dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
			}

			// 3. dp 최소값 찾기
			for (int i = 0; i < 3; i++) {
				if (i != k) { // 1번집의 색과 마지막집의 색이 다른것중에
					answer = Math.min(answer, dp[N - 1][i]);
				}
			}
		}

		System.out.println(answer);

	} // end of main

} // end of class
