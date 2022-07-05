package dp0415;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11055_S2_가장큰증가부분수열_양소정_100ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		int[] LIS = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] =Integer.parseInt(st.nextToken());
		}

		int ans =0;
	
		
		for (int i = 1; i <= N; i++) {
			LIS[i] = arr[i];
			for (int j = 1; j < i; j++) {
				if(arr[j]<arr[i] && LIS[i] < LIS[j]+arr[i]) {
					LIS[i] = LIS[j] + arr[i];
				}
			}
			if(ans < LIS[i]) ans = LIS[i];
			
		}
		System.out.println(ans);
	
	}
}
