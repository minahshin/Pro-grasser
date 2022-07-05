import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_13549_숨바꼭질3_G5_이재순_112ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if (K<=N) {
			System.out.println(N-K);
			return;
		}
		
		int end = Math.min(2*K+1, 100001);
		boolean[] map = new boolean[end];
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(N);
		map[N] = true;
		
		int dis = 0;
		
		if (N!=0) {
			while ((N*=2)<end) {
				if (N==K) {
					System.out.println(dis);
					return;
				}
				if (!map[N]) {
					map[N] = true;
					q.offer(N);
				}
			}
		}
		while (true) {
			int size = q.size();
			dis++;
			for (int i = 0,a,b; i < size; i++) {
				int X = q.poll();
				a = X+1;
				b = X-1;
				if (a==K||b==K) {
					System.out.println(dis);
					return;
				}
				
				if (a<end&&!map[a]) {
					map[a] = true;
					q.offer(a);
				}
				
				if (b>0&&!map[b]) {
					map[b] = true;
					q.offer(b);
				}
				
				while ((a*=2)<end) {
					if (a==K) {
						System.out.println(dis);
						return;
					}
					if (!map[a]) {
						map[a] = true;
						q.offer(a);
					}
				}
				
				if (b>0) {
					while ((b*=2)<end) {
						if (b==K) {
							System.out.println(dis);
							return;
						}
						if (!map[b]) {
							map[b] = true;
							q.offer(b);
						}
					}
				}
			}
		}
	}
}
