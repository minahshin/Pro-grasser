package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_13549_숨바꼭질3_G5_이선민_192ms {

	/** x : 현재 위치, time : x까지 오는데 걸린 시간 */
	static class Pos implements Comparable<Pos> {
		int x, time;

		public Pos(int x, int time) {
			super();
			this.x = x;
			this.time = time;
		}

		@Override
		public int compareTo(Pos o) {
			return this.time - o.time;
		}
	}	// end of Pos class

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 0 ≤ 수빈, 동생의 위치 ≤ 100,000
		int subin = Integer.parseInt(st.nextToken()); // 수빈이의 위치
		int sister = Integer.parseInt(st.nextToken()); // 동생의 위치

		boolean[] visitied = new boolean[100001];
		PriorityQueue<Pos> q = new PriorityQueue<Pos>();
		q.add(new Pos(subin, 0));
		while (!q.isEmpty()) {
			Pos current = q.poll();
			if (visitied[current.x]) {
				continue;
			}

			if (current.x == sister) {
				System.out.println(current.time);
				return;
			}
			visitied[current.x] = true;

			if (current.x + 1 <= 100000) {
				q.add(new Pos(current.x + 1, current.time + 1));
			}
			if (current.x - 1 >= 0) {
				q.add(new Pos(current.x - 1, current.time + 1));
			}
			if (current.x * 2 <= 100000) {
				q.add(new Pos(current.x * 2, current.time));
			}
		}
	} // end of main

}
