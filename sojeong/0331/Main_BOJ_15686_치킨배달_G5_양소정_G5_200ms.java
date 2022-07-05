package march0331;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BOJ_15686_치킨배달_G5_양소정_G5_200ms {
	public static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	private static ArrayList<Node> h; //집
	private static ArrayList<Node> ch;//치킨
	private static int ans;
	private static int N;
	private static int M;
	private static char[][] map;
	private static boolean[] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); //치킨집 최대 수 
		h = new ArrayList<>();
		ch = new ArrayList<>();
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0,k=0; j < N; j++,k+=2) {
				map[i][j] =str.charAt(k); 
				 if (map[i][j] == '1') {
	                    h.add(new Node(i, j));
	                } else if (map[i][j] == '2') {
	                    ch.add(new Node(i, j));
	                }
				
			}
		}//입력 완료
		v = new boolean[ch.size()];
		ans = Integer.MAX_VALUE;
		dfs(0,0);
	
		System.out.println(ans);

	}
	//조합
	private static void dfs(int start,int cnt) {
		if(cnt==M) {
			int sum=0; 
			for (int i = 0; i < h.size(); i++) {
				int dis=Integer.MAX_VALUE;
				for (int j = 0; j < ch.size(); j++) {
					if(v[j]) {
						int d = Math.abs(h.get(i).x - ch.get(j).x)+Math.abs(h.get(i).y - ch.get(j).y);
						dis = Math.min(dis, d); // 치킨집 다 비교하면서 가장 가까운 거리 넣기
					}
				}
				sum+=dis; 
			}
			ans = Math.min(ans, sum);
			return;
		}
		
		for (int i = start; i < ch.size(); i++) {
			v[i] = true;
			dfs(i+1,cnt+1);
			v[i]=false;
		}
	}

	
}
