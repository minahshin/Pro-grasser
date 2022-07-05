import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_11779_최소비용구하기2_G3_이재순_488ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[n+1]; 
		StringTokenizer st;
		for (int i = 0, a; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			nodes[a] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), nodes[a]);
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(start, 0, 1, (new StringBuilder()).append(start).append(" ")));
		boolean[] visited = new boolean[n+1]; 
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (visited[cur.to]) 
				continue;
			visited[cur.to] = true;
			if(cur.to == end) {
				StringBuilder ans = new StringBuilder();
				ans.append(cur.weight).append("\n").append(cur.cnt).append("\n").append(cur.sb);
				System.out.println(ans);
				return;
			}
			for (Node curNode = nodes[cur.to]; curNode != null; curNode = curNode.next) {
				if (!visited[curNode.to]) {
					pq.offer(new Node(curNode.to, cur.weight+curNode.weight, cur.cnt+1, (new StringBuilder()).append(cur.sb).append(curNode.to).append(" ")));
				}
			}
		}
	}
	
	static class Node implements Comparable<Node>{
		int to, weight, cnt;
		Node next;
		
		StringBuilder sb;
		
		public Node(int to, int weight, int cnt, StringBuilder sb) {
			this.to = to;
			this.weight = weight;
			this.cnt = cnt;
			this.sb = sb;
		}
		public Node(int to, int weight, Node next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight-o.weight;
		}
	}
}
