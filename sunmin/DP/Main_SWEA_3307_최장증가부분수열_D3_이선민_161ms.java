package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_SWEA_3307_최장증가부분수열_D3_이선민_161ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= TC; test_case++) {
			int n = Integer.parseInt(br.readLine()); // 수열의 길이
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] arr = new int[n];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int[] LIS = new int[n];	// 해당 인덱스까지의 최장 증가 부분수열의 길이
			Arrays.fill(LIS, 1);	// 자기 자신
			for (int i = 1; i < arr.length; i++) {
				for (int j = 0; j < i; j++) {
					if (arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {
						LIS[i] = LIS[j] + 1;
					}
				}
			}

			int max = 0;
			for (int i = 0; i < LIS.length; i++) {
				if (max < LIS[i]) {
					max = LIS[i];
				}
			}

			sb.append("#").append(test_case).append(" ").append(max).append("\n");
		} // end of test_case
		System.out.println(sb.toString());
	} // end of main
} // end of class
