import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_12851_숨바꼭질2_G5_이재순_148ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if (K<N) {
			System.out.println((new StringBuilder()).append(N-K).append("\n").append(1));
			return;
		}
		
		int end = Math.min(2*K+1, 100001);
		boolean[] map = new boolean[end];
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(N);
		int cnt = 0;
		int dis = 0;
		
		while (cnt == 0) {
			int size = q.size();
			dis++;
			for (int i = 0,a,b,c; i < size; i++) {
				int X = q.poll();
				map[X] = true;
				if (X==K) {
					cnt++;
					continue;
				}
				a = X+1;
				b = X-1;
				c = X*2;
				if (a<end&&!map[a]) 
					q.offer(a);
				if (b>-1&&!map[b]) 
					q.offer(b);
				if (c<end&&!map[c]) 
					q.offer(c);
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(dis-1).append("\n").append(cnt);
		System.out.println(sb);
	}
}
