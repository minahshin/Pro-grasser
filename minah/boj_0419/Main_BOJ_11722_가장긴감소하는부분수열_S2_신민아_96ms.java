package boj_0419;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11722_가장긴감소하는부분수열_S2_신민아_96ms {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		
		int[] numbers = new int[count];
		int[] lis = new int[count];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0;i<count;i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		for(int i=0;i<count;i++) {
			lis[i] = 1;
			for(int j=0;j<i;j++) {
				int temp = lis[j] + 1;
				if(numbers[j] > numbers[i] && lis[i] < temp) {
					lis[i] = temp;
				}
			}
			max = Math.max(max, lis[i]);
		}
		
		System.out.print(max);
	}

}