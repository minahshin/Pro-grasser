package dp0412;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11052_카드구매하기_S1_양소정_100ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			dp[i] = Integer.parseInt(st.nextToken());
		}
		
	
	    for (int i =2 ; i <=N ; i++) {
	    	for (int j = 1; j < i; j++) {
				
	    		dp[i] = Math.max(dp[i-j]+dp[j],dp[i]);
			}
		}
	        System.out.println(dp[N]);
		
	}

}
