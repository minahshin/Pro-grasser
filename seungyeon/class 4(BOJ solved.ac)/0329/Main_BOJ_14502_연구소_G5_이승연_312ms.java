import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BOJ_14502_연구소_G5_이승연_312ms {
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]> loc_0 = new ArrayList<int[]>();
		ArrayList<int[]> loc_2 = new ArrayList<int[]>();
		int[][] grid = new int[N][M];
		int[] dir_r = {-1, 1, 0, 0};
		int[] dir_c = {0, 0, -1, 1};
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if(grid[i][j] == 0) loc_0.add(new int[] {i, j});
				if(grid[i][j] == 2) loc_2.add(new int[] {i, j});
			}
		}
		
		int n_0 = loc_0.size();
		
		combination(n_0, 0, 0, new int[3], grid, loc_0, loc_2, dir_r, dir_c, N, M);
		
		System.out.println(max);
	}
	 
	private static void combination(int n_0, int cnt, int start, int[] idx, int[][] grid, ArrayList<int[]> loc_0, ArrayList<int[]> loc_2, int[] dir_r, int[] dir_c, int N, int M) {
		if(cnt == 3) {		
			int[][] temp_grid = new int[N][M];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					temp_grid[i][j] = grid[i][j];
				}
			}
			
			// 벽 3개 세우기 
			for(int i=0; i<3; i++) {
				temp_grid[loc_0.get(idx[i])[0]][loc_0.get(idx[i])[1]] = 1;
			}
			
			// 바이러스 퍼짐(2에서 시작해서 bfs나 dfs 돌려서) 
			for(int i=0, size=loc_2.size(); i<size; i++) {
				dfs(temp_grid, dir_r, dir_c, N, M, loc_2.get(i)[0], loc_2.get(i)[1]);
			}
			
			max = Math.max(max, count_0(temp_grid, N, M));

			return;
		}
		
		for(int i=start; i<n_0; i++) { 
			idx[cnt] = i;
			combination(n_0, cnt+1, i+1, idx, grid, loc_0, loc_2, dir_r, dir_c, N, M);
		}
	}
	
	private static void dfs(int[][] grid, int[] dir_r, int[] dir_c, int N, int M, int r, int c) {
		if(r<0 || r>=N || c<0 || c>=M) return;
		
		for(int d=0; d<4; d++) {
			int nr = r+dir_r[d];
			int nc = c+dir_c[d];
			if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
			if(grid[nr][nc] == 0) {
				grid[nr][nc] = 2;
				dfs(grid, dir_r, dir_c, N, M, nr, nc);
			} 
		}
	}
	
	private static int count_0(int[][] grid, int N, int M) {
		int result = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(grid[i][j] == 0) result++;
			}
		}
		
		return result;
	}
}
