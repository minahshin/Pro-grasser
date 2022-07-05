package march0329;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_서강그라운드_G4_양소정_100ms{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //지역수
		int M = Integer.parseInt(st.nextToken()); //수색범위
		int R = Integer.parseInt(st.nextToken()); //길의수
		int []arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//인접행렬
		int [][] map = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(i==j) continue;
                map[i][j] = 987654321;
            }
        }
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
            int to= Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
		
            map[from][to] = w; 
            map[to][from] = w;
		}
		//플로이드
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <=N; i++) {
				if(i==k) continue;
				for (int j = 1; j <= N; j++) {
					if(map[i][j]>map[i][k]+map[k][j]) {
						map[i][j] = map[i][k]+map[k][j];
					}
				}
			}
		}
		int ans = 0;
		int sum=0;
		
		//탐색 범위 내의 거리면 더해주기
		for (int i = 1; i <= N; i++) {
			sum =0;
			for (int j = 1; j <= N; j++) {
				if(map[i][j]<=M) sum+=arr[j];
			}
			ans = Math.max(sum, ans);
		}
		
		System.out.println(ans);
	}
	

}
