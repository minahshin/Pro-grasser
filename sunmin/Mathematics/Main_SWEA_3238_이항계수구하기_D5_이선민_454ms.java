package Mathematics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 페르마의 소정리 사용
 *
 * 0 ≤ r ≤ n ≤ 10^18, 2 ≤ p ≤ 10^5, p는 소수
 */
public class Main_SWEA_3238_이항계수구하기_D5_이선민_454ms {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for (int test_case = 1; test_case <= TC; test_case++) {
			sb.append("#").append(test_case).append(" ");
			st = new StringTokenizer(br.readLine(), " ");
			long n = Long.parseLong(st.nextToken());
			long r = Long.parseLong(st.nextToken());
			int p = Integer.parseInt(st.nextToken());

			if (r == 0 || n == r) {
				sb.append(1).append("\n");
				continue;
			} else if (r == 1 || r == n - 1) {
				sb.append(n % p).append("\n");
				continue;
			}
			long[] factorial = new long[p + 1];
			factorial[0] = 1;
			for (int i = 1; i < p; i++) {
				factorial[i] = (factorial[i - 1] * i) % p;
			}

			long ans = 1;
			if (n < p) {
				ans = (ans * factorial[(int) n]) % p;
				ans = (ans * power(factorial[(int) (n - r)], p - 2, p)) % p;
				ans = (ans * power(factorial[(int) r], p - 2, p)) % p;
			} else {
				// 뤼카의 정리
				while (n > 0 || r > 0) {
					long a = n % p;
					long b = r % p;

					if (b > a) {
						ans = 0;
						break;
					}

					// 페르마의 소정리
					ans = (ans * factorial[(int) a]) % p;
					ans = (ans * power(factorial[(int) (a - b)], p - 2, p)) % p;
					ans = (ans * power(factorial[(int) b], p - 2, p)) % p;

					n /= p;
					r /= p;
				}
			}

			sb.append(ans % p).append("\n");
		} // end of test_case
		System.out.println(sb.toString());
	} // end of main

	private static long power(long a, int x, int p) {
		if (x == 0) {
			return 1;
		}

		long half = power(a, x / 2, p);
		long calc = (half * half) % p;
		if (x % 2 == 0) {
			return calc;
		} else {
			return (calc * a) % p;
		}

	} // end of fermat
} // end of class
