import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class Main_BOJ_14502_연구소_G5_이재순_284ms {
	private static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		Virus virus = null;
		int ans = -3;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0, idx = 0; j < M; j++, idx+=2) {
				map[i][j] = line.charAt(idx);
				switch (map[i][j]) {
				case '2':
					virus = new Virus(i, j, virus);
					break;
				case '0':
					ans++;
					break;
				}
			}
		}
		
		combination(0, map, virus, 0, 0);
		
		
		System.out.println(ans-min);
	}
	private static void combination(int cnt, char[][] map, Virus virus, int r, int c) {
		if (cnt==3) {
			int temp = bfs(map, virus);
			if (temp<min) {
				min = temp;
			};
			return;
		}
		
		for (int i = r, size = map[0].length; i < map.length; i++) {
			for (int j = c+1; j < size; j++) {
				if (map[i][j]=='0') {
					map[i][j] = '1';
					combination(cnt+1, map, virus, i, j);
					map[i][j] = '0';
				}
			}
			c = 0;
		}
	}
	private static int bfs(char[][] map, Virus virus) {
		
		Queue<Virus> q = new ArrayDeque<Virus>();
		for (Virus cur = virus; cur != null; cur = cur.next) {
			q.offer(cur);
		}
		int M = map[0].length;
		boolean[][] visited = new boolean[map.length][M];
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		int temp = 0;
		while (!q.isEmpty()) {
			Virus cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if (nr>-1&&nr<map.length&&nc>-1&&nc<M&&map[nr][nc]=='0'&&!visited[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new Virus(nr, nc, null));
					temp++;
				}
			}
		}
		
		return temp;
	}
	
	static class Virus{
		int r, c;
		Virus next;
		public Virus(int r, int c, Virus next) {
			this.r = r;
			this.c = c;
			this.next = next;
		}
	}
}
