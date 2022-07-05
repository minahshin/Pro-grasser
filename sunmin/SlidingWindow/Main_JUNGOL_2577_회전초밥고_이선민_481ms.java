package SlidingWindow;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 15961 회전초밥 과 같은문제
 *
 */
public class Main_JUNGOL_2577_회전초밥고_이선민_481ms {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] dishes = new int[N];
		for (int i = 0; i < dishes.length; i++) {
			dishes[i] = Integer.parseInt(br.readLine());
		}

		int[] ate = new int[d + 1];
		int max = 0;
		int temp = 0;
		for (int i = 0; i < k; i++) {
			if (ate[dishes[i]] == 0) {
				temp++;
			}
			ate[dishes[i]]++;
		}
		if (++ate[c] == 1) {
			temp++;
		}
		max = temp;
		
		// 슬라이딩 윈도우
		for (int i = 0; i < dishes.length; i++) {
			if (--ate[dishes[i]] == 0) {
				temp--;
			}

			if (++ate[dishes[(i + k) % N]] == 1) {
				temp++;
			}

			if(++ate[c]==1) {
				temp++;
			}
			
			max = temp > max ? temp : max;
		}

		System.out.println(max);
	}

}
