package com.ssafy.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_5643_키순서_D4_윤성도 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());	// 테스트케이스의 수 (1 ≤ T ≤ 15)
		StringBuilder sb = new StringBuilder();
		
		for (int TC = 1; TC <= T; ++TC) {	// 입력받은 테스트케이스의 수만큼 반복
			int N = Integer.parseInt(br.readLine());	// 학생들의 수 (2 ≤ N ≤ 500)
			ArrayList<ArrayList<Integer>> adj = new ArrayList<>(N + 1);	// 인접리스트 생성
			for (int i = 0; i <= N; ++i)
				adj.add(new ArrayList<>());
			int M = Integer.parseInt(br.readLine());	// 비교 횟수 (0 ≤ M ≤ N*(N-1)/2)
			while (M-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				adj.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));	// 작은 학생의 리스트에 큰 학생 번호 추가
			}
			
			int[] count = new int[N + 1];	// 나보다 작은 학생의 수 + 나보다 큰 학생의 수
			Queue<Integer> q = new ArrayDeque<>();	// BFS를 위한 큐 생성
			for (int i = 1; i <= N; ++i) {	// 모든 학생에 대해 BFS 실행
				q.add(i);
				boolean[] visit = new boolean[N + 1];
				visit[i] = true;
				while (!q.isEmpty()) {
					int cur = q.poll();
					for (int next : adj.get(cur)) {
						if (visit[next])	// 중복 방지
							continue;
						visit[next] = true;	// 중복 방지
						++count[next];	// 큰 학생보다 작은 학생의 수 1 추가
						++count[i];	
						// 나보다 큰 학생 수 1 추가
						q.offer(next);
					}
				}
			}
			int ans = 0;	//
			for (int i = 1; i <= N; ++i)
				if (count[i] == N - 1)	// 나보다 작은 학생의 수 + 나보다 큰 학생의 수가 N - 1이면 정답 추가
					ans++;
			sb.append("#").append(TC).append(" ").append(ans).append("\n");
		}
		System.out.print(sb.toString());
	}
}
