package boj_0331;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// idea : 완탐 + List 사용해도 152ms로 가능
// idea(?) : 매번 집이랑 치킨을 1:1로 검색하는 것 보단 PQ 등을 이용해서 첫번째 원소만 가지고 비교하는 것은..?
public class Main_BOJ_15686_치킨배달_G5_신민아_152ms {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int size = Integer.parseInt(st.nextToken()); // map의 크기
		int remainChicken = Integer.parseInt(st.nextToken()); // 남겨야할 치킨집
		
		List<Point> houses = new ArrayList<Point>(2*size); // 집의 위치
		List<Point> chickens = new ArrayList<Point>(13); // 치킨집의 위치
		
		for(int i=0;i<size;i++) { // 직접 map을 저장하지 않고 좌표만 저장
			String line = br.readLine();
			for(int j=0,index=0;j<size;j++,index+=2) {
				char current = line.charAt(index); // 현재 위치가
				
				if(current == '1') houses.add(new Point(i, j)); // 1이면 집의 좌표 저장
				else if(current == '2')	chickens.add(new Point(i, j)); // 2면 치킨집의 좌표 저장
			}
		}
		int[] answer = {Integer.MAX_VALUE}; // 임의의 값 : Math.min 비교를 위해 MAX_VALUE 설정
		
		// 치킨집을 남겨두는 조합을 통해 최소의 치킨 거리를 푸는 메소드
		getChickenComb(0, 0, new int[remainChicken], answer, houses, chickens, chickens.size());
		System.out.print(answer[0]);
	}
	
	// parameter : count-현재 몇개를 뽑았는지 카운트, start-조합을 뽑기 시작하는 숫자, index-chicken 배열에서 뽑아야 하는 index, answer-답 저장용 배열(call by ref 이용)
	//				house-집의 좌표, chicken-치킨집의 좌표, chickenCount-치킨집의 개수(메소드 호출을 최소화하기 위함)
	private static void getChickenComb(int count, int start, int[] index, int[] answer,
										List<Point> house, List<Point> chicken, int chickenCount) {
		if(count == index.length) {
			answer[0] = getDistSum(answer[0], index, house, chicken); // 거리를 계산하는 메소드
			return;
		}
		
		// 평범한 조합 코드
		for(int i=start;i<chickenCount;i++) {
			index[count] = i;
			getChickenComb(count + 1, i+1, index, answer, house, chicken, chickenCount);
		}
	}
	
	// 집으로부터 남은 치킨집을 각각 1:1로 거리를 검사함
	// PriorityQueue와 같은 방법으로 사용해도 되..나?
	// parameter : lastAnswer-현재까지의 최소 치킨거리, index-chicken 배열에서 살아남은 매장, house-집의 좌표, chicken-치킨집의 좌표
	private static int getDistSum(int lastAnswer, int[] index, List<Point> house, List<Point> chicken) {
		int houseCount = house.size(); // 치킨 집 개수
		int sum = 0; // 치킨 거리의 합
		
		for(int i=0;i<houseCount;i++) {
			int temp = 10000; // Math.min을 위해 임의의 큰 값 작성
			for(int j=0;j<index.length;j++) {
				temp = Math.min(temp, getDistance(house.get(i), chicken.get(index[j]))); // 실제 거리를 1:1로 검사하며 작은 값으로 update
			}
			
			sum += temp; // 한 집에 대해 최소 치킨거리를 구했으면 sum에 치킨거리를 더함
			if(sum > lastAnswer) return lastAnswer; // 중간에 이전의 최소 치킨 거리를 넘었다면 이전의 최소 치킨거리를 리턴하고 끝
			
		}
		
		return sum;
	}
	
	// |r1 - r2| + |c1 - c2|
	private static int getDistance(Point house, Point chicken) {
		return Math.abs(house.r - chicken.r) + Math.abs(house.c - chicken.c);
	}
}
