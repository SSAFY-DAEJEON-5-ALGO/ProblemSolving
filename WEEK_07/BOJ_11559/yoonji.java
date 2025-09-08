import java.io.*;
import java.util.*;

public class Main {
	static char[][] map;
	static int[] dr = {-1 ,1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] visited;
	
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
		
		map = new char[12][6];
		for (int i = 0; i < 12; i++) {
			String line = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		int cnt = 0;
		while (true) {
			boolean isPop = false;
			
			visited= new boolean[12][6];
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.' && !visited[i][j]) {
						List<Point> puyo = findPuyo(i, j);
						
						// 뿌요가 4개 이상이면
						if (puyo.size() >= 4) {
							isPop = true;
							for (Point p : puyo) {
								map[p.r][p.c] = '.';
							}
						}
					}
				}
			}
			
			if (!isPop) break;
			
			applyGravity();
			cnt++;
		}
		System.out.println(cnt);

	}
	
	// (r, c)에서 시작하여 연결된 같은 색의 뿌요를 찾는다.
	private static List<Point> findPuyo(int r, int c) {
		Queue<Point> q = new ArrayDeque<>();
		List<Point> puyo = new ArrayList<>();
		
		q.offer(new Point(r, c));
		puyo.add(new Point(r, c));
		visited[r][c] = true;
		char color = map[r][c];
		
		while (!q.isEmpty()) {
			Point current = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = current.r + dr[d];
				int nc = current.c + dc[d];
				
				if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6 && !visited[nr][nc] && map[nr][nc] == color) {
					visited[nr][nc] = true;
					q.offer(new Point(nr, nc));
					puyo.add(new Point(nr, nc));
				}
			}
		}
		
		return puyo;
	}
	
	// 중력 작용 -> 뿌요를 아래로 내리기
	private static void applyGravity() {
		for (int c = 0; c < 6; c++) { 
			Queue<Character> remainPuyo = new ArrayDeque<>();
			// 열을 아래부터 위로 훑으며 남아있는 뿌요를 큐에 담는다.
			for (int r = 11; r >= 0; r--) {
				if (map[r][c] != '.') {
					remainPuyo.offer(map[r][c]);
				}
			}
			
			// 해당 열을 모두 빈칸으로 초기화
			for (int r = 0; r < 12; r++) {
				map[r][c] = '.';
			}
			
			// 큐에 담아둔 뿌요를 열의 맨 아래부터 다시 채운다.
			int r = 11;
			while (!remainPuyo.isEmpty()) {
				map[r--][c] = remainPuyo.poll();
			}
		}
		
	}
}
