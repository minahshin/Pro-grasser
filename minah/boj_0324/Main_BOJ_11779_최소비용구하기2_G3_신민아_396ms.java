package boj_0324;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

// idea : 최단 길이가 갱신 될 때 경로도 갱신 되어야함!
// idea : 이 때 시작점부터 시작하는 것 보단 갱신 될 때 도착점이 어디서부터 출발했는지를 체크하면 좋음 -> 경로를 stack을 이용하여 역추적을 해본다
public class Main_BOJ_11779_최소비용구하기2_G3_신민아_396ms {
	static class Node implements Comparable<Node>{
		int end, weight;

		public Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}

	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int cities = Integer.parseInt(br.readLine());
		int buses = Integer.parseInt(br.readLine());
		
		
		List<Node>[] nodes = new ArrayList[cities + 1];
		
		for(int i=1;i<nodes.length;i++) {
			nodes[i] = new ArrayList<Node>();
		}
	
		StringTokenizer st;
		for(int i=0;i<buses;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int fee = Integer.parseInt(st.nextToken());
			
			nodes[from].add(new Node(to, fee));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		int[] routes = new int[cities + 1];
		
		StringBuilder sb = new StringBuilder();
		sb.append(getLowestFee(start, end, nodes, routes)).append("\n");
		
		// 역추적
		Stack<Integer> reverseRoute = new Stack<Integer>();
		while(true) {
			reverseRoute.push(end); // 역추적을 위해 end부터 넣어줌
			end = routes[end]; // routes 배열엔 end로 가기위한 출발점이 있음 -> 역으로 따라감
			if(end == start) { // end가 시작지점이랑 같아지면
				reverseRoute.push(start); // 시작지점을 넣어주고
				break; // 끝
			}
		}
		
		sb.append(reverseRoute.size()).append("\n"); // 경로의 크기 출력
		
		while(reverseRoute.size() > 0) {
			sb.append(reverseRoute.pop()).append(" "); // 경로가 역순으로 들어가있으므료 pop을 해서 stringbuilder에 붙여쥼
		}
		
		System.out.print(sb.toString());
	}
	
	// Dijkstra
	static private int getLowestFee(int start, int end, List<Node>[] nodes, int[] routes) {
		boolean[] visited = new boolean[nodes.length];
		int[] distances = new int[nodes.length];
		Arrays.fill(distances, Integer.MAX_VALUE);
		
		PriorityQueue<Node> pQueue = new PriorityQueue<Node>();
		distances[start] = 0;
		pQueue.offer(new Node(start, 0));
		
		while(!pQueue.isEmpty()) {
			Node current = pQueue.poll();
			
			if(current.end == end) return distances[end];
			if(visited[current.end]) continue;
			
			visited[current.end] = true;
			
			for(Node node : nodes[current.end]) {
				int tempDistance = distances[current.end] + node.weight;
				if(distances[node.end] > tempDistance) {
					distances[node.end] = tempDistance;
					pQueue.offer(new Node(node.end, distances[node.end]));
					routes[node.end] = current.end; // 루트 갱신 시 역추적을 위해 다음 노드는 어느 노드에서 출발했는지 값을 갱신해줌
				}
			}
		}
		
		return distances[end];
	}

}
