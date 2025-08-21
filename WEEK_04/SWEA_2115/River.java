import java.io.*;
import java.util.*;

public class River {
	//일꾼당 M개 가로 방향.. 겹치면 안 됨
	//최대 C
	static int max = -1;
	static int rMax = -1;
	static int N, M, C;
	static int[][] board;
	static ArrayList<int[]> posList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringTokenizer st;
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			board = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++)
					board[i][j] = Integer.parseInt(st.nextToken());
			}

			posList = new ArrayList<>();
			for(int i=0;i<N;i++)
				for(int j=0;j<=N-M;j++) {
					max = -1;
					subset(i,j,0, new boolean[M]);	//각 구간에서 최대
					posList.add(new int[] {i,j,max});
				}
			
			//뽑은 pos 중 안 겹치게 2개 pick...
			int rMax = -1;
			for(int i=0;i<posList.size();i++) {
				for(int j=i+1;j<posList.size();j++)
					if(check(i,j,posList)) {
						rMax = Math.max(rMax, posList.get(i)[2]+posList.get(j)[2]);
					}
			}
			System.out.printf("#%d %d\n",tc, rMax);
		}
	}
	static boolean check(int a, int b, ArrayList<int[]> posList) {
		int[] A = posList.get(a);
		int[] B = posList.get(b);
		
		if(A[0]==B[0]) {
			if(A[1]<B[1] && A[1]+M>B[1]) return false;
			else if(A[1]>B[1] && B[1]+M>A[1]) return false;
		}
		return true;
	}
	static void subset(int row, int start, int idx, boolean[] visited) {
		if(idx==M) {
			int sum = 0;
			ArrayList<Integer> list = new ArrayList<>();
			for(int i=0;i<M;i++) {
				if(visited[i]) {
					sum += board[row][start+i];
					list.add(board[row][start+i]);
				}
			}
			if(sum > C) return;
			int res = cal(list);
			max = Math.max(max, res);
			return;
		}
		
		visited[idx] = true;
		subset(row, start, idx+1, visited);
		visited[idx] = false;
		subset(row, start, idx+1, visited);
	}
	static int cal(ArrayList<Integer> list) {
		int sum = 0;
		for(int i=0;i<list.size();i++) {
			int temp = list.get(i);
			sum += (temp*temp);
		}
		return sum;
	}
}