import java.io.*;
import java.util.*;

public class River {
	static int N, M, R, C, L;
	static int[][] board;
	static ArrayList<int[]>[] pipes = new ArrayList[8];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringTokenizer st;
		
		for(int i=1;i<8;i++)
			pipes[i] = new ArrayList<>();
		
		pipes[1].add(new int[] {0,-1});
		pipes[1].add(new int[] {-1,0});
		pipes[1].add(new int[] {0,1});
		pipes[1].add(new int[] {1,0});

		pipes[2].add(new int[] {-1,0});
		pipes[2].add(new int[] {1,0});

		pipes[3].add(new int[] {0,-1});
		pipes[3].add(new int[] {0,1});

		pipes[4].add(new int[] {-1,0});
		pipes[4].add(new int[] {0,1});

		pipes[5].add(new int[] {1,0});
		pipes[5].add(new int[] {0,1});

		pipes[6].add(new int[] {0,-1});
		pipes[6].add(new int[] {1,0});
		

		pipes[7].add(new int[] {0,-1});
		pipes[7].add(new int[] {-1,0});
		
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			board = new int[N][M];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++)
					board[i][j] = Integer.parseInt(st.nextToken());
			}

			visited = new boolean[N][M];
			v = new boolean[N][M];
			cnt = 0;
			v[R][C] = true;
			dfs(R,C,1);
			
			sb.append("#"+tc+" "+cnt+"\n");
			
			//System.out.printf("#%d %d\n",tc, cnt);
		}
		System.out.println(sb);
	}
	static boolean[][] v;
	static boolean[][] visited;
	static int cnt = 0;
	static void dfs(int x, int y, int h) {
		if(h==L) {
			if(!visited[x][y]) {
				visited[x][y] = true;
				cnt++;
			}
			return;
		}
		
		dfs(x, y, h+1);
		
		ArrayList<int[]> posList = find(x, y);
		for(int[] next : posList) {
			if(v[next[0]][next[1]]) continue;
			v[next[0]][next[1]] = true;
			dfs(next[0], next[1], h+1);
			v[next[0]][next[1]] = false;
		}
	}
	static ArrayList<int[]> find(int x, int y){
		ArrayList<int[]> list = new ArrayList<>();
		
		int num = board[x][y];
		
		for(int[] next : pipes[num]) {
			int nx = x+next[0];
			int ny = y+next[1];
			if(checkRange(nx, ny) && checkPipe(x, y, nx, ny)) {
				list.add(new int[] {nx, ny});
			}
		}
		
		return list;
	}
	static boolean checkPipe(int px, int py, int cx, int cy) {
		int pre = board[px][py];
		int curr = board[cx][cy];
		
		if(curr==0) return false;
		if(curr==1) return true;

		//아래랑 연결(1,2,4,7) -> 1,2,5,6
		//위랑 연결(1,2,5,6) -> 1,2,4,7
		//왼쪽 연결(1,3,4,5) -> 1,3,6,7
		//오른쪽 연결(1,3,6,7) -> 1,3,4,5
		
		if(pre==1 || pre==2 || pre==4 || pre==7)
			if(px-1==cx && (curr == 2 || curr==5 || curr==6)) return true;
		
		if(pre==1 || pre==2 || pre==5 || pre==6)
			if(px+1==cx && (curr == 2 || curr==4 || curr==7)) return true;
		
		if(pre==1 || pre==3 || pre==4 || pre==5)
			if(py+1==cy && (curr == 3 || curr==6 || curr==7)) return true;
		
		if(pre==1 || pre==3 || pre==6 || pre==7)
			if(py-1==cy && (curr == 3 || curr==4 || curr==5)) return true;
		
		return false;
	}
	static boolean checkRange(int x, int y) {
		if(x>=0 && x<N && y>=0 && y<M) return true;
		
		return false;
	}
}