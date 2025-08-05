import java.io.*;
import java.util.*;

public class Main {
	static int N, max = Integer.MIN_VALUE;
	static List<Integer> nums = new ArrayList<>();
	static List<Character> ops = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		String input = br.readLine();
		
		for (int i = 0; i < N; i++) {
			char c = input.charAt(i);
			if (i % 2 == 0) nums.add(c - '0');
			else ops.add(c);
		}
		
		dfs(0, nums.get(0));
		System.out.println(max);

	}
	/**
	 * 
	 * @param idx : nums, ops의 idx
	 * @param cur : 현재까지 계산한 값
	 */
	private static void dfs(int idx, int cur) {
		if (idx >= ops.size()) {
			max = Math.max(max, cur);
			return;
		}
		
		int noBracket = calc(cur, nums.get(idx + 1), ops.get(idx));
		dfs(idx + 1, noBracket);
		
		if (idx + 1 < ops.size()) {
			int bracket = calc(nums.get(idx + 1), nums.get(idx+2), ops.get(idx+1));
			int withBracket = calc(cur, bracket, ops.get(idx));
			dfs(idx + 2, withBracket);
		}
		
	}
	
	private static int calc(int a, int b, char op) {
		if (op == '+') return a + b;
		else if (op == '-') return a - b;
		else return a * b;
	}

}