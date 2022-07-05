import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_13549_숨바꼭질3_G5_이승연_136ms {
	static class Loc{
		int time; 
		int loc;
		
		public Loc(int time, int loc) {
			super();
			this.time = time;
			this.loc = loc;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int result = 0; 
		
		if(K<=N) {
			result = N-K;
		} else {
			int[] visited_time = new int[100001]; 
			Arrays.fill(visited_time, -1);
			
			Queue<Loc> queue = new LinkedList<Loc>();
			queue.add(new Loc(0, N));
			visited_time[N] = 1; 
			
			while(!queue.isEmpty()) {
				Loc loc = queue.poll();
				
				int nLoc = loc.loc-1; 
				if(nLoc>=0) {
					if(visited_time[nLoc] == -1 || visited_time[nLoc]>loc.time+1) {
						visited_time[nLoc] = loc.time+1;
						queue.add(new Loc(loc.time+1, nLoc));
					}
				}
				
				nLoc = loc.loc+1; 
				if(nLoc<=100000) {
					if(visited_time[nLoc] == -1 || visited_time[nLoc]>loc.time+1) {
						visited_time[nLoc] = loc.time+1;
						queue.add(new Loc(loc.time+1, nLoc));
					}
				}
				
				nLoc = loc.loc*2; 
				if(nLoc<=100000) {
					if(visited_time[nLoc] == -1 || visited_time[nLoc]>loc.time) {
						visited_time[nLoc] = loc.time;
						queue.add(new Loc(loc.time, nLoc));
					}
				}
			}
			result = visited_time[K];
		}
	
		System.out.println(result);
	}
}
