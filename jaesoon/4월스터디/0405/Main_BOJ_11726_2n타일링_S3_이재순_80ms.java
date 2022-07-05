import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_11726_2n타일링_S3_이재순_80ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] fibonacci = new int [n+1];
		fibonacci[0] = fibonacci[1] = 1;
		for (int i = 2; i <= n; i++) {
			fibonacci[i] = fibonacci[i-2]+fibonacci[i-1];
			if (fibonacci[i]>1000000000) 
				fibonacci[i]%=10007;
		}
		System.out.println(fibonacci[n]%10007);
	}
}
