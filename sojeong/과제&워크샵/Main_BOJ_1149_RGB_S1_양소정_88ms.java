import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1149_RGB_S1_양소정_88ms{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		int [][] map = new int [N][N];
		st = new StringTokenizer(br.readLine());
		map[0][0] = Integer.parseInt(st.nextToken());
		map[0][1] = Integer.parseInt(st.nextToken());
		map[0][2] = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x0 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			
			map[i][0] = Math.min(map[i-1][1],map[i-1][2]) + x0;
			map[i][1] = Math.min(map[i-1][0],map[i-1][2]) + x1;
			map[i][2] = Math.min(map[i-1][1],map[i-1][0]) + x2;
		
		}
		System.out.println(Math.min(map[N-1][2], Math.min(map[N-1][0], map[N-1][1])));
		
		
		
	}

}
