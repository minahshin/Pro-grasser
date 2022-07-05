package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_14890_경사로_G3_이선민_104ms {

	private static int N;
	private static boolean[][] slopes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		slopes = new boolean[N][N]; // 경사로를 놓은곳은 true
		int ans = 0;
		// 가로방향 확인
		boolean flag = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j == N - 1) {
					ans++;
					break;
				}

				if (map[i][j] == map[i][j + 1]) {
					continue;
				}

				if (Math.abs(map[i][j] - map[i][j + 1]) > 1) { // 높이 차이가 1 초과
					flag = false;
					break;
				}

				if (map[i][j] - map[i][j + 1] < 0) { // 올라가는 경사로 필요
					if (!slopeAvailable(i, j, map, L, false, true)) {
						flag = false;
						break;
					}
				} else { // 내려가는 경사로 필요
					if (!slopeAvailable(i, j + 1, map, L, false, false)) {
						flag = false;
						break;
					}
				}
			}

			// 지나갈 수 없는 길은 지금까지 놓은 경사로 없애줌
			if (!flag) {
				for (int j = 0; j < N; j++) {
					slopes[i][j] = false;
				}
			}

		} // 가로 확인 끝


		// 세로방향 확인
		flag = true;
		for (int i = 0; i < N; i++) {
			Arrays.fill(slopes[i], false);
		}
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				if (i == N - 1) {
					ans++;
					break;
				}

				if (map[i][j] == map[i + 1][j]) {
					continue;
				}

				if (Math.abs(map[i][j] - map[i + 1][j]) > 1) { // 높이 차이가 1 초과
					flag = false;
					break;
				}

				if (map[i][j] - map[i + 1][j] < 0) { // 올라가는 경사로 필요
					if (!slopeAvailable(i, j, map, L, true, true)) {
						flag = false;
						break;
					}
				} else { // 내려가는 경사로 필요
					if (!slopeAvailable(i + 1, j, map, L, true, false)) {
						flag = false;
						break;
					}
				}
			}

			// 지나갈 수 없는 길은 지금까지 놓은 경사로 없애줌
			if (!flag) {
				for (int i = 0; i < N; i++) {
					slopes[i][j] = false;
				}
			}
		} // 세로 확인 끝

		System.out.println(ans);

	} // end of main

	/**
	 * 경사로를 놓을 수 있으면 true, 없으면 false
	 * 
	 * verticalWay : true면 세로방향, false면 가로방향 up : true면 위로가는 경사로, false면 아래로가는 경사로
	 */
	private static boolean slopeAvailable(int r, int c, int[][] map, int L, boolean verticalWay, boolean up) {
		int[][] points = new int[L][2]; // 경사로를 놓을 위치들
		if (slopes[r][c]) {
			return false;
		}
		points[0][0] = r;
		points[0][1] = c;

		if (verticalWay) { // 수직방향
			if (up) { // 올라가는 경사로 필요
				for (int i = 1; i < L; i++) { // 경사로 길이만큼의 땅이 높이가 같아야함
					if (outOfBound(r - i, c) || slopes[r - i][c] || map[r][c] != map[r - i][c]) {
						return false;
					}

					points[i][0] = r - i;
					points[i][1] = c;
				}
			} else { // 내려가는 경사로 필요
				for (int i = 1; i < L; i++) { // 경사로 길이만큼의 땅이 높이가 같아야함
					if (outOfBound(r + i, c) || slopes[r + i][c] || map[r][c] != map[r + i][c]) {
						return false;
					}
					points[i][0] = r + i;
					points[i][1] = c;
				}
			}
		} else { // 수평방향
			if (up) { // 올라가는 경사로 필요
				for (int i = 1; i < L; i++) { // 경사로 길이만큼의 땅이 높이가 같아야함
					if (outOfBound(r, c - i) || slopes[r][c - i] || map[r][c] != map[r][c - i]) {
						return false;
					}
					points[i][0] = r;
					points[i][1] = c - i;
				}
			} else { // 내려가는 경사로 필요
				for (int i = 1; i < L; i++) { // 경사로 길이만큼의 땅이 높이가 같아야함
					if (outOfBound(r, c + i) || slopes[r][c + i] || map[r][c] != map[r][c + i]) {
						return false;
					}
					points[i][0] = r;
					points[i][1] = c + i;
				}
			}
		}

		// 경사로 놓기
		for (int i = 0; i < L; i++) {
			slopes[points[i][0]][points[i][1]] = true;
		}
		return true;
	} // end of slopeAvailable

	/**
	 * 배열의 밖으로 나가면 true, 내부면 false
	 */
	private static boolean outOfBound(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N) {
			return true;
		}
		return false;
	} // end of outOfBound

}
