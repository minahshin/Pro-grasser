package boj_0324;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// idea(?) : 이걸.. 진작에 풀었더라면.. 토X 코테문제를 풀수 있었을지도..? 흑
// idea : BFS + Queue(visit 체크를 push할 때 말고 pop할때 하면 경우의 수 체크가 가능!)
public class Main_BOJ_12851_숨바꼭질_G5_신민아_144ms {
	static class Node {
		int time; // 현재 위치로 도달할 때의 시간
		int loc; // 현재 위치

		public Node(int time, int loc) {
			this.time = time;
			this.loc = loc;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int subinLoc = Integer.parseInt(st.nextToken()); // 수빈의 위치
		int broLoc = Integer.parseInt(st.nextToken()); // 동생의 위치
		
		int time = Integer.MAX_VALUE; // 최소 시간 저장
		int ways = 0; // 최소 시간에 갈 수 있는 방법
		
		ArrayDeque<Node> queue = new ArrayDeque<Node>();
		queue.offer(new Node(0, subinLoc)); // 출발점 저장
		
		boolean[] visited = new boolean[100001]; // 방문 배열
		
		while(queue.size() > 0) { // 큐가 빌 때까지
			Node current = queue.poll(); // 현재 위치 뽑기
			visited[current.loc] = true; // 현재 위치의 방문 체크를 poll할 때 체크 : 최소시간에 갈 수 있는 경우의 수를 구하기 위해
			
			if(current.time > time) { // 이미 현재 시간이 최소 시간을 넘겼을 경우 뒤도 안돌아보고 ㅌㅌ
				break;
			}
			
			if(current.loc == broLoc) { // 현재 위치가 동생의 위치랑 같으면
				time = current.time; // 시간 저장 : queue에 저장되는 데이터는 나중에 push될수록 time이 점점 늘어나기 때문에 별다른 체크가 필요 없음
				ways++; // 경우의 수 증가
				continue;
			}
			
			int nextTime = current.time + 1; // 1초 후
			if(current.loc > 0 && !visited[current.loc-1]) { // x-1의 위치로 갈 수 있다면
				queue.offer(new Node(nextTime, current.loc-1)); // push
			}
			
			if(current.loc < 100000 && !visited[current.loc+1]) { // x+1의 위치로 갈 수 있다면
				queue.offer(new Node(nextTime, current.loc+1));
			}
			
			if(current.loc < 50001 && !visited[2*current.loc]) { // 2*x(순간이동)으로 갈 수 있다면
				queue.offer(new Node(nextTime, 2*current.loc));
			}

		}
		
		System.out.print(time + "\n" + ways); // 출력
	}

}
