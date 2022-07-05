package boj_0414;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// idea : 처음에 2차원 배열로 생각했다가 1차원 배열로 줄이면 생각하기 쉬움
/*
 * 현재 계산하려는 금액을 money라고 가정할 때,
 * 주어진 동전을 사용하여 경우의 수를 찾으며 다음 동전으로 넘어가면, 이전 동전에서 계산한 경우에 수에다가 array[money - 이전 동전의 금액]을 더해준다
 */
public class Main_BOJ_2293_동전1_G5_신민아_92ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int coins = Integer.parseInt(st.nextToken());
		int answer = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[answer+1];
		int[] coin = new int[coins];
		dp[0] = 1;
		
		for(int i=0;i<coin.length;i++) {
			coin[i] = Integer.parseInt(br.readLine());
			for(int j=coin[i];j<dp.length;j++) {
				dp[j] += dp[j - coin[i]];
			}
		}
		
		System.out.print(dp[answer]);
	}

}
