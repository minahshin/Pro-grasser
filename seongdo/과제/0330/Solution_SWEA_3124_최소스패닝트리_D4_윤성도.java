package com.ssafy.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_SWEA_3124_최소스패닝트리_D4_윤성도 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, M, i, a, b, c, T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int TC = 1; TC <= T; TC++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ArrayList<ArrayList<edge>> adj = new ArrayList<>(N + 1);
			for (i = 0; i <= N; i++)
				adj.add(new ArrayList<>());
			for (i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				adj.get(a).add(new edge(b, c));
				adj.get(b).add(new edge(a, c));
			}
			boolean[] visit = new boolean[N + 1];
			PriorityQueue<edge> q = new PriorityQueue<>((z, x) -> z.w - x.w);
			q.offer(new edge(1, 0));
			long ans = 0;
			while (!q.isEmpty()) {
				edge cur = q.poll();
				if(visit[cur.to]) continue;
				visit[cur.to] = true;
				ans+=cur.w;
				for(edge next : adj.get(cur.to)) q.offer(next);
			}
			sb.append("#").append(TC).append(" ").append(ans).append("\n");
		}
		System.out.print(sb.toString());
	}

	static class edge {
		int to, w;

		public edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}
}
