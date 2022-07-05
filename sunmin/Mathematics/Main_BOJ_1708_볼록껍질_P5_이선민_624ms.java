package Mathematics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_1708_볼록껍질_P5_이선민_624ms {
	static class Point {
		long x, y;

		public Point(long x, long y) {
			super();
			this.x = x;
			this.y = y;
		}

	} // end of Point class

	private static Point first;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		List<Point> list = new ArrayList<Point>(N);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		first = new Point(40001, 40001); // 시작점 : x좌표나 y좌표가 가장 작은 점
		for (int i = 0; i < N; i++) {
			if (list.get(i).x < first.x) {
				first = list.get(i);
			} else if (list.get(i).x == first.x) {
				if (list.get(i).y < first.y) {
					first = list.get(i);
				}
			}
		}

		// 시작점을 기준으로 반시계방향으로 점들의 순서 정렬하기
		list.sort(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				int dir = ccw(first, o1, o2);
				if (dir == 1) { // ccw
					return -1;
				} else if (dir == -1) { // cw
					return 1;
				} else { // 일직선상에 있을 경우, 시작점과 더 가까이 있는게 우선 순위 높음
					long dist1 = (first.x - o1.x) * (first.x - o1.x) + (first.y - o1.y) * (first.y - o1.y);
					long dist2 = (first.x - o2.x) * (first.x - o2.x) + (first.y - o2.y) * (first.y - o2.y);
					if (dist1 < dist2) {
						return -1;
					} else {
						return 1;
					}
				}
			}
		});

		// 그라함 스캔 알고리즘
		// 스택에 있는 두 점을 이어서 다음 점이 해당 벡터의 오른쪽(cw)에 있으면 다음점이 벡터의 왼쪽(ccw)에 있을때까지 pop한다.
		Stack<Point> stack = new Stack<Point>();
		stack.push(first);
		for (int i = 1; i < N; i++) {
			// 볼록 껍질의 변에 점이 여러 개 있는 경우에는 가장 양 끝 점만 개수에 포함한다.
			while (stack.size() > 1
					&& ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), list.get(i)) <= 0) {
				stack.pop();
			}
			stack.push(list.get(i));
		}

		System.out.println(stack.size());

	} // end of main

	/**
	 * 세 점을 배열했을때 계산결과가 양수면 반시계방향(ccw), 음수면 시계방향(cw), 0이면 일직선상에 있음
	 */
	protected static int ccw(Point fisrt, Point second, Point third) {
		long dir = (fisrt.x * second.y + second.x * third.y + third.x * fisrt.y)
				- (second.x * fisrt.y + third.x * second.y + fisrt.x * third.y);
		if (dir > 0) { // ccw
			return 1;
		} else if (dir < 0) { // cw
			return -1;
		} else { // 일직선
			return 0;
		}
	} // end of ccw

} // end of class
