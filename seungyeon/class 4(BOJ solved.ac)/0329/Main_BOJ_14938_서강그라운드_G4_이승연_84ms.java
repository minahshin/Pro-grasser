import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14938_서강그라운드_G4_이승연_84ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken()); // 지역의 개수 (1<=n<=100)
		int m = Integer.parseInt(st.nextToken()); // 수색범위 (1<=m<=15)
		int r = Integer.parseInt(st.nextToken()); // 길의 개수 (1<=r<=100)
		
		int[] items = new int[n+1]; // 각 구역에 있는 아이템의 수 
		
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=1; i<=n; i++) { 
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] field = new int[n+1][n+1];
		
		for(int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			field[a][b] = w;
			field[b][a] = w;
		}
		
		for(int k=1; k<=n; k++) { // 경유지 
			for(int i=1; i<=n; i++) { // 출발지 
 				if(i == k || field[i][k] == 0) continue; 
				for(int j=1; j<=n; j++) { // 도착지 
					if(i == j || field[k][j] == 0) continue; 
					if(field[i][j] == 0 || field[i][j] > field[i][k] + field[k][j]) {
						field[i][j] = field[i][k] + field[k][j];
					}
				}
			}
		}

		int result = 0; 
		
		for(int i=1; i<=n; i++) {
			int temp_result = items[i]; 
			for(int j=1; j<=n; j++) {
				if(field[i][j]!=0 && field[i][j]<=m) {
					temp_result += items[j];
				}
			}
			result = Math.max(result, temp_result);
		}
		
		System.out.println(result);
	}
}
