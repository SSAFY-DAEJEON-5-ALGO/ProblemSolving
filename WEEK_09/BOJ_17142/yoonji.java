import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, minTime;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static List<Point> virus;
	static Point[] activeVirus;
	static int emptyCnt;
	
	static class Point {
		int r, c, time;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public Point(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		virus = new ArrayList<>();
		emptyCnt = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus.add(new Point(i, j));
				}
				if (map[i][j] == 0) {
					emptyCnt++;
				}
			}
		}
		
		if (emptyCnt == 0) {
			System.out.println(0);
			return;
		}
		
		minTime = Integer.MAX_VALUE;
		activeVirus = new Point[M];
		combination(0, 0);
		
		if (minTime == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(minTime);

	}
	
	// 활성화할 바이러스 M개를 뽑는 조합
	private static void combination(int start, int cnt) {
		if (cnt == M) {
			int time = bfs();
			if (time != -1) {
				minTime = Math.min(minTime, time);
			}
			return;
		}
		
		for (int i = start; i < virus.size(); i++) {
			activeVirus[cnt] = virus.get(i);
			combination(i+1, cnt+1);
		}
		
	}

	private static int bfs() {
		Queue<Point> q = new ArrayDeque<>();
		int[][] dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dist[i][j] = -1;
			}
		}
		
		for (Point p : activeVirus) {
			q.offer(new Point(p.r, p.c, 0));
			dist[p.r][p.c] = 0;
		}
		
		int maxTime = 0;
		int cnt = 0; // 감염 횟수
		
		while (!q.isEmpty()) {
			Point cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				// 범위를 벗어나지 않고 벽(1)이 아니고 거리가 초기화되었으면 아직 바이러스가 전파 X
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != 1 && dist[nr][nc] == -1) {
					dist[nr][nc] = cur.time+1;
					q.offer(new Point(nr, nc, cur.time+1));
					
					// 바이러스가 감염되면
					if (map[nr][nc] == 0) {
						cnt++;
						maxTime = Math.max(maxTime, dist[nr][nc]);
					}
				}
			}
		}
		
		// 모든 빈 칸이 감염되었는지 확인
		if (cnt == emptyCnt) return maxTime;
		else return -1;	
		
	}

}
