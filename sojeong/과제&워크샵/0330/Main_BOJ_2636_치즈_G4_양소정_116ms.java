import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2636_치즈_G4_양소정_116ms {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int [][] map = new int [N][M];
		int cheese =0 ; //처음 치즈수
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());		
				if(map[i][j]==1) cheese++;
			}
		}
		
		Queue<Cheese> q = new LinkedList<Cheese>();
		ArrayList<Cheese> melt = null;
	
		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };
		int ans = 0; //시간
		int cnt =0; // 다 녹기 한시간 전 치즈수 
		Cheese cur;
		while (cheese > 0) {
			cnt = cheese;
			int [][] v = new int[N][M];
			q.add(new Cheese(0,0));
			v[0][0]++;
			melt = new ArrayList<>();
			while(!q.isEmpty()) {
				cur = q.poll();
				for (int i = 0; i < 4; i++) {
					int rr = cur.r + dr[i];
					int cc = cur.c + dc[i];
					if (rr < 0 || rr >= N || cc < 0 || cc >= M) continue;
					if(map[rr][cc]==0) {//치즈가 아닐 때
						if(v[rr][cc]==0) {
							v[rr][cc]++;
							q.add(new Cheese(rr,cc));
						}
					}else if(v[rr][cc]==0) {//치즈일때 
						melt.add(new Cheese(rr,cc));
						v[rr][cc]++;
					}						
//					else if(++v[rr][cc]==1) {//치즈일때 이렇게 처리해줘도됨
//						melt.add(new Cheese(rr,cc));
//					}
				}	
			}
			for(Cheese i : melt) {
				map[i.r][i.c] = 0;
				cheese--;	
			}
			ans++;
		}
		
		System.out.println(ans);
		System.out.print(cnt);
		
	}
	static class Cheese {
		int r, c;

		public Cheese(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

}
