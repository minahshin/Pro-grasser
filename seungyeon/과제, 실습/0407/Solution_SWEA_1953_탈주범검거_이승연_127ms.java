import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_1953_탈주범검거_이승연_127ms {
	static int[] dr = {-1, 0, 1, 0}; 
	static int[] dc = {0, 1, 0, -1};
	
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int N = Integer.parseInt(st.nextToken()); // 지하 터널 지도의 세로 크기(5<=N<=50)
			int M = Integer.parseInt(st.nextToken()); // 지하 터널 지도의 가로 크기(5<=M<=50)
			int R = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑이 위치한 장소 
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken()); // 탈출 후 소요된 시간 
			
			int[][] map = new int[N][M];

			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append("#").append(tc).append(" ").append(bfs(R, C, L, map, N, M)).append("\n");
		}
		
		System.out.println(sb.toString());
	}
 	
 	private static int bfs(int r, int c, int l, int[][] map, int N, int M) {
 		boolean[][] visited = new boolean[N][M];
 		Queue<int[]> queue = new LinkedList<int[]>(); // int[] : r, c, level
 		queue.offer(new int[] {r, c, 1});
 		visited[r][c] = true;
 		int level = 1;
 		int nr, nc = 0; 
 		int result = 0;
 		
 		while(!queue.isEmpty()) {
 			int size = queue.size();
 			level++;
 			for(int i=0; i<size; i++) {
 				int[] cur = queue.poll();
 				result++; 
 				if(cur[2] == l) continue; 
 					
 				boolean[] nd = dir_calc(cur[0], cur[1], N, M, map);
 				
 				for(int d=0; d<4; d++) {
 					if(nd[d]) {
 						nr = cur[0] + dr[d];
 						nc = cur[1] + dc[d];
 						if(!visited[nr][nc]) {
 							queue.offer(new int[]{nr, nc, level});
 							visited[nr][nc] = true;
 						} 						
 					}
 				}
 			}
 		}
 		
 		return result; 
 	}
	
	// 어느 방향으로 갈 수 있는지 판단 (idx)
	private static boolean[] dir_calc(int r, int c, int N, int M, int[][] map) {
		boolean[] cd = new boolean[4]; // 현재 위치에서 갈 수 있는 방향 
		boolean[] nd = new boolean[4]; // 다음으로 갈 수 있는 방향
		int nr, nc, d;
		switch(map[r][c]) {
		case 1: 
			for(int i=0; i<4; i++) {
				cd[i] = true; 
			}
			
			break;
		case 2: 
			cd[0] = true; 
			cd[2] = true; 

			break;
		case 3: 
			cd[1] = true; 
			cd[3] = true; 

			break;
		case 4: 
			cd[0] = true; 
			cd[1] = true;

			break;
		case 5: 
			cd[1] = true; 
			cd[2] = true; 

			break;
		case 6: 		
			cd[2] = true; 
			cd[3] = true; 

			break;
		case 7: 
			cd[0] = true; 
			cd[3] = true; 

			break;
		}
		
		for(int i=0; i<4; i++) {
			if(cd[i]) {
				switch(i) {
				case 0:
					nr = r + dr[i]; 
					nc = c + dc[i]; 
					if(nr>=0 && nr<N && nc>=0 && nc<M) {
						if(map[nr][nc]==1 || map[nr][nc]==2 || map[nr][nc]==5 || map[nr][nc]==6) nd[i] = true; 
					}
					break; 
				case 1:
					nr = r + dr[i]; 
					nc = c + dc[i]; 
					if(nr>=0 && nr<N && nc>=0 && nc<M) {
						if(map[nr][nc]==1 || map[nr][nc]==3 || map[nr][nc]==6 || map[nr][nc]==7) nd[i] = true; 
					}
					break; 
				case 2:
					nr = r + dr[i]; 
					nc = c + dc[i]; 
					if(nr>=0 && nr<N && nc>=0 && nc<M) {
						if(map[nr][nc]==1 || map[nr][nc]==2 || map[nr][nc]==4 || map[nr][nc]==7) nd[i] = true; 
					}
					break; 
				case 3:
					nr = r + dr[i]; 
					nc = c + dc[i]; 
					if(nr>=0 && nr<N && nc>=0 && nc<M) {
						if(map[nr][nc]==1 || map[nr][nc]==3 || map[nr][nc]==4 || map[nr][nc]==5) nd[i] = true; 
					}
					break; 
				}
			}
		}
		
		return nd; 
	}
}
