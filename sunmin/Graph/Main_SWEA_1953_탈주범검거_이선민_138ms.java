package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_SWEA_1953_탈주범검거_이선민_138ms {
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}	// end of Pos class

	static int[][] drdc = { { 0, 1, 0, -1 }, { 1, 0, -1, 0 } }; // 우하좌상 순서

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int test_case = 1; test_case <= TC; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int limit = Integer.parseInt(st.nextToken());
			char[][] map = new char[row][];
			for (int i = 0; i < row; i++) {
				map[i] = br.readLine().replace(" ", "").toCharArray();
			}

			sb.append("#").append(test_case).append(" ").append(search(map, limit, r, c, row, col)).append("\n");
		} // end of test_case
		System.out.println(sb.toString());
	} // end of main

	/**
	 * 탈출후 소요된 시간동안 파이프 이동하며 탈주범이 위치할 수 있는 장소개수 return
	 */
	private static int search(char[][] map, int limit, int r, int c, int row, int col) {
		int cnt = 0;
		boolean[][] visited = new boolean[row][col];
		Queue<Pos> q = new ArrayDeque<Pos>();
		q.offer(new Pos(r, c));
		for (int i = 0; i < limit; i++) {
			int size = q.size();
			while (size-- > 0) {
				Pos current = q.poll();
				if (visited[current.r][current.c]) {
					continue;
				}
				visited[current.r][current.c] = true;
				cnt++;

				char now = map[current.r][current.c]; // 현재 파이프
				for (int dir = 0; dir < 4; dir++) {
					int nr = current.r + drdc[0][dir];
					int nc = current.c + drdc[1][dir];

					if (nr < 0 || nr >= row || nc < 0 || nc >= col || map[nr][nc] == '0' || visited[nr][nc]) {
						continue;
					}
					char next = map[nr][nc]; // 다음 파이프
					// 현재 파이프와 다음 파이프의 방향이 맞아야지만 넘어갈 수 있음
					switch (dir) {
					case 0: // 우
						if (now == '1' || now == '3' || now == '4' || now == '5') {
							if (next == '1' || next == '3' || next == '6' || next == '7') {
								q.offer(new Pos(nr, nc));
							}
						}
						break;
					case 1: // 하
						if (now == '1' || now == '2' || now == '5' || now == '6') {
							if (next == '1' || next == '2' || next == '4' || next == '7') {
								q.offer(new Pos(nr, nc));
							}
						}
						break;
					case 2: // 좌
						if (now == '1' || now == '3' || now == '6' || now == '7') {
							if (next == '1' || next == '3' || next == '4' || next == '5') {
								q.offer(new Pos(nr, nc));
							}
						}
						break;
					case 3: // 상
						if (now == '1' || now == '2' || now == '4' || now == '7') {
							if (next == '1' || next == '2' || next == '5' || next == '6') {
								q.offer(new Pos(nr, nc));
							}
						}
						break;
					}
				} // 파이프별 갈수 있는 방향 탐색 완료

			} // 한 시간 경과
		} // 제한시간 모두 경과
		return cnt;
	} // end of search

} // end of class
