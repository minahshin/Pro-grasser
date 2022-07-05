import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_JO_1681_해밀턴순환회로_이승연_134ms {
	private static int min;
	private static int temp_sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 배달해야 하는 장소의 수 (1<=N<=13)
		int[][] adjMatrix = new int[N][N];
		
		StringTokenizer st; 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;
		temp_sum = 0; 
		
		dfs(adjMatrix, new boolean[N], N, 0, 1); // 출발지(회사): 1번, 여기서는 0번
		
		System.out.println(min);
	} // end of main
	
	public static void dfs(int[][] adjMatrix, boolean[] visited, int N, int current, int cnt) {
		if(temp_sum > min) return;
		
		if(cnt == N) {
			if(adjMatrix[current][0] != 0 && min>temp_sum+adjMatrix[current][0]) {
				min = temp_sum+adjMatrix[current][0];
			}
			return; 
		}
			
		visited[current] = true;
		
		for(int i=0; i<N; i++) { // i=1부터 해도 됨. 
			if(!visited[i] && adjMatrix[current][i]!=0) {
				temp_sum += adjMatrix[current][i];
				dfs(adjMatrix, visited, N, i, cnt+1);
				temp_sum -= adjMatrix[current][i];
			}
		}
		
		visited[current] = false; 
	}
} // end of class
