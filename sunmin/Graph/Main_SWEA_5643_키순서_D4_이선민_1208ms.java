package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_SWEA_5643_키순서_D4_이선민_1208ms {
	static class Node {
		int vertex;
		Node link;

		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}

		public Node(int vertex) {
			super();
			this.vertex = vertex;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= TC; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			StringTokenizer st;
			Node[] adjList = new Node[N + 1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
			}

			int[] taller = new int[N + 1]; // 자신(인덱스번호)보다 키큰 사람의 수
			int[] smaller = new int[N + 1]; // 자신(인덱스번호)보다 키작은 사람의 수
			for (int i = 1; i <= N; i++) {
				search(adjList, N, i, i, new boolean[N + 1], taller, smaller);
			}

			int ans = 0;
			for (int i = 1; i <= N; i++) {
				if (taller[i] + smaller[i] == N - 1) {
					ans++;
				}
			}

			sb.append("#").append(test_case).append(" ").append(ans).append("\n");
		} // end of test_case
		System.out.println(sb.toString());
	} // end of main

	private static void search(Node[] adjList, int N, int start, int cur, boolean[] visited, int[] taller,
			int[] smaller) {
		visited[cur] = true;

		for (Node temp = adjList[cur]; temp != null; temp = temp.link) {
			if (!visited[temp.vertex]) {
				taller[start]++;
				smaller[temp.vertex]++;
				search(adjList, N, start, temp.vertex, visited, taller, smaller);
			}
		}
	} // end of search

}
