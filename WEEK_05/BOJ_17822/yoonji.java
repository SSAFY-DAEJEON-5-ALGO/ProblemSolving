import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] plates;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		plates = new int[N+1][M];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				plates[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			rotate(x, d, k);
			adjacent();
		}
		
		int totalSum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				totalSum += plates[i][j];
			}
		}
		System.out.println(totalSum);

	}


	private static void rotate(int x, int d, int k) {
		// x의 배수인 원판 모두 회전
		for (int i = x; i <= N; i+=x) {
			int[] temp = new int[M]; // 임시배열을 이용해서 회전하자
			for (int j = 0; j < M; j++) {
				if (d==0) {
					temp[(j + k) % M] = plates[i][j];
				} else {
					temp[(j - k % M + M) % M] = plates[i][j];
				}
			}
			// 회전이 끝난 후 임시배열 덮어쓰기
			plates[i] = temp;
			
		}
		
	}
	
	private static void adjacent() {
		boolean[][] toRemove = new boolean[N + 1][M];
		boolean foundMatch = false;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (plates[i][j] == 0) continue;
				
				int right = (j + 1) % M;
				if (plates[i][j] == plates[i][right]) {
					toRemove[i][j] = true;
					toRemove[i][right] = true;
					foundMatch = true;
				}
				
				if (i < N && plates[i][j] == plates[i + 1][j]) {
					toRemove[i][j] = true;
					toRemove[i + 1][j] = true;
					foundMatch = true;
				}
			}
		}
		
		if (foundMatch) {
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if (toRemove[i][j]) {
						plates[i][j] = 0;
					}
				}
			}
		} else {
			int sum = 0;
			int count = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if (plates[i][j] != 0) {
						sum += plates[i][j];
						count++;
					}
				}
			}
			
			if (count > 0) {
				double average = (double) sum / count;
				for (int i = 1; i <= N; i++) {
					for (int j = 0; j < M; j++) {
						if (plates[i][j] != 0) {
							if (plates[i][j] > average) plates[i][j]--;
							else if (plates[i][j] < average) plates[i][j]++;
						}
					}
				}
			}
			
		}
		
	}

}