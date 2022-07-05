package boj_0329;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Floyd를 사용하여 구현
public class Main_BOJ_14938_서강그라운드_G4_신민아_104ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int INF = 100000; // 임의의 INF 값 : Integer.MAX_VALUE를 두면 overflow때문에 처리가 귀찮아짐
		int area = Integer.parseInt(st.nextToken()); // 지역의 개수
		int maxWalkBound = Integer.parseInt(st.nextToken()) + 1; // 걸을 수 있는 범위(<= 연산을 하지 않기 위해 +1을 미리 함)
		int road = Integer.parseInt(st.nextToken()); // 길의 개수
		
		// map initialization
		int[][] distances = new int[area+1][area+1];
		for(int i=1;i<distances.length;i++) {
			for(int j=1;j<distances.length;j++) {
				if(i != j) // 출발점과 끝점이 같다면 거리가 0이므로 패스
					distances[i][j] = INF; // 같지 않다면 INF 처리
			}
		}
		
		// item initialization
		st = new StringTokenizer(br.readLine(), " ");
		int[] items = new int[area+1]; // item 개수 저장
		for(int i=1;i<items.length;i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		// road initialization : set accurate distances from input
		for(int i=0;i<road;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			distances[p1][p2] = distances[p2][p1] = weight; // 양방향이므로 양쪽으로 저장
		}
		
		// call by ref 이용 -> return 필요 없음
		getMinDistanceByFloyd(distances); // floyd로 최단 거리 get
		
		// floyd를 통해 구한 최단 거리로 출발점으로부터 돌아다닐 수 있는 거리 내에서 item 줍기
		int answer = 0;
		for(int i=1;i<distances.length;i++) {
			int temp = 0; // 임시
			for(int j=1;j<distances.length;j++) {
				if(distances[i][j] < maxWalkBound) // 이동 반경 내에 도착점이 존재한다면
					temp += items[j]; // temp 증가
			}
			answer = Math.max(answer, temp);
		}
		
		System.out.print(answer);
	}
	
	// Floyd
	private static void getMinDistanceByFloyd(int[][] dist) {
		for(int m=1;m<dist.length;m++) {
			for(int s=1;s<dist.length;s++) {
				if(m == s) continue;
				for(int e=1;e<dist.length;e++) {
					if(m == e || s == e) continue;
					dist[s][e] = Math.min(dist[s][e], dist[s][m] + dist[m][e]);
				}
			}
		}
	}
	
}
