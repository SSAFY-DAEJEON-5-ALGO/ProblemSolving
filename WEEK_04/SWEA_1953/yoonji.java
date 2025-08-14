package algorithm;

import java.util.*;

class place {
	int r, c, time;
	void reset(int _r, int _c, int _time) {
		r =_r;
		c = _c;
		time =_time;
	}
};

public class Solution {
	static int MAX_SIZE = 52;
	static int N, M, R, C, L;
	
	static int dir[][] = {
			{0, 1}, 
			{1, 0},
			{0, -1},
			{-1, 0}
	};
	
	static int open[][] = {
			{0, 0, 0, 0},
			{1, 1, 1, 1},
			{0, 1, 0, 1}, 
			{1, 0, 1, 0},
			{1, 0, 0, 1},
			{1, 1, 0, 0}, 
			{0, 1, 1, 0},
			{0, 0, 1, 1}
	};
	
	static int board[][][] = new int[MAX_SIZE][MAX_SIZE][4];
	
	static int visited[][] = new int[MAX_SIZE][MAX_SIZE];
	
	static place queue[] = new place[MAX_SIZE*MAX_SIZE];
	static int head, tail;
	
	static int count;
	
	static int in_range(int r, int c) {
		if (r >= 0 && r < N && c >=0 && c < M) return 1;
		return 0;
	}
	
	static void init() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = 0;
			}
		}
		
		head = tail = 0;
	}
	
	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int T;
			T=sc.nextInt();
			
			for (int i = 0; i < MAX_SIZE * MAX_SIZE; i++) {
				queue[i] = new place();
			}
		
			for(int test_case = 1; test_case <= T; test_case++)
			{
		
				N = sc.nextInt();
				M = sc.nextInt();
				R = sc.nextInt();
				C = sc.nextInt();
				L = sc.nextInt();
				
				init();
				int type;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						type = sc.nextInt();
						for (int d = 0; d < 4; d++) {
							board[i][j][d] = open[type][d];
						}
		
					}
				}
				
				count = 1;
				visited[R][C] = 1;
				queue[tail++].reset(R, C, 1);
				
				place p;
				int nr, nc, nt, opp_d;
				
				while (head != tail) {
					p = queue[head++];
					for (int d = 0; d < 4; d++) {
						nr = p.r + dir[d][0];
						nc = p.c + dir[d][1];
						nt = p.time + 1;
						
						opp_d = (d + 2) % 4;
						
						if ((in_range(nr, nc) != 0) && (visited[nr][nc] == 0) && (nt <= L) && (board[p.r][p.c][d] == 1) && (board[nr][nc][opp_d] == 1)) {
							count++;
							visited[nr][nc] = 1;
							queue[tail++].reset(nr, nc, nt);
						}
					}
				}
				
				
				System.out.println("#" + test_case + " " + count);
		
			}
		}		

}