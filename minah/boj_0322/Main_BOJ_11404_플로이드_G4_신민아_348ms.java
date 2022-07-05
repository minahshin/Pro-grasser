package boj_0322;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_11404_플로이드_G4_신민아_348ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());
		int[][] map = new int[size+1][size+1];
		int buses = Integer.parseInt(br.readLine());
		
		int INF = 500000000;
		for(int[] m : map) {
			Arrays.fill(m, INF);
		}
		
		StringTokenizer st;
		for(int i=0;i<buses;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			if(map[start][end] > weight)
				map[start][end] = weight;
		}
		doFloyd(map);
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<map.length;i++) {
			for(int j=1;j<map.length;j++) {
				sb.append(map[i][j] == INF ? 0 : map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
		
	}
	
	private static void doFloyd(int[][] map) {
		for(int m=1;m<map.length;m++) {
			for(int s=1;s<map.length;s++) {
				if(m == s) continue;
				for(int e=1;e<map.length;e++) {
					if(m != e && s != e) {
						map[s][e] = Math.min(map[s][e], map[s][m] + map[m][e]);
					}
				}
			}
		}
	}

}
