package boj_0329;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_BOJ_14502_연구소_G5_신민아_460ms {
	static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		int rSize = line.charAt(0) - '0';
		int cSize = line.charAt(2) - '0';
		
		char[][] map = new char[rSize][cSize];
		List<Node> safePoints = new ArrayList<Node>(); // 안전한 구역 배열 : 벽을 세우기 위해 사용(조합에 사용)
		List<Node> viruses = new ArrayList<Node>(); // 바이러스가 위치하는 곳의 배열(진원지) : 추후에 BFS를 통해 바이러스 확산(4방으로 확산)
		
		for(int i=0;i<rSize;i++) {
			line = br.readLine();
			for(int j=0,index=0;j<cSize;j++,index+=2) {
				map[i][j] = line.charAt(index); // map에 입력 값 받기
				
				if(map[i][j] == '0') safePoints.add(new Node(i, j)); // 0인 곳의 좌표를 list에 저장
				else if(map[i][j] == '2') viruses.add(new Node(i, j)); // 2인 곳(바이러스)의 좌표를 list에 저장
				
			}
		}
		
		int maxSafeArea = 0;
		// selectWallLocation : 현재까지 조합한 개수, 조합 시작점, 조합 결과물, 안전한 방의 최대 개수(call by ref 이용), 연구실 상황, 안전한 곳 리스트, 안전한 곳 개수, 바이러스 진원지 리스트
		maxSafeArea = Math.max(maxSafeArea, selectWallLocation(0, 0, new int[3], new int[1], map, safePoints, safePoints.size(), viruses));
		
		System.out.print(maxSafeArea);
	}
	
	// selectWallLocation : 안전한 곳의 리스트를 받아 벽을 세우는 경우의 수를 구하여 바이러스 확산을 계산하게 하는 메소드
	// parameter : 현재까지 조합한 개수, 조합 시작점, 조합 결과물, 안전한 방의 최대 개수(call by ref 이용), 
	//				연구실 상황, 안전한 곳 리스트, 안전한 곳 개수, 바이러스 진원지 리스트
	private static int selectWallLocation(int count, int start, int[] index, int[] curMax,
											char[][] map, List<Node> safePoints, int safePointsCount, List<Node> viruses) {
		// 벽은 3개를 반드시 세워야하므로 3개를 모두 고르면 바이러스를 퍼트리면 됨
		if(count == 3) {
			// getSafeCounts : 벽을 세운 후 바이러스를 BFS로 4방 탐색하여 퍼트리고, 남은 안전한 구역의 개수를 찾는 메소드
			// parameter : 이전까지의 안전한 방의 최대 개수, 조합 결과물, 연구실, 안전한 곳 리스트, 바이러스 진원지 리스트
			curMax[0] = Math.max(curMax[0], getSafeCounts(curMax[0], index, map, safePoints, viruses));
			return curMax[0];
		}
		
		// 중복을 허용하지 않는 조합코드
		for(int i=start;i<safePointsCount;i++) {
			index[count] = i;
			selectWallLocation(count+1, i+1, index, curMax, map, safePoints, safePointsCount, viruses);
		}
		
		// 현재까지 정해진 정답 리턴 : 0을 리턴하면 for문이 끝났을 때 0을 리턴하므로 값에 대한 정보를 잃어버림
		return curMax[0];
	}
	
	/*
	 * 1. 새로운 맵 복사
	 * 2. 조합으로 정해진 벽을 세워야 하는 곳에 벽으로 변경
	 * 3. Queue에 바이러스 offer
	 * 4. 4방 탐색을 하며 맵의 안쪽에 벽을 만나기 전까지 안전 구역에 바이러스 확산 by BFS
	 * 5. visit check by == '0' --> not visited yet
	 * 6. 가지치기를 위해 이전의 안전한 방의 개수보다 적으면 무조건 빠져나옴
	 */
	private static int getSafeCounts(int beforeMax, int[] index, char[][] map, List<Node> safePoints, List<Node> viruses) {
		int curSafeCount = safePoints.size() - 3; // 벽을 3개 세울 예정이므로 미리 3개 빼줌
		char[][] newMap = copyMap(map); // 기존에 있는 맵을 바꾸면 곤란하므로 copy
		
		for(int i=0;i<index.length;i++) { // 실제로 벽을 세워주기
			Node w = safePoints.get(index[i]);
			newMap[w.r][w.c] = '1';
		}
				
		// BFS로 바이러스가 확산 되는 것을 구현하기 위해 진원지를 먼저 offer하기
		Queue<Node> virusPath = new ArrayDeque<Node>();
		for(int i=0;i<viruses.size();i++) {
			virusPath.offer(viruses.get(i));
		}
		
		// 4방 탐색하면서 BFS로 확산
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		// 중간에 curSafeCount가 beforeMax보다 작아진다면 while문 종료
		while(virusPath.size() > 0) {
			if(curSafeCount < beforeMax) 
				break;
			
			Node curVirus = virusPath.poll();
			
			for(int d=0;d<4;d++) {
				int nr = curVirus.r + dr[d];
				int nc = curVirus.c + dc[d];
				
				if(isNotAvailable(nr, nc, newMap)) continue; // 배열 밖으로 나가면 다음으로
				if(newMap[nr][nc] == '0') { // 배열 안쪽이며 안전 구역일 경우만
					virusPath.offer(new Node(nr, nc)); // 바이러스가 확산 될 수 있으므로 queue에 offer
					newMap[nr][nc] = '2'; // 바이러스 확산 체크 == visit check
					curSafeCount--; // 확산 되었으므로 방을 줄임
				}
				
			}
		}
				
		return curSafeCount;
	}
	
	// 기존의 연구실에서 복사하여 벽 세우고 바이러스 확산을 시뮬레이션하기 위한 메소드
	private static char[][] copyMap(char[][] map) {
		char[][] newMap = new char[map.length][map[0].length];
		
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				newMap[i][j] = map[i][j];
			}
		}
		
		return newMap;
	}
	
	// 배열 범위를 나갔는지 확인
	private static boolean isNotAvailable(int nr, int nc, char[][] map) {
		return((nr < 0) || (nr >= map.length) || (nc < 0) || (nc >= map[0].length));
	}
	
}
