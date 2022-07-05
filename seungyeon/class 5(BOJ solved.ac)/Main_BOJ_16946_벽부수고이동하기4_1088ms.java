import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BOJ_16946_벽부수고이동하기4_1088ms {
	public static class Point{
		int r; 
		int c;
		
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 1000
		int M = Integer.parseInt(st.nextToken()); // 1000
		
		int[][] map = new int[N][M];
		String str;
		for(int i=0; i<N; i++) {
			str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		int[][] group = new int[N][M];
		int groupNum = 1;
		ArrayList<Integer> num = new ArrayList<Integer>();
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		// group 초기화 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0 && group[i][j] == 0) {
					num.add(bfs(map, group, i, j, groupNum, dr, dc, N ,M));
					groupNum++;
				}
			}
		}
		
		int cur_r;
		int cur_c;
		int temp;
		
		StringBuilder sb = new StringBuilder();
		Set<Integer> set;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				set = new HashSet<Integer>();
				temp = 1;
				if(map[i][j] == 1) {
					for(int d=0; d<4; d++) {
						cur_r = i+dr[d];
						cur_c = j+dc[d];
						if(cur_r>=0 && cur_r<N && cur_c>=0 && cur_c<M && map[cur_r][cur_c]==0) {
							set.add(group[cur_r][cur_c]-1);
						}
					}
					for(int n : set) {
						temp += num.get(n);
					}
					sb.append(temp%10);
				} else {
					sb.append("0");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static int bfs(int[][] map, int[][] group, int r, int c, int groupNum, int[] dr, int[] dc, int N, int M) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(r, c));
		group[r][c] = groupNum;
		int cur_r;
		int cur_c; 
		int cnt = 1;
		Point p;
		
		while(!queue.isEmpty()) {
			p = queue.poll();
			for(int d=0; d<4; d++) {
				cur_r = p.r + dr[d];
				cur_c = p.c + dc[d];
				if(cur_r>=0 && cur_r<N && cur_c>=0 && cur_c<M && map[cur_r][cur_c]==0 && group[cur_r][cur_c]==0) {
					group[cur_r][cur_c] = groupNum;
					cnt++;
					queue.add(new Point(cur_r, cur_c));
 				}
			}
		}
		
		return cnt;
	}
}

