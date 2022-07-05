package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_Main_1865_동철이의일분배_D4_이선민_1606ms {

	private static double[][] percents;
	private static double max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력값 읽어오기
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= TC; test_case++) {
			sb.append("#").append(test_case).append(" ");
			int N = Integer.parseInt(br.readLine());
			percents = new double[N][N]; // 일을 성공할 확률
			for (int i = 0; i < percents.length; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < percents.length; j++) {
					percents[i][j] = Double.parseDouble(st.nextToken()) / 100;
				}
			}

			max = 0;
			permutation(0, N, 0, 1);

			sb.append(String.format("%.6f", max * 100)).append("\n");
		} // end of test_case
		System.out.println(sb.toString());
	} // end of main

	private static void permutation(int cnt, int N, int flag, double temp) {
		if (cnt == N) {
			if (temp > max) {
				max = temp;
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0) {
				continue;
			}

			double ntemp = temp * percents[cnt][i];
			if (ntemp < max || ntemp == 0) {
				continue;
			}
			permutation(cnt + 1, N, flag | 1 << i, ntemp);
		}

	} // end of permutation

} // end of class
