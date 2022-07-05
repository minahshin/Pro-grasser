import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_7576_토마토_G5_이승연_540ms {
	static class Node{
		int r;
		int c;
		
		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[] dir_r = {0, 1, 0, -1}; // 동 남 서 북 
		int[] dir_c = {1, 0, -1, 0};
		
		int N = Integer.parseInt(st.nextToken()); // 상자의 세로칸 (2<=N<=1000)
		int M = Integer.parseInt(st.nextToken()); // 상자의 가로칸 (2<=M<=1000)
		
		int[][] box = new int[M+2][N+2];
		
		for(int j=0; j<N+2; j++) {
			box[0][j] = -1;
			box[M+1][j] = -1;
		}
		for(int i=0; i<M+2; i++) {
			box[i][0] = -1;
			box[i][N+1] = -1;
		}
		
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=N; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		} 
		
		bfs(box, M, N, dir_r, dir_c);
		
		int result_max = 0;
		
		for(int i=1; i<=M; i++) {
			for(int j=1; j<=N; j++) {
				if(box[i][j] == 0) { // 안 익은 토마토가 있으면
					System.out.println("-1");
					return; 
				}
				
				result_max = Math.max(result_max, box[i][j]);
			}
		}
		
		System.out.println(result_max-1);
	} // end of main
	
	public static void bfs(int[][] box, int M, int N, int[] dir_r, int[] dir_c) {
		Queue<Node> queue = new LinkedList<Node>();
		
		for(int i=1; i<=M; i++) {
			for(int j=1; j<=N; j++) {
				if(box[i][j] == 1){
					queue.offer(new Node(i, j));
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nr = n.r + dir_r[i];
				int nc = n.c + dir_c[i];
				
				if(box[nr][nc] == 0) {
					box[nr][nc] = box[n.r][n.c]+1;
					queue.add(new Node(nr, nc));
				}
			}
		}
	}
} // end of class 
