import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Solution_SWEA_3124_최소스패닝트리_D4_이승연_1957ms {
	static class Edge implements Comparable<Edge>{
		int from, to;
		int weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		} 
	}
	
	private static int[] parents;
	private static int V;
	private static Edge[] edgeList;
	
	public static void makeSet() {
		parents = new int[V+1];
		for(int i=1; i<=V; i++) {
			parents[i] = i;
		}
	}
	
	public static int findSet(int a) {
		if(a == parents[a]) return a; 
		
		return parents[a] = findSet(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false; 
		
		parents[bRoot] = aRoot;
		return true; 
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		
	
		for(int testCase=1; testCase<=TC; testCase++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			V = Integer.parseInt(st.nextToken()); // 정점의 개수 (1<=V<=100000)
			int E = Integer.parseInt(st.nextToken()); // 간선의 개수 (1<=E<=200000)
			edgeList = new Edge[E];
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(from, to, weight);
			}
			
			Arrays.sort(edgeList); // 간선비용의 오름차순 정렬 
			makeSet();
			
			long result = 0;
			int cnt = 0;
			for(Edge edge : edgeList) {
				if(union(edge.from, edge.to)) {
					result += edge.weight;
					if(++cnt == V-1) break;
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
