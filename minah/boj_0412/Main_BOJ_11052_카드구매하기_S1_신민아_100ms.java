package boj_0412;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11052_카드구매하기_S1_신민아_100ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cardPackCount = Integer.parseInt(br.readLine()); // 카드 팩의 개수
		int[] maxCardPay = new int[cardPackCount+1]; // 카드 팩을 사는 최대 금액
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=1;i<maxCardPay.length;i++) {
			// 카드 팩을 사는 최대 금액을 f(1)+f(i-1)부터 f(i/2) + f(i/2) 값을 구해 비교한 후 사고자 하는 카드팩의 수와 동일한 카드팩의 가격과 비교하여 큰 값으로 저장
			maxCardPay[i] = Math.max(getMaxFromPrevious(maxCardPay, i), Integer.parseInt(st.nextToken()));
		}
		
		System.out.print(maxCardPay[cardPackCount]);
	}
	
	// 카드 팩을 사는 최대 금액을 f(1)+f(i-1)부터 f(i/2) + f(i/2) 값을 구해 비교하면서 가장 큰 값을 리턴
	private static int getMaxFromPrevious(int[] card, int index) {
		int lastIndex = index / 2 + 1; // 
		int max = 0;
		
		for(int i=0;i<lastIndex;i++) {
			max = Math.max(max, card[i] + card[index-i]);
		}
		
		return max;
	}

}
