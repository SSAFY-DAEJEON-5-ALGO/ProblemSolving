import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class yoonji {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1 , 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int maxSum = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visited[i][j] = false;
				
				checkT(i, j);
			}
		}
		System.out.println(maxSum);

	}
	
	private static void checkT(int r, int c) {
		// ㅗ
		if (r > 0 && c > 0 && c < M-1) {
			int sum = map[r][c] + map[r-1][c] + map[r][c-1] + map[r][c+1];
			maxSum = Math.max(sum, maxSum);
		}
		
		// ㅜ
		if (r < N-1 && c > 0 && c < M-1) {
			int sum = map[r][c] + map[r][c-1] + map[r][c+1] + map[r+1][c];
			maxSum = Math.max(sum, maxSum);
		}
		
		// ㅏ
		if (r > 0 && r < N-1 && c < M-1) {
			int sum = map[r][c] + map[r-1][c] + map[r][c+1] + map[r+1][c];
			maxSum = Math.max(sum, maxSum);
		}
		
		// ㅓ
		if (r > 0 && r < N-1 && c > 0) {
			int sum = map[r][c] + map[r-1][c] + map[r][c-1] + map[r+1][c];
			maxSum = Math.max(sum, maxSum);
		}
		
	}

	/**
	 * 
	 * @param r 
	 * @param c
	 * @param depth : 현재까지 연결한 정사각형의 개수
	 * @param sum : 현재까지 방문한 칸의 숫자 합
	 */
	private static void dfs(int r, int c, int depth, int sum) {

		if (depth == 4) {
			maxSum = Math.max(sum, maxSum);
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc, depth+1, sum+map[nr][nc]);
				visited[nr][nc] = false;
				
			}
		}
		
	}

}