import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_5643_키순서_D4_이승연_419ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine()); // 1<=TC<=15
		
		ArrayList<Integer>[] adjList;
		ArrayList<Integer>[] adjList_r;
		
		for(int testCase=1; testCase<=TC; testCase++) {
			int N = Integer.parseInt(br.readLine()); // 학생 수 (2<=N<=500)
			int M = Integer.parseInt(br.readLine()); // 0<=M<=N*(N-1)/2
			adjList = new ArrayList[N+1];	
			adjList_r = new ArrayList[N+1];	
			
			for(int i=0; i<=N; i++) {
				adjList[i] = new ArrayList<Integer>();
				adjList_r[i] = new ArrayList<Integer>();
			}
		
			int result = 0;
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				adjList[a].add(b);
				adjList_r[b].add(a);
			}
			
			int[] totalCnt = new int[N+1];
			Queue<Integer> queue = new LinkedList<Integer>();
			
			for(int i=1; i<=N; i++) { // 1부터 N까지 
				boolean[] visited = new boolean[N+1]; // 동일한 수가 세졌는지 
				// 자기보다 키 큰 학생
				for(int j=0, cnt=adjList[i].size(); j<cnt; j++) {
					queue.offer(adjList[i].get(j));
					visited[adjList[i].get(j)] = true;
				}			
				while(!queue.isEmpty()) {
					int stu_n = queue.poll();
					totalCnt[i]++;
						
					for(int j=0, cnt=adjList[stu_n].size(); j<cnt; j++) {
						if(!visited[adjList[stu_n].get(j)]) {
							queue.offer(adjList[stu_n].get(j));		
							visited[adjList[stu_n].get(j)] = true;
						}
					}			
				}
				// 자기보다 키 작은 학생
				for(int j=0, cnt=adjList_r[i].size(); j<cnt; j++) {
					queue.offer(adjList_r[i].get(j));
					visited[adjList_r[i].get(j)] = true;
				}			
				while(!queue.isEmpty()) {
					int stu_n = queue.poll();
					totalCnt[i]++;
					
					for(int j=0, cnt=adjList_r[stu_n].size(); j<cnt; j++) {
						if(!visited[adjList_r[stu_n].get(j)]) {
							queue.offer(adjList_r[stu_n].get(j));
							visited[adjList_r[stu_n].get(j)] = true;
						}
					}			
				}
			}

			for(int i=1; i<=N; i++) {
				if(totalCnt[i] == N-1) {
					result++;
				}
			}		
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
