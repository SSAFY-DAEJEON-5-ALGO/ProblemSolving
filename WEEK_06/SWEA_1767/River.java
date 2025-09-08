import java.io.*;
import java.util.*;
public class River {
	static int N;
	static ArrayList<Core> cores;
	static int min;
	static int maxCnt;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine().trim());
			cores = new ArrayList<>();
			min = Integer.MAX_VALUE;
			maxCnt = 0;
			visited = new boolean[N][N];

			StringTokenizer st;
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					int temp = Integer.parseInt(st.nextToken());
					if(temp==1) {
						visited[i][j]=true;
						if(i!=0 && i!=N-1 && j!=0 && j!=N-1)
							cores.add(new Core(i,j));
					}
				}
			}

			backt(0,0,0);
			
			System.out.printf("#%d %d\n",tc,min);
		}

	}
	static void backt(int idx, int curr, int cnt) {
		if(idx==cores.size()) {
			if(maxCnt < cnt) {
				maxCnt = cnt;
				min = curr;
			}else if(maxCnt==cnt)
				min = Math.min(min, curr);
			return;
		}

		Core core = cores.get(idx);
		for(int d=0;d<4;d++) {
			int len = check(core.x, core.y, d);
			if(len==-1) {
				backt(idx+1, curr, cnt);
			}else {
				toggle(core.x, core.y, d);
				backt(idx+1, curr+len, cnt+1);
				toggle(core.x, core.y, d);				
			}
		}
	}
	static void toggle(int x, int y, int d) {
		int nx = x+dx[d];
		int ny = y+dy[d];
		while(true) {
			if(nx<0 || nx>=N || ny<0 || ny>=N) break;
			visited[nx][ny] = !visited[nx][ny];
			nx += dx[d];
			ny += dy[d];
		}
	}
	static int check(int x, int y, int d) {
		int nx = x+dx[d];
		int ny = y+dy[d];
		
		int cnt = 0;
		while(true) {
			if(nx<0 || nx>=N || ny<0 || ny>=N) break;
			if(visited[nx][ny]) return -1;
			cnt++;
			nx += dx[d];
			ny += dy[d];
		}
		
		return cnt;
	}
	static class Core{
		int x;
		int y;

		Core(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
