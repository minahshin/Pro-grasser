package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11055_가장큰증가부분수열_S2_이선민_104ms {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 수열의 크기
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[2][N]; // 0행 : 이전 수열의 인덱스 번호, 1행 : 지금까지의 합
		dp[1][0] = arr[0];
		for (int i = 1; i < N; i++) {
			int temp = 0; // 지금까지의 증가수열 합 중 가장 큰값
			int index = -1; // temp 값을 가지고있는 인덱스 번호
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && temp < dp[1][j]) {
					temp = dp[1][j];
					index = j;
				}
			}
			dp[0][i] = index;
			dp[1][i] = arr[i] + temp;
		}

		int max = 0;
		for (int i = 0; i < N; i++) {
			if (max < dp[1][i]) {
				max = dp[1][i];
			}
		}

		System.out.println(max);

	} // end of main

}
