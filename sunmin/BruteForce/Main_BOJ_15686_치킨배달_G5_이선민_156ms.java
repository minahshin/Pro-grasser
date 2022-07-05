package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_15686_치킨배달_G5_이선민_156ms {
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	} // end of Pos class

	private static int M, ans;
	private static char[][] map;
	private static List<Pos> chickens;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 배열의 한변 크기
		M = Integer.parseInt(st.nextToken()); // 살려둘 치킨집의 개수
		map = new char[N][N]; // 도시
		chickens = new ArrayList<Pos>(); // 치킨집 위치 저장
		for (int i = 0; i < map.length; i++) {
			String s = br.readLine();
			for (int j = 0, index = 0; j < map.length; j++, index += 2) {
				map[i][j] = s.charAt(index);
				if (map[i][j] == '2') {
					chickens.add(new Pos(i, j));
				}
			}
		}

		ans = Integer.MAX_VALUE; // 도시의 최소 치킨거리
		comb(0, 0, chickens.size(), new int[M]);

		System.out.println(ans);
	} // end of main

	/**
	 * 살려둘 치킨집을 조합으로 구하기
	 */
	private static void comb(int cnt, int start, int size, int[] choice) {
		if (cnt == M) {
			chickDist(choice);
			return;
		}

		for (int i = start; i < size; i++) {
			choice[cnt] = i;
			comb(cnt + 1, i + 1, size, choice);
		}
	} // end of comb

	/**
	 * 도시의 치킨거리 구하기
	 */
	private static void chickDist(int[] choice) {
		int max = 0; // 도시의 치킨거리
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (max > ans) { // 지금까지 구한 도시의 최소 치킨거리보다 크다면 더이상 계산할 필요x
					return;
				}
				if (map[i][j] == '1') {
					int min = Integer.MAX_VALUE; // 해당 집의 치킨거리
					for (int k = 0; k < choice.length; k++) { // comb()에서 고른 치킨집 중 현재 집의 치킨거리 구하기
						int temp = Math.abs(i - chickens.get(choice[k]).r) + Math.abs(j - chickens.get(choice[k]).c);
						if (temp < min) {
							min = temp;
						}
					}
					max += min;
				}
			}
		}

		// 도시의 최소 치킨거리 갱신
		if (ans > max) {
			ans = max;
		}
	} // end of chickDist

} // end of class
