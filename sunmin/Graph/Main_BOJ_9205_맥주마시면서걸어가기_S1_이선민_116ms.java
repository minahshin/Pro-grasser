package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_9205_맥주마시면서걸어가기_S1_이선민_116ms {
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	} // end of Pos class

	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		StringTokenizer st = null;
		for (int test_case = 0; test_case < TC; test_case++) {
			int convi = Integer.parseInt(br.readLine()); // 편의점 개수
			st = new StringTokenizer(br.readLine(), " ");
			// 상근이 집
			Pos home = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			List<Pos> convis = new ArrayList<Pos>(); // 편의점 위치
			for (int i = 0; i < convi; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				convis.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			st = new StringTokenizer(br.readLine(), " ");
			// 페스티벌 장소
			Pos fest = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			findConvi(home, convis, convi, fest);
		} // end of test_case
		System.out.println(sb.toString());
	} // end of main

	/**
	 * temp(현재 위치)로부터 bfs로 갈수있는 편의점 찾기
	 */
	private static void findConvi(Pos home, List<Pos> convis, int convi, Pos fest) {
		Queue<Pos> q = new ArrayDeque<Pos>();
//		boolean[][] visited = new boolean[32767 + 32768 + 1][32767 + 32768 + 1];
		boolean[] visited = new boolean[convi];
		q.offer(home);
		while (!q.isEmpty()) {
			Pos temp = q.poll();
			// 지금 위치에서 페스티벌 장소를 갈수있으면 바로 종료
			if (Math.abs(temp.x - fest.x) + Math.abs(temp.y - fest.y) <= 1000) {
				sb.append("happy\n");
				return;
			}
			for (int i = 0; i < convi; i++) {
				if (visited[i]) {
					continue;
				}
				if (Math.abs(temp.x - convis.get(i).x) + Math.abs(temp.y - convis.get(i).y) <= 1000) {
					visited[i] = true;
					q.offer(convis.get(i));
				}
			}
		}
		
		sb.append("sad\n");
	} // end of findConvi

} // end of class
