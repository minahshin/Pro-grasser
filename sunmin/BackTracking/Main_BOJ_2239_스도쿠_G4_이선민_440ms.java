package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_BOJ_2239_스도쿠_G4_이선민_440ms {
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Pos> zeros = new ArrayList<Pos>();
		int[][] board = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				board[i][j] = s.charAt(j) - '0';
				if (board[i][j] == 0) {
					zeros.add(new Pos(i, j));
				}
			}
		}

		dfs(board, zeros, 0);
	} // end of main

	private static void dfs(int[][] board, List<Pos> zeros, int index) {
		if (index == zeros.size()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(board[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}

		Pos current = zeros.get(index);
		boolean[] visited = new boolean[10]; // 사용한 숫자 확인
		// 가로
		for (int i = 0; i < 9; i++) {
			if (board[current.r][i] != 0) {
				visited[board[current.r][i]] = true;
			}
		}

		// 세로
		for (int i = 0; i < 9; i++) {
			if (board[i][current.c] != 0) {
				visited[board[i][current.c]] = true;
			}
		}

		// 3X3 사각형
		int startR = current.r / 3 * 3, endR = startR + 3;
		int startC = current.c / 3 * 3, endC = startC + 3;
		for (int i = startR; i < endR; i++) {
			for (int j = startC; j < endC; j++) {
				if (board[i][j] != 0) {
					visited[board[i][j]] = true;
				}
			}
		}

		for (int i = 1; i < 10; i++) {
			if (!visited[i]) {
				board[current.r][current.c] = i;
				dfs(board, zeros, index + 1);
				board[current.r][current.c] = 0;
			}
		}

	} // end of dfs

}
