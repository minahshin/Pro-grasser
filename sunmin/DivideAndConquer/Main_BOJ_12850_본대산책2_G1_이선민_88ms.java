package DivideAndConquer;

import java.io.*;

public class Main_BOJ_12850_본대산책2_G1_이선민_88ms {
	static final int MOD = 1000000007;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long D = Long.parseLong(br.readLine());
		// System.out.println(D);

		long[][] matrix = { { 0, 1, 1, 0, 0, 0, 0, 0 }, { 1, 0, 1, 1, 0, 0, 0, 0 }, { 1, 1, 0, 1, 1, 0, 0, 0 },
				{ 0, 1, 1, 0, 1, 1, 0, 0 }, { 0, 0, 1, 1, 0, 1, 1, 0 }, { 0, 0, 0, 1, 1, 0, 0, 1 },
				{ 0, 0, 0, 0, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0, 1, 1, 0 } };

		matrix = pow(matrix, D);
		System.out.print(matrix[0][0]);

	} // end of main

	/** m1, m2 두 행렬을 곱한 결과(행렬)을 반환 */
	public static long[][] mul(long[][] m1, long[][] m2) {
		long[][] res = new long[8][8];

		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m2[0].length; j++) {
				for (int k = 0; k < m1[0].length; k++) {
					res[i][j] = (res[i][j] + m1[i][k] * m2[k][j]) % MOD;
				}
			}
		}

		return res;
	} // end of mul

	/** m 행렬을 D번 거듭제곱 */
	public static long[][] pow(long[][] m, long D) {
		if (D == 1) {
			return m;
		}

		// 분할정복
		if (D % 2 == 0) {
			return pow(mul(m, m), D / 2);
		} else {
			return mul(m, pow(mul(m, m), D / 2));
		}
	} // end of pow
} // end of class
