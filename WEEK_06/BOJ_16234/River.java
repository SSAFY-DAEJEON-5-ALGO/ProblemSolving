import java.util.*;
import java.io.*;

public class River {
	static int N, L, R;
	static int[][] board;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		initialize(br);
		
		int day = 0;
		while(true) {
			visited = new boolean[N][N];
			boolean isMoved = false;
			for(int i=0;i<N;i++)
				for(int j=0;j<N;j++)
					if(!visited[i][j]) {
						visited[i][j] = true;
						if(sol(i,j)) isMoved = true;
					}
			
			if(!isMoved) break;
			day++;
		}
		System.out.println(day);
	}
	static boolean sol(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		
		visited[x][y] = true;
		
		int total = board[x][y];
		int cnt = 1;
		ArrayList<int[]> picked = new ArrayList<>();
		picked.add(new int[] {x, y});
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			
			int cx = curr[0];
			int cy = curr[1];

			for(int d=0;d<4;d++) {
				int nx = cx+dx[d];
				int ny = cy+dy[d];
				
				if(!checkNext(nx, ny) || visited[nx][ny]) continue;
				if(isUnion(cx,cy,nx,ny)) {
					picked.add(new int[] {nx, ny});
					total += board[nx][ny];
					cnt++;
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
			}
		}
		
		if(cnt==1) return false;
		
		int update = total/cnt;
		
		for(int[] pos : picked) {
			board[pos[0]][pos[1]] = update;
		}
		return true;
	}
	static boolean isUnion(int r, int c, int x, int y) {
		int diff = Math.abs(board[r][c]-board[x][y]);
		return diff>=L && diff<=R;
	}
	static boolean checkNext(int x, int y) {
		if(x<0 || x>=N || y<0 || y>=N) return false;
		return true;
	}
	private static void initialize(BufferedReader br) throws IOException {
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}
	}
}
