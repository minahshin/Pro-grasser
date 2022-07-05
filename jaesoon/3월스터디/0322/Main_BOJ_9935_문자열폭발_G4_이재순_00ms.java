package Class4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BOJ_9935_문자열폭발_G4_이재순_00ms {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String bomb = br.readLine();
		int size = line.length();
		int bombSize = bomb.length();
		Letter letter = null;
		Letter temp = null;
		letter = new Letter(line.charAt(0), letter);
		temp = letter;
		for (int i = 1; i < size; i++) {
			temp.next = new Letter(line.charAt(i));
			temp = temp.next;
		}
		Letter start = new Letter(' ', letter);
		for (Letter cur = letter, prev = start; cur != null; cur = cur.next) {
			if (cur.letter == bomb.charAt(0)) {
				if (bombDisposal(bomb, bombSize, prev) != null) {
					cur = prev;
				}
			}
			prev = cur;
		}
		StringBuilder sb = new StringBuilder();
		for (Letter cur = start.next ; cur != null; cur = cur.next) {
			sb.append(cur.letter);
		}
		if (sb.length() == 0) {
			System.out.println("FRULA");
		}
		else System.out.println(sb);
	}
	
	private static Letter bombDisposal(String bomb, int bombSize, Letter prev) {
		Letter cur = prev.next;
		int i = 1;
		while (true) {
			while (cur.next != null && i < bombSize && cur.next.letter == bomb.charAt(i)) {
				i++;
				cur = cur.next;
			}
			if (i == bombSize) {
				prev.next = cur.next;
				return prev.next;
			} else if (cur.next != null && cur.next.letter == bomb.charAt(0)) {
				cur = bombDisposal(bomb, bombSize, cur);
				if (cur != null && cur.letter == bomb.charAt(i)) {
					i++;
					continue;
				}
				return null;
			} else
				return null;
		}
	}
	
	static class Letter {
		char letter;
		Letter next;
		public Letter(char letter) {
			this.letter = letter;
		}
		public Letter(char letter, Letter next) {
			this.letter = letter;
			this.next = next;
		}
	}
}
