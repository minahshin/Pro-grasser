package com.ssafy.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20040_사이클게임_G4_윤성도_rank_632ms {
	static int[] root, rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		root = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++)
			root[i] = i;
		int a, b;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if (union(a, b)) {
				System.out.print(i + 1);
				return;
			}
		}
		System.out.print(0);
	}

	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b)
			return true;
		if (rank[a] < rank[b]) {
			root[a] = b;
		} else {
			root[b] = a;
			if (rank[a] == rank[b])
				rank[a]++;
		}
		return false;
	}

	static int find(int a) {
		if (root[a] == a)
			return a;
		return root[a] = find(root[a]);
	}
}
