package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_14502_연구소_G5_이선민_536ms {
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static int row, col, ans, count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 3 ≤ row, col ≤ 8
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		// 2 ≤ 바이러스 개수 ≤ 10
		List<Pos> zeros = new ArrayList<Pos>(); // 빈칸들의 좌표를 담아둔 리스트
		int[][] map = new int[row][col];
		for (int i = 0; i < row; i++) {
			String s = br.readLine().replace(" ", "");
			for (int j = 0; j < col; j++) {
				map[i][j] = s.charAt(j) - '0';
				if (map[i][j] == 0) {
					zeros.add(new Pos(i, j));
				}
			}
		}

		buildWall(map, zeros, 0, 0);
		System.out.println(ans);

	} // end of main

	/**
	 * 벽 세우기
	 */
	private static void buildWall(int[][] map, List<Pos> zeros, int cnt, int start) {
		if (cnt == 3) {
			int temp = spread(copy(map, new int[row][col]));
			if (temp > ans) {
				ans = temp;
			}
			return;
		}

		// 조합으로 벽 세우기
		for (int i = start; i < zeros.size(); i++) {
			int r = zeros.get(i).r;
			int c = zeros.get(i).c;
			map[r][c] = 1;
			buildWall(map, zeros, cnt + 1, i + 1);
			map[r][c] = 0;
		}

	} // end of buildWall

	/**
	 * 바이러스 전파
	 */
	private static int spread(int[][] map) {
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };

		Queue<Pos> q = new ArrayDeque<Pos>();
		boolean[][] visited = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (!visited[i][j] && map[i][j] == 2) {
					// bfs
					q.offer(new Pos(i, j));
					while (!q.isEmpty()) {
						Pos current = q.poll();
						if (visited[current.r][current.c]) {
							continue;
						}
						visited[current.r][current.c] = true;

						for (int k = 0; k < dc.length; k++) {
							int nr = current.r + dr[k];
							int nc = current.c + dc[k];

							if (nr < 0 || nr >= row || nc < 0 || nc >= col || map[nr][nc] == 1 || visited[nr][nc])
								continue;
							map[nr][nc] = 2;
							q.offer(new Pos(nr, nc));
						}
					}
				}
			}
		} // 모든 map 탐색 완료

		int cnt = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (map[i][j] == 0) {
					cnt++;
				}
			}
		}

		return cnt;

	} // end of spread

	private static int[][] copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		return newMap;
	} // end of copy

}
