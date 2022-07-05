package com.ssafy.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_SWEA_1767_프로세서연결하기_윤성도_153ms {
	static int N, core, line, map[][];
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int TC = 1; TC <= T; TC++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			core = 0;
			line = Integer.MAX_VALUE;
			ArrayList<Pos> cores = new ArrayList<>();
			for (int i = 0; i < N; ++i) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						if ((i == 0 || j == 0 || i == N - 1 || j == N - 1)) {
							continue;
						} else {
							cores.add(new Pos(i, j));
						}
					}
				}
			}

			dfs(cores, 0, 0, 0);
			sb.append("#").append(TC).append(" ").append(line).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static void dfs(ArrayList<Pos> cores, int coreSum, int lineSum, int idx) {
		int tmp = cores.size() - idx;
		if (tmp < core - coreSum)
			return;
		if (tmp == 0) {
			if (coreSum > core) {
				core = coreSum;
				line = lineSum;
			}

			else if (coreSum == core) {
				if (lineSum < line) {
					line = lineSum;
				}
			}
			return;
		}

		Pos cur = cores.get(idx);
		for (int i = 0; i < 4; ++i) {
			Pos next = new Pos(cur.r + dr[i], cur.c + dc[i]);
			int result = draw(next, i);
			if (result == -1)
				continue;
			dfs(cores, coreSum + 1, lineSum + result, idx + 1);
			delete(next, i);
		}
		dfs(cores, coreSum, lineSum, idx + 1);
	}

	public static int draw(Pos pos, int dir) {
		try {
			if (map[pos.r][pos.c] != 0)
				return -1;
			Pos next = new Pos(pos.r + dr[dir], pos.c + dc[dir]);
			int result = draw(next, dir);
			if (result == -1)
				return -1;
			map[pos.r][pos.c] = 2;
			return result + 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public static void delete(Pos pos, int dir) {
		try {
			Pos next = new Pos(pos.r + dr[dir], pos.c + dc[dir]);
			map[pos.r][pos.c] = 0;
			delete(next, dir);
		} catch (Exception e) {
			return;
		}
	}

	static class Pos {
		int r, c;

		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
