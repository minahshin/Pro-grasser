import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1600_말이되고픈원숭이_G4_양소정_468ms{
	
	static class Monkey{
		int x;
		int y;
		int d; //동작수
		int k; //말로 이동 가능한 수 
		public Monkey(int x, int y, int d, int k) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.k = k;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int [][]map = new int[H][W];
		int ans =-1;
		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0,j1=0; j < W; j++,j1+=2) {
				map[i][j] = str.charAt(j1)-'0';
			}
		}
			
		int []dx = {1,-1,0,0};
		int []dy = {0,0,1,-1};
		int []hx = {-2,-1,-2,-1,2,1,2,1};
		int []hy = {-1,-2,1,2,-1,-2,1,2};
		Queue<Monkey> q = new ArrayDeque<>();
		q.add(new Monkey(0,0,0,k));
		boolean [][][]v = new boolean[H][W][k+1];
		v[0][0][k]=true;
		Monkey cur;
		
		while(!q.isEmpty()) {
			cur = q.poll();
			if(cur.x==H-1 && cur.y==W-1) { //도착하면
				ans = cur.d;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int xx = cur.x +dx[i];
				int yy = cur.y +dy[i];
				if(xx<0 || xx >=H || yy<0 || yy >=W ) continue; //맵 경계 체크
				if(map[xx][yy]==0 && !v[xx][yy][cur.k]) {
					v[xx][yy][cur.k]=true;
					q.add(new Monkey(xx,yy,cur.d+1,cur.k));
				}
				
			}
			if(cur.k>0) {
				for (int i = 0; i < 8; i++) {
					int xx = cur.x +hx[i];
					int yy = cur.y +hy[i];
					if(xx<0 || xx >=H || yy<0 || yy >=W ) continue; //맵 경계 체크
					if(map[xx][yy]==0 && !v[xx][yy][cur.k-1]) {
						v[xx][yy][cur.k-1]=true;
						q.add(new Monkey(xx,yy,cur.d+1,cur.k-1));
					}	
				}	
			}
						
		}
		System.out.println(ans==-1?"-1":ans);
		
		
	}//end of main

}
