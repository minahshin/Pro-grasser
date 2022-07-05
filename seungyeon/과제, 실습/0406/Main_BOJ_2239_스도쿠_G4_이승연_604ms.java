import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_BOJ_2239_스도쿠_G4_이승연_604ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] grid = new int[9][9];
		ArrayList<int[]> loc_0 = new ArrayList<int[]>(); // 0인 지점 

		for(int i=0; i<9; i++) {
			String str = br.readLine();
			for(int j=0; j<9; j++) {
				int n = str.charAt(j)-'0';
				grid[i][j] = n;
				if(n == 0) {
					loc_0.add(new int[]{i,j});
				}
			}
		}
		
		calc(grid, loc_0, 0);
	}
	
	private static boolean calc(int[][] grid, ArrayList<int[]> loc_0, int idx) {
		if(idx == loc_0.size()) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sb.append(grid[i][j]);
				}
				sb.append("\n");
			}
			
			System.out.println(sb.toString());
			
			return true; 
		}
		
		
		int[] num = new int[10];
		int[] loc = loc_0.get(idx);
		
		// 가로 체크 
		for(int i=0; i<9; i++) {
			num[grid[loc[0]][i]]++;
		}
		
		// 세로 체크 
		for(int i=0; i<9; i++) {
			num[grid[i][loc[1]]]++;
		}
		
		// 네모 체크 
		int sp_r = loc[0]/3*3;
		int sp_c = loc[1]/3*3;
		for(int i=sp_r, cnt_r=sp_r+3; i<cnt_r; i++) {
			for(int j=sp_c, cnt_c=sp_c+3; j<cnt_c; j++) {
				num[grid[i][j]]++;
			}
		}
		
		for(int i=1; i<10; i++) {
			if(num[i] == 0) {
				grid[loc[0]][loc[1]] = i;
				if(calc(grid, loc_0, idx+1)) return true; 
				grid[loc[0]][loc[1]] = 0;
			}
		}
		
		return false; 
	}
}
