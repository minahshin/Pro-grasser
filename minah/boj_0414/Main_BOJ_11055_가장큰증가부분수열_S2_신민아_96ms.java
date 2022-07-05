package boj_0414;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// idea: 인덱스 별로 검사할 때 i번째 인덱스를 기준으로 0부터 i-1까지 매번 검색 -> O(n^2)에 수렴
public class Main_BOJ_11055_가장큰증가부분수열_S2_신민아_96ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		
		int[] original = new int[count]; // 원래의 데이터가 있는 배열
		int[] maxSum = new int[count]; // 가장 합이 큰 증가하는 부분 수열
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0;i<count;i++) {
			original[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0; // 합이 가장 큰 부분 수열의 값
		for(int i=0;i<count;i++) {
			maxSum[i] = original[i]; // 현재 인덱스에 있는 값으로 초기화
			for(int j=0;j<i;j++) { // 이전 값들과의 비교
				
				// 현재 위치의 수가 그 전의 위치보다 작고 && 현재의 Sum값이 이전의 Sum값에 현재 위치의 수를 더한 값보다 작으면
				if(original[j] < original[i] && maxSum[i] < maxSum[j] + original[i]) {
					maxSum[i] = maxSum[j] + original[i];
				}
			}
			if(max < maxSum[i]) max = maxSum[i]; // 가장 큰 값이 바뀔 때 갱신
		}
		
		System.out.print(max);
	}

}
