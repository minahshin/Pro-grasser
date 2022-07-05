import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_10026_적록색약_G5_이승연_88ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		char[][] picture = new char[N][N];
		int[] dir_r = {0, 1, 0, -1};
		int[] dir_c = {1, 0, -1, 0};
		
		for(int i=0; i<N; i++) {
			picture[i] = br.readLine().toCharArray();
		}
		
		boolean[][] visited_normal = new boolean[N][N];
		boolean[][] visited_rg = new boolean[N][N];
		
		int result_normal = 0;
		int result_rg = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited_normal[i][j]) {
					dfs_normal(picture, visited_normal, dir_r, dir_c, N, i, j);
					result_normal++;
				}
				if(!visited_rg[i][j]) {
					dfs_rg(picture, visited_rg, dir_r, dir_c, N, i, j);
					result_rg++;
				}
			}
		}
		
		System.out.println(result_normal+" "+result_rg);
	}
	
	public static void dfs_normal(char[][] picture, boolean[][] visited_normal, int[] dir_r, int[] dir_c, int N, int r, int c) {
		visited_normal[r][c] = true;

		for(int i=0; i<4; i++) {
			int nr = r + dir_r[i];
			int nc = c + dir_c[i];
			
			if(nr>=0 && nc>=0 && nr<N && nc<N && !visited_normal[nr][nc] && picture[r][c]==picture[nr][nc]) {
				dfs_normal(picture, visited_normal, dir_r, dir_c, N, nr, nc);
			}
		}
	}
	
	public static void dfs_rg(char[][] picture, boolean[][] visited_rg, int[] dir_r, int[] dir_c, int N, int r, int c) {
		visited_rg[r][c] = true;

		for(int i=0; i<4; i++) {
			int nr = r + dir_r[i];
			int nc = c + dir_c[i];
			
			if(nr>=0 && nc>=0 && nr<N && nc<N && !visited_rg[nr][nc]) {
				if(((picture[r][c]=='R' || picture[r][c]=='G') && (picture[nr][nc]=='R' || picture[nr][nc]=='G')) || picture[r][c]==picture[nr][nc]) {
					dfs_rg(picture, visited_rg, dir_r, dir_c, N, nr, nc);
				}
			}
		}
	}
}
