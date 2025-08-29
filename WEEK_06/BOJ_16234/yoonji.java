import java.io.*;
import java.util.*;

public class Main {
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1 , 1};
	
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
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int days = 0;
		while (true) {
			visited = new boolean[N][N];
			boolean moved = false; // 인구 이동 여부 파악
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c]) {
						List<Point> union = new ArrayList<>();
						union.add(new Point(r, c));
						int sum = bfs(r, c, union);
						
						if (union.size() > 1) {
							int newPopulation = sum / union.size();
							for (Point country : union) {
								map[country.r][country.c] = newPopulation;
							}
							moved = true;
						}
					}
				}
			}
			
			// 인구 이동이 없었다면 종료
			if (!moved) break;
			days++;
		}
		System.out.println(days);
		

	}
	
	// (r, c)에서 시작하여 하나의 연합을 찾는다.
	private static int bfs(int r, int c, List<Point> union) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(r, c));
		visited[r][c] = true;
		int sum = map[r][c];
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					int diff = Math.abs(map[current.r][current.c] - map[nr][nc]);
					
					if (diff >= L && diff <= R) {
						visited[nr][nc] = true;
						q.offer(new Point(nr, nc));
						union.add(new Point(nr, nc));
						sum += map[nr][nc];
					}
				}
			}
		}
		
		return sum;
	}

}
