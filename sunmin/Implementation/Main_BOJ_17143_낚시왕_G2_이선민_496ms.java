package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17143_낚시왕_G2_이선민_496ms {
	public static class Shark {
		int speed, dir, size;

		public Shark(int speed, int dir, int size) {
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	} // end of Shark class

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken()); // 상어의 수

		Shark[][] sharks = new Shark[R + 1][C + 1]; // 상어 정보 저장
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			if (d == 1 || d == 2) { // 상어가 상하로 움직이는 경우
				s %= (R - 1) * 2;
			} else { // 상어가 좌우로 움직이는 경우
				s %= (C - 1) * 2;
			}
			sharks[r][c] = new Shark(s, d, z);
		}

		int ans = 0; // 잡은 상어 크기의 합
		for (int i = 1; i <= C; i++) {
			// 땅과 제일 가까운 상어 잡기
			for (int j = 1; j <= R; j++) {
				if (sharks[j][i] != null) { // 상어가 있다면
					ans += sharks[j][i].size;
					sharks[j][i] = null; // 잡은 상어 없애기
					break; // 해당 열에서의 상어 낚시 끝내기
				}
			}

			// 마지막 열은 상어 이동시킬 필요없이 답 출력
			if (i == C) {
				break;
			}

			// 상어 이동
			moveShark(sharks, R, C);
		}

		System.out.println(ans);

	} // end of main

	/**
	 * 상어 이동
	 */
	private static void moveShark(Shark[][] sharks, int R, int C) {
		Shark[][] after = new Shark[R + 1][C + 1]; // 상어가 이동한 이후의 모습

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (sharks[i][j] != null) { // 상어가 있는 칸
					int row = i;
					int col = j;

					for (int k = 0; k < sharks[i][j].speed; k++) {
						if (sharks[i][j].dir == 1) { // 상
							row--; // 위로 한칸 이동

							if (row == 0) { // 경계를 넘어가면
								sharks[i][j].dir = 2; // 방향 반대로
								row = 2;
							}
						} else if (sharks[i][j].dir == 2) { // 하
							row++;

							if (row > R) {
								sharks[i][j].dir = 1;
								row = R - 1;
							}
						} else if (sharks[i][j].dir == 3) { // 우
							col++;

							if (col > C) {
								sharks[i][j].dir = 4;
								col = C - 1;
							}
						} else { // 좌
							col--;

							if (col == 0) {
								sharks[i][j].dir = 3;
								col = 2;
							}
						}
					} // 상어 한마리 이동 완료

					// 상어가 움직인 자리에 이미 다른 상어가 있지만 원래 있던 상어보다 크기가 큰 경우
					// 혹은 빈자리일 경우
					// 현재 상어(shark) 등록
					if ((after[row][col] != null && after[row][col].size < sharks[i][j].size)
							|| after[row][col] == null) {
						after[row][col] = new Shark(sharks[i][j].speed, sharks[i][j].dir, sharks[i][j].size);
					}
				}
			}
		} // 모든 상어 이동 완료

		// 상어 정보 깊은 복사
		for (int i = 0; i < after.length; i++) {
			System.arraycopy(after[i], 0, sharks[i], 0, after[i].length);
		}

	} // end of moveShark

} // end of class
