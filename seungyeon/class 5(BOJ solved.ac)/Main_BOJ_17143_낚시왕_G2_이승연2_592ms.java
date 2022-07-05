import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_17143_낚시왕_G2_이승연2_592ms {
	public static class Shark{
		int r; // 위치
		int c; // 위치 
		int s; // 속력 
		int d; // 이동방향 (1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽)
		int z; // 크기 
		
		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int R = Integer.parseInt(st.nextToken()); // 격자판의 크기 
		int C = Integer.parseInt(st.nextToken()); // 격자판의 크기 
		int M = Integer.parseInt(st.nextToken()); // 상어의 수 
		
		int fisher_c = -1; // 낚시왕 위치(col)
		
		int[][] grid = new int[R][C];
		int[][] next_grid = new int[R][C];
		
		for(int i=0; i<R; i++) {
			Arrays.fill(grid[i], -1);
		}
		Shark[] sharks = new Shark[M];
		
		int R_mul2 = 2*(R-1);
		int C_mul2 = 2*(C-1);
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken()); // 속력 
			int d = Integer.parseInt(st.nextToken()); // 방향 
			int z = Integer.parseInt(st.nextToken()); // 크기 
			if(d==1 || d==2) { // 위, 아래 
				s %= R_mul2;
			} else { // 왼쪽, 오른쪽
				s %= C_mul2; 
			}
			sharks[i] = new Shark(r, c, s, d, z);
			grid[r][c] = i;
		}

		int result = 0;
		int limit = C-1;
		while(fisher_c < limit) { 
			// 1. 낚시왕이 오른쪽으로 한 칸 이동 
			fisher_c++;
			// 2. 낚시왕이 있는 열에 있는 상어 중 땅과 가장 가까운 상어를 잡음. 
			for(int i=0; i<R; i++) {
				if(grid[i][fisher_c] != -1) {
					result += sharks[grid[i][fisher_c]].z;
					grid[i][fisher_c] = -1;
					break; // 가장 가까운 상어 잡으면 끝 
				}
			}
			// 3. 상어가 이동 
			move(grid, next_grid, sharks, R-1, C-1, M);
		}
		
		System.out.println(result);
	}
	
	public static void move(int[][] grid, int[][] next_grid, Shark[] sharks, int R, int C, int M) {
		for(int r=0; r<=R; r++) {
			for(int c=0; c<=C; c++) {
				next_grid[r][c] = -1;
			}
		}
		int i;
		for(int r=0 ; r<=R; r++) { // 상어 수만큼 
			for(int c=0; c<=C; c++) {
				if(grid[r][c] != -1) { // 살아있는 상어에 대해서 
					i = grid[r][c];
					// 상어 이동 
					// 상어 방향으로 속도만큼 이동, 단 끝을 만나면 방향 바꿔서 이동 
					for(int j=0; j<sharks[i].s; j++) {
						// 끝을 만나면 방향 바꿈 
						if(sharks[i].r == 0 && sharks[i].d == 1) { // 위
							sharks[i].d = 2;
						} else if(sharks[i].r == R && sharks[i].d == 2) { // 아래 
							sharks[i].d = 1;
						} else if(sharks[i].c == C && sharks[i].d == 3) { // 오른쪽 
							sharks[i].d = 4;
						} else if(sharks[i].c == 0 && sharks[i].d == 4) { // 왼쪽 
							sharks[i].d = 3;
						} 
						
						// 상어 방향으로 이동
						if(sharks[i].d == 1) { // 위로 이동 
							--sharks[i].r;
						} else if(sharks[i].d == 2) { // 아래로 이동 
							++sharks[i].r;
						} else if(sharks[i].d == 3) { // 오른쪽으로 이동 
							++sharks[i].c;
						} else { // 왼쪽으로 이동 
							--sharks[i].c;
						}
					}
					
					// 이동 후 다른 상어가 있으면 크기가 큰 걸로 
					if(next_grid[sharks[i].r][sharks[i].c] == -1) {
						next_grid[sharks[i].r][sharks[i].c] = i;						
					} else {
						if(sharks[next_grid[sharks[i].r][sharks[i].c]].z < sharks[i].z) { // 새로운 애가 더 클 때 
							next_grid[sharks[i].r][sharks[i].c] = i; // 갱신 
						} 
					}
				}
			}
		}
		for(int r=0; r<=R; r++) {
			for(int c=0; c<=C; c++) {
				grid[r][c] = next_grid[r][c];
			}
		}
	}
}

