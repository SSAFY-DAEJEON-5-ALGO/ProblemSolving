import java.io.*;
import java.util.*;

public class Main {
	static int R, C, N;
    // 제자리, 상, 하, 좌, 우
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, -1, 1};
	static char[][] map;
	static char[][] bomb;
	
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
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		Queue<Point> q = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'O') {
					q.offer(new Point(i, j));
				}
			}
		}
		
		if (N == 1) {
			print(map);
		}
		else if (N % 2 == 0) {
			char[][] bombMap = createFullBomb();
			print(bombMap);
		}
		else { // N이 3 이상인 홀수
			char[][] map3Sec = createFullBomb();
			bomb(map3Sec, q);
			
			if (N % 4 == 3) {
				print(map3Sec);
			}
			else if (N % 4 == 1) {
				Queue<Point> bomb3Sec = findBomb(map3Sec);
				char[][] map5Sec = createFullBomb();
				bomb(map5Sec, bomb3Sec);
				print(map5Sec);
			}
		}
	

	}

	private static Queue<Point> findBomb(char[][] map) {
		Queue<Point> q = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'O') {
					q.offer(new Point(i, j));
				}
			}
		}
		return q;
	}

	private static void bomb(char[][] map, Queue<Point> q) {
		while (!q.isEmpty()) {
			Point current = q.poll();
			for (int d = 0; d < 5; d++) { // 현재 폭탄 위치와 상하좌우
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
					map[nr][nc] = '.';
				}
			}
		}
		
	}

	private static char[][] createFullBomb() {
		char[][] newMap = new char[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				newMap[i][j] = 'O';
			}
		}
		return newMap;
	}

	private static void print(char[][] map) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
