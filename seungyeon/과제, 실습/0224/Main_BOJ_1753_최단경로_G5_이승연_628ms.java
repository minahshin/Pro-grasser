import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_1753_최단경로_G5_이승연_628ms {
	static class Node {
		int no; 
		int weight;
		Node link;
		
		public Node(int no, int weight, Node link) {
			super();
			this.no = no;
			this.weight = weight;
			this.link = link;
		}
	}
	
	static class Vertex implements Comparable<Vertex>{
		int no, minDistance; // 정점 번호, 출발지에서 자신으로의 최소비용 

		public Vertex(int no, int minDistance) {
			super();
			this.no = no;
			this.minDistance = minDistance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.minDistance - o.minDistance; // 오름차순
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수(1<=V<=20000)
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수(1<=E<=300000)
		
		int K = Integer.parseInt(br.readLine()); // 시작 정점의 번호(1<=K<=V)
		
		Node[] adjList= new Node[V+1];
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		int[] distance = new int[V+1]; // 출발지에서 자신으로 오는 최소비용
		boolean[] visited = new boolean[V+1]; // 최소비용 확정여부 
		PriorityQueue<Vertex> pQueue = new PriorityQueue<Vertex>();
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[K] = 0;
		pQueue.offer(new Vertex(K, distance[K]));
		
		while(!pQueue.isEmpty()) {
			// 단계1: 최소 비용이 확정되지 않은 정점 중 최소비용의 정점 선택 
//			int min = Integer.MAX_VALUE, current = 0;
//			for(int j=1; j<=V; j++) { 
//				if(!visited[j] && min>distance[j]) {
//					min = distance[j];
//					current = j;
//				}
//			}
			
			Vertex current = pQueue.poll();
			
			if(visited[current.no]) continue;
			
			visited[current.no] = true; 
			
			// 단계2: 선택된 정점을 경유지로 하여 아직 최소비용이 확정되지 않은 다른 정점의 최소비용을 고려 
			// current 정점의 인접정점 처리 (단, 방문하지 않은 인접정점만)
			for(Node temp=adjList[current.no]; temp != null; temp=temp.link) { // 현재 정점의 head, 반드시 인접한 정점이 있어야 함. 다음 노드로 가야함. 
				if(!visited[temp.no] && distance[temp.no]>distance[current.no]+temp.weight) { // 인접 여부 판단 필요없음. 이미 인접한 얘들만 있으니까. 
					distance[temp.no] = distance[current.no]+temp.weight;
					pQueue.offer(new Vertex(temp.no, distance[temp.no]));
				}
			}
		}
		
		for(int i=1; i<=V; i++) {
			if(distance[i] != Integer.MAX_VALUE) {
				sb.append(distance[i]).append("\n");
			} else {
				sb.append("INF").append("\n");
			}
		}
		
		System.out.print(sb.toString());
	} // end of main
} // end of class
