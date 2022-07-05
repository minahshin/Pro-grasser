package boj_0412;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

// idea : BigInteger 조와요 / 2차원 배열
// 킹치만.. 요구하는건 int형 배열일테니 노오력해서 풀어봐야할듯
public class Main_BOJ_11057_오르막수_S1_신민아_88ms_BigIntegerVer {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int count = Integer.parseInt(br.readLine());
		BigInteger[][] countOfIncreasing = new BigInteger[count+1][11];
		
		Arrays.fill(countOfIncreasing[1], new BigInteger("1")); // 1자릿수 배열은 시작하는 첫번째 자리마다 1개씩만 존재하므로 1로 초기화
		countOfIncreasing[1][10] = new BigInteger("10"); // 자릿수가 1개인 오르막수는 10개이므로 초기화
		
		for(int i=2;i<countOfIncreasing.length;i++) {
			BigInteger next = countOfIncreasing[i-1][10];
			BigInteger tempSum = next;
			for(int c=0;c<10;c++) {
				countOfIncreasing[i][c] = next;
				next = next.subtract(countOfIncreasing[i-1][c]);
				tempSum = tempSum.add(next);
			}
			
			countOfIncreasing[i][10] = tempSum;
		}
		
		System.out.print(countOfIncreasing[count][10].remainder(new BigInteger("10007")));
	}

}
