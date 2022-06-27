package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_BOJ_4485_녹색옷입은애가젤다지_G4_이선민_248ms {
	static class Position implements Comparable<Position> {
		int r, c;
		int lose;

		public Position(int r, int c, int lose) {
			super();
			this.r = r;
			this.c = c;
			this.lose = lose;
		}

		@Override
		public int compareTo(Position o) {
			return this.lose - o.lose;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N;
		int TC = 1;
		while ((N = Integer.parseInt(br.readLine())) != 0) {
			int[][] map = new int[N][N];
			for (int i = 0; i < map.length; i++) {
				String s = br.readLine().replace(" ", "");
				for (int j = 0; j < map.length; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}

			PriorityQueue<Position> pq = new PriorityQueue<Position>();
			boolean[][] visited = new boolean[N][N];
			pq.offer(new Position(0, 0, map[0][0]));
			int[] dr = { 0, 1, 0, -1 };
			int[] dc = { 1, 0, -1, 0 };
			while (!pq.isEmpty()) {
				Position current = pq.poll();
				if (visited[current.r][current.c]) {
					continue;
				}
				if (current.r == N - 1 && current.c == N - 1) {
					sb.append("Problem ").append(TC++).append(": ").append(current.lose).append("\n");
					break;
				}
				visited[current.r][current.c] = true;

				for (int i = 0; i < dr.length; i++) {
					int nr = current.r + dr[i];
					int nc = current.c + dc[i];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
						continue;
					}

					pq.offer(new Position(nr, nc, current.lose + map[nr][nc]));
				}

			}
		} // end of testcase
		System.out.println(sb.toString());
	} // end of main

}
