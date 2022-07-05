import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_13172_Σ_G4_이재순_180ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		long sum=0;
		for (int i = 0, a, b; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			b = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			sum+= ferma(a, b);
		}
		System.out.println(sum%1000000007);
	}
	
	private static long ferma(long a, long b) {
		long temp=1;
		int number = 1000000007;
		int r = number-2;
		while (r!=1) {
			if ((r&1)==1) 
				temp = ((b*temp)%number);
			b = (b*b)%number;
			r>>=1;
		}
		b = (b*temp)%number;
		return (a*b)%number;
	}
}
