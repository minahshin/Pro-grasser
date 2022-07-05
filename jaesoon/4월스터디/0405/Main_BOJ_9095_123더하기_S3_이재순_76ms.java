import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_9095_123더하기_S3_이재순_76ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] arr = new int[T];
		int max = 0;
		for (int i = 0; i < T; i++) {
			arr[i] = Integer.parseInt(br.readLine()); 
			if (max<arr[i]) 
				max = arr[i];
		}
		int[] ans = new int[Math.max(max+1, 4)];
		ans[1] = 1;
		ans[2] = 2;
		ans[3] = 4;
		for (int i = 4; i <= max; i++) {
			ans[i] = ans[i-3]+ans[i-2]+ans[i-1];
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) 
			sb.append(ans[arr[i]]).append("\n");
		System.out.print(sb);
	}
}
