package SWEA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_SWEA_1767_프로세서연결하기_이선민_925ms {

	static class Core {
		int r, c;

		public Core(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	} // end of Core class

	static int maxCore, minLen;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= TC; test_case++) {
			int N = Integer.parseInt(br.readLine());
			char[][] board = new char[N][];
			for (int i = 0; i < N; i++) {
				board[i] = br.readLine().replace(" ", "").toCharArray();
			}

			Core[] cores = new Core[12]; // 코어는 최대 12개
			int coreNum = 0; // 가장자리를 제외한 코어의 개수
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < N - 1; j++) {
					if (board[i][j] == '1') {
						cores[coreNum++] = new Core(i, j);
					}
				}
			}

			maxCore = -1; // 최대로 연결한 코어의 개수
			minLen = Integer.MAX_VALUE; // 전선길이 합의 최소
			dfs(0, 0, 0, board, N, cores, coreNum);
			sb.append("#").append(test_case).append(" ").append(minLen).append("\n");
		} // end of test_case
		System.out.println(sb.toString());
	}

	/**
	 * @param cnt   : 지금까지 확인한 코어의 개수
	 * @param core  : 전원과 연결시킨 코어의 개수
	 * @param len   : 전선의 길이
	 * @param board
	 */
	private static void dfs(int cnt, int core, int len, char[][] board, int N, Core[] cores, int coreNum) {
		if (cnt == coreNum) {
			if (core > maxCore) {
				maxCore = core;
				minLen = len;
			} else if (core == maxCore && minLen > len) {
				minLen = len;
			}
			return;
		}

		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		for (int i = 0; i < dr.length; i++) {
			int nr = cores[cnt].r;
			int nc = cores[cnt].c;
			int length = 0; // 전선길이
			while (true) {
				nr += dr[i];
				nc += dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
					break;
				}

				if (board[nr][nc] == '1') { // 중간에 코어를 만나면 전선길이 초기화
					length = 0;
					break;
				}

				length++;
			}

			if (length == 0) { // 전원에 연결 못했을 경우
				dfs(cnt + 1, core, len, board, N, cores, coreNum);
			} else { // 전원에 연결한 경우
				nr = cores[cnt].r;
				nc = cores[cnt].c;
				for (int j = 0; j < length; j++) { // 전선 연결
					nr += dr[i];
					nc += dc[i];
					board[nr][nc] = '1';
				}

				dfs(cnt + 1, core + 1, len + length, board, N, cores, coreNum);

				nr = cores[cnt].r;
				nc = cores[cnt].c;
				for (int j = 0; j < length; j++) { // 원상복구
					nr += dr[i];
					nc += dc[i];
					board[nr][nc] = '0';
				}
			}
		} // end of 사방탐색
	} // end of dfs

}
