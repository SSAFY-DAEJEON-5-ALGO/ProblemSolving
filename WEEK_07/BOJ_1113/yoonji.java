import java.util.*;
import java.io.*;

public class yoonji {
	static int N, M;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		int maxHeight = 0;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		}
		
		int totalWater = 0;
		for (int h = 1; h <= maxHeight; h++) {
			totalWater += calWaterHeight(h);
		}
		
		System.out.println(totalWater);

	}
	
	// 특정 높이 h에서 고이는 물의 양을 계산하는 함수
	private static int calWaterHeight(int h) {
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				// 가장자리 칸이면서 현재 수위보다 낮으면 물이 빠져나간다.
				if (r == 0 || r == N-1 || c == 0 || c == M-1) {
					if (map[r][c] < h) {
						q.offer(new Point(r, c));
						visited[r][c] = true;
					}
				}
			}
		}
		
		while(!q.isEmpty()) {
			Point current = q.poll();
	
			for (int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] < h) {
					visited[nr][nc] = true;
					q.offer(new Point(nr, nc));
				}
			}
		}
		
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!visited[r][c] && map[r][c] < h) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}