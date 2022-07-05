import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.StringTokenizer;
//BOj 14890 경사로 똑같은 문제 80ms
public class Solution_SWEA_4014_활주로건설_양소정_103ms {
	private static int[][] map;
	private static int N;
	private static int X;
	private static int ans;
	private static int[][] map2;
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans=0;
				
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			map2 = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j =0 ,k=0; j < N; j++,k+=2) {
					map[i][j] = str.charAt(k)-'0';
					map2[j][i] = str.charAt(k)-'0';
				}
			}

			go(map);	//가로 
			go(map2);   //세로
			
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void go(int[][]map) {
		int cnt;
		boolean [][]v = new boolean[N][N];
		boolean flag=true;;
		

		for (int i = 0; i < N; i++) {
			cnt =0;
			flag=true; //가능 여부 
go:			for (int j =1; j < N; j++) {
			
				if(Math.abs( map[i][j] - map[i][j-1]) ==1 ) { //1.차이가 1이면
					if(map[i][j] > map[i][j-1]) { //현재가 크면
						for (int x = 1; x <= X; x++) {
							if(!isOK(j-x) ||v[i][j-x] || map[i][j-1] != map[i][j-x]) {
								for (int k = 1; k < x; k++) {
									v[i][j-k] = false;
								}
								flag=false;
								break go;
							}else {
								v[i][j-x] = true;
							}
						}
						
					}else {//현재가 작으면
						for (int x = 0; x < X; x++) {
							if(!isOK(j+x) ||v[i][j+x] || map[i][j] != map[i][j+x]) {
								for (int k = 0; k < x; k++) {
									v[i][j+k] = false;
								}
								flag= false;
								break go;
							}else {
								v[i][j+x] = true;
							}
						
						}
						j+=X-1;
					}
				}else if(Math.abs( map[i][j] - map[i][j-1]) >1) { //2.차이가 1보다 크면
					flag =false;
					break; 
				}
				//3.차이 없을 때는 초기 true 상태로 넘김
			}
			if(flag) ans++;
		}
	}

	private static boolean isOK(int j) {
		if( j>=0 && j<N ) return true;
		return false;
	}

}
