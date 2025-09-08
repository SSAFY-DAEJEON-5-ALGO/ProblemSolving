import java.util.*;
import java.io.*;

public class yoonji {
	static int[][] results = new int[6][3];
	static int[][] matches = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, 
								{1, 2}, {1, 3}, {1, 4}, {1, 5},
								{2, 3}, {2, 4}, {2, 5},
								{3, 4}, {3, 5},
								{4, 5}
								};
	static boolean isPossible;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			boolean check = true;
			
			int totalSum = 0;
			for (int country = 0; country < 6; country++) {
				int win = Integer.parseInt(st.nextToken());
				int draw = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());
				
				results[country][0] = win;
				results[country][1] = draw;
				results[country][2] = lose;
				
				totalSum += (win + draw + lose);
				
				// 각 나라의 경기 수 합이 5가 아니면
				if (win + draw + lose != 5) {
					check = false;
				}
			}
			
			if (!check) {
				System.out.print("0 ");
				continue;
			}

			isPossible = false;
			dfs(0);
			
			if (isPossible) System.out.print("1 ");
			else System.out.print("0 ");
		
		}

	}

	private static void dfs(int idx) {
		if (isPossible) return;
		
		if (idx == 15) {
			isPossible = true;
			return;
		}
		
		int teamA = matches[idx][0];
		int teamB = matches[idx][1];
		
		// 경우 1: A 승, B 패
		if (results[teamA][0] > 0 && results[teamB][2] > 0) {
			results[teamA][0]--;
			results[teamB][2]--;
			dfs(idx+1);
			results[teamA][0]++;
			results[teamB][2]++;
		}
		
		// 경우 2: A 무, B 무
		if (results[teamA][1] > 0 && results[teamB][1] > 0) {
			results[teamA][1]--;
			results[teamB][1]--;
			dfs(idx+1);
			results[teamA][1]++;
			results[teamB][1]++;
		}
		
		// 경우 3: A 패, B 승
		if (results[teamA][2] > 0 && results[teamB][0] > 0) {
			results[teamA][2]--;
			results[teamB][0]--;
			dfs(idx+1);
			results[teamA][2]++;
			results[teamB][0]++;
		}
	}

}