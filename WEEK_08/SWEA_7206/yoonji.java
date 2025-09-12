import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class yoonji {
	static int s;
	static Map<Integer, Integer> memo;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			s = Integer.parseInt(br.readLine());
			memo = new HashMap<>();
			System.out.println("#"+tc+" "+numberGame(s));
		}

	}

	private static int numberGame(int n) {
		if (n < 10) {
			return 0;
		}
		
		if (memo.containsKey(n)) {
			return memo.get(n);
		}
		
		String s = String.valueOf(n);
		int len = s.length();
		int maxTurn = 0;
		
		for (int i = 1, size = 1 << len-1; i < size; i++) {
			int product = 1;
			int lastCut = 0; // 마지막으로 잘랐던 위치
			
			/* 비트마스킹
			 * 1234 (쪼갤 위치 3개)가 있다면, 1부터 2³-1=7까지 반복
				i=1 (001): 123|4	
				i=2 (010): 12|34	
				i=3 (011): 12|3|4
			 */
			for (int j = 0; j < len-1; j++) {
				if ((i & (1 << j)) != 0) { // j번째 위치를 자른다면
					product *= Integer.parseInt(s.substring(lastCut, j+1));
					lastCut = j+1;
					
				}
			}
			
			product *= Integer.parseInt(s.substring(lastCut, len));
			
			maxTurn = Math.max(maxTurn, 1+numberGame(product));
		}
		
		memo.put(n, maxTurn);
		return maxTurn;
		
	}

}
