package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_14938_서강그라운드_G4_이선민_112ms {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken()); // 지역의 개수
		int m = Integer.parseInt(st.nextToken()); // 예은이의 수색범위
		int r = Integer.parseInt(st.nextToken()); // 길의 개수
		int[] items = new int[n + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		int[][] adjMatrix = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(adjMatrix[i], 123456789);
		}

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			// 양방향 이동 가능
			adjMatrix[from][to] = w;
			adjMatrix[to][from] = w;
		}

		// 플로이드-워셜
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				if (i == k)
					continue;
				for (int j = 1; j <= n; j++) {
					if (j == k || j == i)
						continue;
					if (adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]) {
						adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
					}
				}
			}
		}

		// 최대 아이템 개수 찾기
		int max = 0;
		for (int i = 1; i <= n; i++) {
			int temp = items[i];
			for (int j = 1; j <= n; j++) {
				if (adjMatrix[i][j] <= m) {
					temp += items[j];
				}
			}
			if (max < temp) {
				max = temp;
			}
		}
		
		System.out.println(max);
	} // end of main

}
