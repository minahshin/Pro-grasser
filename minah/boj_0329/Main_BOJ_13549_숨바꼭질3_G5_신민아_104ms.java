package boj_0329;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_13549_숨바꼭질3_G5_신민아_104ms {
	static class Node {
		int time;
		int location;

		public Node(int time, int location) {
			this.time = time;
			this.location = location;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		boolean[] visited = new boolean[100001];
		int subin = Integer.parseInt(st.nextToken());
		int bro = Integer.parseInt(st.nextToken());
		
		Queue<Node> queue = new ArrayDeque<Node>();
		queue.offer(new Node(0, subin));
		visited[subin] = true;
		
		while(queue.size() > 0) {
			Node current = queue.poll();
			
			if(current.location == bro) {
				System.out.print(current.time);
				return;
			}
			
			int nextLoc = current.location * 2; // 순간이동인 경우
			if(nextLoc < 100001 && !visited[nextLoc]) {
				queue.offer(new Node(current.time, nextLoc)); // 0초 후에 바로 이동(시간 소요 x)
				visited[nextLoc] = true; // 시간 소요가 없으므로 greedy 적으로 생각하여 가장 먼저 배치하기
			}
			
			// 이 이후에는 1초씩 소요되는 이동 방법이므로 우선순위를 뒤로 뻼
			int nextTime = current.time + 1;
			nextLoc = current.location - 1;
			if(nextLoc > -1 && !visited[nextLoc]) {
				queue.offer(new Node(nextTime, nextLoc));
				visited[nextLoc] = true;
			}
			
			nextLoc = current.location + 1;
			if(nextLoc < 100001 && !visited[nextLoc]) {
				queue.offer(new Node(nextTime, nextLoc));
				visited[nextLoc] = true;
			}
			
		}
		
	}

}
