import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	// 0: 상, 1: 하, 2 : 좌, 3 : 우
	static int[][][] cctv = {
			{},
			{{0}, {1}, {2}, {3}}, // 1번 CCTV는 한 쪽 방향
			{{2, 3}, {0, 1}}, // 2번은 감시하는 방향이 서로 반대방향
			{{0, 2}, {1, 3}, {0, 3}, {1, 2}}, // 3번은 직각 방향
			{{0, 3, 2}, {0, 3, 1}, {3, 1, 2}, {0, 1, 2}}, // 4번은 세 방향
			{{0, 1, 2, 3}} // 5번은 네 방향
	};
	
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	static List<Point> cctvList;
	static int minAns = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		cctvList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5) {
					cctvList.add(new Point(i, j));
				}
			}
		}
		
		dfs(0, map);
		
		System.out.println(minAns);
	}

	private static void dfs(int cctvIdx, int[][] prevMap) {
		if (cctvIdx == cctvList.size()) {
			int current = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (prevMap[i][j] == 0) {
						current++;
					}
				}
			}
			minAns = Math.min(minAns, current);
			return;
		}
		
		Point currentCCTV = cctvList.get(cctvIdx);
		int r = currentCCTV.r;
		int c = currentCCTV.c;
		int type = map[r][c];
		
		for (int[] dirs : cctv[type]) {
			int[][] temp = new int[N][M];
			for (int i = 0; i < N; i++) {
				temp[i] = prevMap[i].clone();
			}
			
			for (int d : dirs) {
				watch(r, c, d, temp);
			}
			
			dfs(cctvIdx+1 , temp);
		}
	}

	private static void watch(int r, int c, int dir, int[][] targetMap) {
		int nr = r;
		int nc = c;
		while (true) {
			nr += dr[dir];
			nc += dc[dir];
			
			// 맵을 벗어나거나 벽(6)이면 종료
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || targetMap[nr][nc] == 6) {
				break;
			}
			
			// 빈 칸인 경우에만 감시 영역 (-1)로 변경
			if (targetMap[nr][nc] == 0) {
				targetMap[nr][nc] = -1;
			}
		}
		
	}
}
