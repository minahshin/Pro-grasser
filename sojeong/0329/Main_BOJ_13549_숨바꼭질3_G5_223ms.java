
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//방문체크 순서 중요
public class Main_BOJ_13549_숨바꼭질3_G5_223ms{
	  public static class Node {
	        int x;
	        int time;
	        
	        public Node(int x, int time) {
	            this.x = x;
	            this.time = time;
	        }
	    }
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		boolean[] visited = new boolean[100001];
		
		if (N>=K) { 
			System.out.print(N-K); 
			System.exit(0); 
		}
		int min = Integer.MAX_VALUE;

		
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(N,0));
		visited[N] = true;
		int ans=0;	
		while(!q.isEmpty()) {
		
				Node cur = q.poll();
				visited[cur.x] = true;
				if(cur.x == K) {				
					min = Math.min(min, cur.time);
					ans = min;	
				}
				if(cur.x*2<=100000 && !visited[cur.x*2]) {
					q.add(new Node(cur.x*2,cur.time));
					
				}
				if(cur.x+1<=100000 && !visited[cur.x+1]) {  
					q.add(new Node(cur.x+1,cur.time+1));
					
				}
				if(cur.x-1>=0 && !visited[cur.x-1]) {
					q.add(new Node(cur.x-1,cur.time+1));
					
				}
				
			
		}
		System.out.println(ans);
		
	}

}
