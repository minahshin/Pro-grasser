import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1149_RGB거리_S1_이승연_96ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 집의 수 (2<=N<=1000)
		int[] temp_cost = new int[3];
		int[] cost = new int[3];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		temp_cost[0] = Integer.parseInt(st.nextToken());
		temp_cost[1] = Integer.parseInt(st.nextToken());
		temp_cost[2] = Integer.parseInt(st.nextToken());

		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			cost[0] = Math.min(temp_cost[1], temp_cost[2]) + Integer.parseInt(st.nextToken());
			cost[1] = Math.min(temp_cost[0], temp_cost[2]) + Integer.parseInt(st.nextToken());
			cost[2] = Math.min(temp_cost[0], temp_cost[1]) + Integer.parseInt(st.nextToken());
			temp_cost[0] = cost[0];
			temp_cost[1] = cost[1];
			temp_cost[2] = cost[2];
		}
		
		System.out.println(Math.min(Math.min(cost[0], cost[1]), cost[2]));
	}
}
