import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_SWEA_1767_프로세서연결하기_이승연_870ms {
	private static int max_core_n;
	private static int min_wire_n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		ArrayList<int[]> corelist;
		int[] dir_r = {0, 1, 0, -1};
		int[] dir_c = {1, 0, -1, 0};
		
		for(int testCase=1; testCase<=TC; testCase++) {
			int N = Integer.parseInt(br.readLine());
			char[][] maxi = new char[N][N];
			corelist = new ArrayList<int[]>();
			max_core_n = 0; 
			min_wire_n = 1000; 
			
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c=0; c<N; c++) {
					maxi[r][c] = st.nextToken().charAt(0);
					if(r>0 && r<N-1 && c>0 && c<N-1 && maxi[r][c]=='1') { // core들 위치 저장 
						corelist.add(new int[]{r,c});
					}
				}
			}
			
			dfs(maxi, corelist, 0, dir_r, dir_c, N, 0, 0);
			
			sb.append("#").append(testCase).append(" ").append(min_wire_n).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static void dfs(char[][] maxi, ArrayList<int[]> corelist, int core_idx, int[] dir_r, int[] dir_c, int N, int core_n, int wire_n) {
		if(core_idx == corelist.size()) {
			if(max_core_n < core_n) {
				max_core_n = core_n;
				min_wire_n = wire_n;
			} else if(max_core_n == core_n) {
				min_wire_n = Math.min(min_wire_n, wire_n);
			}
			return; 
		}
		
		int cur_r = corelist.get(core_idx)[0];
		int cur_c = corelist.get(core_idx)[1];
				
		for(int d=0; d<4; d++) {
			int temp_r = cur_r;
			int temp_c = cur_c;
			int cnt = 0;
			
			while(true) {
				temp_r += dir_r[d];
				temp_c += dir_c[d];

				if(temp_r<0 || temp_r>=N || temp_c<0 || temp_c>=N) break; // 끝까지 도착 
				
				if(maxi[temp_r][temp_c]=='1') { // 중간에 core를 만나거나 전선을 만났을 때 
					cnt=0; 
					break;
				}
				cnt++; 
			}
			
			// 전선 
			temp_r = cur_r; 
			temp_c = cur_c; 
			
			for(int i=0; i<cnt; i++) { 
				temp_r += dir_r[d];
				temp_c += dir_c[d];
				
				maxi[temp_r][temp_c] = '1';
			}
			
			if(cnt == 0) dfs(maxi, corelist, core_idx+1, dir_r, dir_c, N, core_n, wire_n);
			else {
				dfs(maxi, corelist, core_idx+1, dir_r, dir_c, N, core_n+1, wire_n+cnt);
				
				// 원래대로 
				temp_r = cur_r; 
				temp_c = cur_c; 
				
				for(int i=0; i<cnt; i++) { 
					temp_r += dir_r[d];
					temp_c += dir_c[d];
					
					maxi[temp_r][temp_c] = '0';
				}
			}
		}
	}
}
