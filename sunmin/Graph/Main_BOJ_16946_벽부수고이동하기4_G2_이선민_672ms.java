package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BOJ_16946_벽부수고이동하기4_G2_이선민_672ms {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] origin = new char[N][]; // 원래 맵
		for (int i = 0; i < origin.length; i++) {
			origin[i] = br.readLine().toCharArray();
		}

		int[] setNum = new int[N * M + 1]; // 인덱스 번호의 집합이 가진 0 개수
		int[][] set = new int[N][M]; // 집합의 번호(1번부터 시작)
		makeSet(N, M, setNum, origin, set); // 0끼리 붙어있는 부분을 집합으로 만들기

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < origin.length; i++) {
			for (int j = 0; j < origin[i].length; j++) {
				if (origin[i][j] == '0') {
					sb.append(0);
					continue;
				}
				sb.append(search(i, j, set, setNum, N, M));
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());

	} // end of main

	// 상, 우, 하, 좌 순서
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	/**
	 * (r, c) 기준 사방에 존재하는 0 집합 탐색
	 */
	private static int search(int r, int c, int[][] set, int[] setNum, int N, int M) {
		int cnt = 1; // 자기 자신
		Set<Integer> check = new HashSet<>(); // 사방에 같은 번호의 집합이 있을 경우를 위해
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
				continue;
			}
			check.add(set[nr][nc]);
		}

		for (Integer integer : check) {
			cnt += setNum[integer];
		}

		return cnt % 10;
	} // end of search

	/**
	 * 0끼리 붙어있는 부분을 집합으로 만들기
	 */
	private static void makeSet(int N, int M, int[] setNum, char[][] origin, int[][] set) {
		int index = 1; // 집합 번호는 1부터 시작
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (origin[i][j] == '1' || set[i][j] != 0) {
					continue;
				}
				setNum[index] = bfs(i, j, origin, N, M, set, index++);
			}
		}
	} // end of makeSet

	/**
	 * 붙어있는 0끼리 집합으로 만들고 0 개수 반환
	 */
	private static int bfs(int r, int c, char[][] origin, int N, int M, int[][] set, int index) {
		int cnt = 1;
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] { r, c });
		set[r][c] = index;
		while (!q.isEmpty()) {
			int[] temp = q.poll();

			for (int i = 0; i < dr.length; i++) { // 사방탐색
				int nr = temp[0] + dr[i];
				int nc = temp[1] + dc[i];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || set[nr][nc] != 0 || origin[nr][nc] == '1') {
					continue;
				}
				q.add(new int[] { nr, nc });
				cnt++;
				set[nr][nc] = index;

			}
		} // end of while

		return cnt;
	} // end of bfs

} // end of class
