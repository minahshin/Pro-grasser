import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_JUNGOL_2577_회전초밥_이승연_449ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 회전 초밥 벨트에 놓인 접시의 수 (2<=N<=3000000)
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수 (2<=d<=3000)
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수 (2<=k<=3000) 
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호 (1<=c<=d)
		
		int[] sushi = new int[N+k];
		
		for(int i=0; i<N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=0, cnt=k-1; i<cnt; i++) {
			sushi[N+i] = sushi[i];
		}
		
		System.out.println(calcMaxKind(sushi, N, d, k, c));
	}
	
	private static int calcMaxKind(int[] sushi, int N, int d, int k, int c) {
		int result = 0;
		int[] kind = new int[d+1]; // 먹은 스시 종류 체크 
		
		for(int i=0; i<k; i++) {
			if(kind[sushi[i]]==0) result++;
			kind[sushi[i]]++;			
		}
		
		int max = result; 
		
		for(int i=1; i<N; i++) {
			if(kind[sushi[i-1]]==1) result--;
			kind[sushi[i-1]]--;
			if(kind[sushi[i+k-1]]==0) result++;
			kind[sushi[i+k-1]]++;
			
			if(kind[c]==0) max = Math.max(max, result+1);
			else max = Math.max(max, result);

		}
		
		return max; 
	}
}
