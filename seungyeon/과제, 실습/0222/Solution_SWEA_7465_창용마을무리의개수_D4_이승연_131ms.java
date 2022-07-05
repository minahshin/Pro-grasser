import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution_SWEA_7465_창용마을무리의개수_D4_이승연_131ms {
	static class Node {
		int vertex; 
		Node link;
		
		public Node(int vertex, Node link) { // 정점번호, 다음 link 가져와서 초기화  
			super();
			this.vertex = vertex;
			this.link = link;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int testCase=1; testCase<=TC; testCase++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			Node[] relation = new Node[N+1];
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());

				relation[p1] = new Node(p2, relation[p1]);
				relation[p2] = new Node(p1, relation[p2]); // 양방향이니까 뒤집어서 넣으면 된다. 
			}
			
			boolean[] visited = new boolean[N+1]; 
			int result = 0; 
			
			for(int i=1; i<=N; i++) {
				if(!visited[i]) {
					bfs(relation, visited, i);
					result++;
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void bfs(Node[] adjList, boolean[] visited, int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.offer(start);
		visited[start] = true; 
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for(Node temp=adjList[current]; temp != null; temp=temp.link) { // 현재 정점의 head, 반드시 인접한 정점이 있어야 함. 다음 노드로 가야함. 
				if(!visited[temp.vertex]) { // 인접 여부 판단 필요없음. 이미 인접한 얘들만 있으니까. 
					queue.offer(temp.vertex);
					visited[temp.vertex] = true;
				}
			}
		}
	}
}
