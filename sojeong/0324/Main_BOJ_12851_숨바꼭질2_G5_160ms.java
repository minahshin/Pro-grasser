
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_12851_숨바꼭질2_G5_160ms{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int time =0;
		int cnt =0;
		boolean[] visited = new boolean[100001];
		
		if(N == K) {
			System.out.println(0);
			System.out.println(1);
			return;
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(N);
		visited[N] = true;
		
		while(!q.isEmpty()) {
			time++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				visited[cur] = true;
				if(cur == K) {		
					cnt++;
					continue;
				}
				if(cur+1<=100000 && !visited[cur+1]) q.add(cur+1);
				if(cur-1>=0 && !visited[cur-1])	q.add(cur-1);
				if(cur*2<=100000 && !visited[cur*2]) q.add(cur*2);
				
			}
			if(cnt != 0) q.clear();
		}
		System.out.println(time-1);
		System.out.println(cnt);
		
		
	}

}
