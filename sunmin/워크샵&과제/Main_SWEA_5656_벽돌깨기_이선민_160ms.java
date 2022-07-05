package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_SWEA_5656_벽돌깨기_이선민_160ms {

	static class Pos {
		int r, c, cnt;

		public Pos(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	} // end of Pos class

	static int min, N, row, col;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int test_case = 1; test_case <= TC; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());
			int[][] board = new int[row][col];
			for (int i = 0; i < row; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < col; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			min = Integer.MAX_VALUE;
			throwMarble(0, board);
			sb.append("#").append(test_case).append(" ").append(min).append("\n");

		} // end of test_case
		System.out.println(sb.toString());
	} // end of main

	/**
	 * 중복 순열로 구슬을 던질 위치, 순서 정하기
	 * 
	 * 벽돌이 모두 부서졌다면 true, 아니면 false
	 */
	private static boolean throwMarble(int cnt, int[][] board) {
		int result = getRemain(board);

		// 벽돌이 다 부서졌다면
		if (result == 0) {
			min = 0;
			return true;
		}

		if (cnt == N) {
			min = Math.min(min, result);
			return false;
		}

		int[][] newBoard = new int[row][col];
		for (int c = 0; c < col; c++) {
			int r = 0;
			// 빈공간이면 계속해서 아래로
			while (r < row && board[r][c] == 0)
				++r;

			// 해당 열은 벽돌이 없음
			if (r == row)
				continue;

			// 배열의 상태를 백업
			copy(board, newBoard);

			// 현재 벽돌 기준으로 주변의 가능한 모든 벽돌 함께 연쇄 처리
			crush(newBoard, r, c);

			// 부서진 벽돌 정리
			rearrange(newBoard);

			// 다음 구슬 던지기
			if (throwMarble(cnt + 1, newBoard)) {
				return true;
			}
		}

		return false;
	} // end of throwMarble

	/**
	 * r,c 위치의 벽돌 파괴
	 */
	private static void crush(int[][] board, int r, int c) {
		Queue<Pos> q = new ArrayDeque<Pos>();
		if (board[r][c] > 1) { // 벽돌 크기가 2 이상이면
			q.offer(new Pos(r, c, board[r][c]));
		}
		board[r][c] = 0; // 벽돌 파괴

		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		while (!q.isEmpty()) {
			Pos curr = q.poll();
			for (int i = 0; i < dc.length; i++) {
				int nr = curr.r;
				int nc = curr.c;

				for (int j = 1; j < curr.cnt; j++) { // (벽돌크기-1) 만큼 반복
					nr += dr[i];
					nc += dc[i];
					if (nr < 0 || nr >= row || nc < 0 || nc >= col || board[nr][nc] == 0)
						continue;

					if (board[nr][nc] > 1) {
						q.offer(new Pos(nr, nc, board[nr][nc]));
					}
					board[nr][nc] = 0;
				}
			} // end of 사방탐색
		}

	} // end of crush

	/**
	 * 부서진 벽돌 정리
	 */
	static List<Integer> list = new ArrayList<Integer>();

	private static void rearrange(int[][] board) {
		for (int c = 0; c < col; c++) {
			int r = row - 1; // 마지막 행에서 시작
			while (r >= 0) {
				if (board[r][c] > 0) { // 빈칸이면 내릴 벽돌 찾기
					list.add(board[r][c]);
					board[r][c] = 0;
				}
				r--;
			}

			r = row - 1;
			for (int block : list) { // 깨지지않고 남은 벽돌 리스트
				board[r--][c] = block;
			}
			list.clear();
		}

	} // end of rearrange

	private static int getRemain(int[][] board) {
		int cnt = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (board[r][c] != 0) {
					cnt++;
				}
			}
		}

		return cnt;
	}

	private static void copy(int[][] board, int[][] newBoard) {
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				newBoard[r][c] = board[r][c];
			}
		}
	} // end of copy

} // end of class
