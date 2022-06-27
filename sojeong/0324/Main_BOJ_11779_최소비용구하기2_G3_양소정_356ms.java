

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main_BOJ_11779_최소비용구하기2_G3_양소정_356ms{

	static class Node implements Comparable<Node>{
		int v,w;
		Node link;
		//link 필요없는듯..
		public Node(int v,int w, Node link) {
			super();
			this.v = v;
			this.w = w;
			this.link = link;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.w-o.w;
		}		
	}

	public static int N;
	private static int[] parent ;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		Node[] adj = new Node[N+1];
		
		StringTokenizer st = null;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[from] = new Node(to,w,adj[from]);
			
			
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[] distance = new int[N+1]; 
		boolean[] visited = new boolean[N+1]; 
		
		long res = 0;
		parent = new int[N+1];
		//최소비용
        res = dijkstra(start,end, distance, visited,adj,parent);
        System.out.println(res);
        
        //도시 개수 (출발도시 포함)
        int cnt=1;
        Stack<Integer> t = new Stack<>();
        t.add(end);
       //역순으로 탐색
        while(parent[end]!=0) {
        	cnt++; //경로 개수 카운팅
        	t.add(parent[end]);
        	end = parent[end];
        }
        
        System.out.println(cnt);
      
        while(!t.isEmpty()) {
    	   System.out.print(t.pop()+" ");
        }
		
	}
	private static long dijkstra(int start, int end , int[] distance, boolean[] visited, Node[] adj,int[] parent) {
		PriorityQueue<Node> pQueue = new PriorityQueue<Node>();
		pQueue.offer(new Node(start, 0, null)); //시작점과 비용 넣음
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0; // 시작점 0으로
		 
		
		while(!pQueue.isEmpty()) {
			
			int current = pQueue.poll().v; 
			
			if(visited[current]) continue; 
			
			visited[current] = true;
			
			for (Node node = adj[current]; node!=null; node = node.link) {
                if (distance[node.v] > distance[current] + node.w) {
                    distance[node.v] = distance[current] + node.w;
                    pQueue.add(new Node(node.v, distance[node.v],null));
                    parent[node.v] = current;
                }
            }
		}
		return distance[end];
	}
}
