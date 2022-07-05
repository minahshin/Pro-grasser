package boj_0412;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// idea : 2차원 배열에서 dp[i][j] = dp[i-1][j] + dp[i][j-1];
// idea : 각 자릿수의 0부터 시작하는 친구들은 무조건 경우의 수가 1이다
public class Main_BOJ_11057_오르막수_S1_신민아_76ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int mod = 10007;
		int count = Integer.parseInt(br.readLine());
		int[][] inc = new int[count+1][11];
		
		Arrays.fill(inc[1], 1); // 1자릿수 배열은 시작하는 첫번째 자리마다 1개씩만 존재하므로 1로 초기화
		
		for(int i=2;i<inc.length;i++) {
			inc[i][0] = 1; // 각 자리수의 0으로 시작하는 숫자는 00, 000, 0000.. 등의 한 가지 경우밖에 없으므로 1로 초기화
			for(int c=1;c<10;c++) {
				inc[i][c] = (inc[i-1][c] + inc[i][c-1]) % mod; // 규칙에 따라 저장 후 나머지 연산
			}			
		}
		
		inc[count][10] = 0; // 초기화
		for(int c=0;c<10;c++) {
			inc[count][10] = (inc[count][10] + inc[count][c]) % mod;
		}
		
		System.out.print(inc[count][10]);
	}

}
