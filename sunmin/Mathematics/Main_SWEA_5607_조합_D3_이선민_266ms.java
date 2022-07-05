package Mathematics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N의 범위가 백만까지이기 때문에 페르마의 소정리 사용
 *
 */
public class Main_SWEA_5607_조합_D3_이선민_266ms {
	static final int MOD = 1234567891;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for (int test_case = 1; test_case <= TC; test_case++) {
			sb.append("#").append(test_case).append(" ");
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			long[] factorial = new long[n + 1];
			factorial[1] = 1;
			for (int i = 2; i < factorial.length; i++) { // N! 구하기
				factorial[i] = (factorial[i - 1] * i) % MOD;
			}

			long denominator = (factorial[r] * factorial[n - r]) % MOD;

			sb.append(factorial[n] * fermat(denominator, MOD - 2) % MOD).append("\n");
		} // end of test_case
		System.out.println(sb.toString());
	} // end of main

	/**
	 * a^(p-2) = 1/a (mod p) 이용
	 */
	private static long fermat(long a, int x) {
		if (x == 0) {
			return 1;
		}

		// 이진탐색 사용
		long half = fermat(a, x / 2);
		long calc = (half * half) % MOD;
		if (x % 2 == 0) {
			return calc;
		} else {
			return (calc * a) % MOD;
		}

	} // end of fermat

}
