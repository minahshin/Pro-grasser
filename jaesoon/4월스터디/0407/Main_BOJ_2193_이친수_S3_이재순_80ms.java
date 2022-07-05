import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_2193_이친수_S3_이재순_80ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Math.max(Integer.parseInt(br.readLine()),2);
		long[] fibonacci = new long[n];
		fibonacci[0] = fibonacci[1] = 1;
		for (int i = 2; i < n; i++) {
			fibonacci[i] = fibonacci[i-2]+fibonacci[i-1];
		}
		System.out.println(fibonacci[n-1]);
	}
}
