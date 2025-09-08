import java.util.*;
import java.io.*;

public class yoonji {
	static int[][] ground, A;
	static int N, M, K;
	static int[] dr =  {-1, -1, -1, 0, 0, 1, 1, 1}; 
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	static class Tree {
		int r, c, age;

		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}
		
		
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		ground = new int[N+1][N+1];	// 양분 
		A = new int[N+1][N+1];  // 추가 양분 
		
		PriorityQueue<Integer>[][] trees = new PriorityQueue[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j<= N; j++) {
				trees[i][j] = new PriorityQueue<>();
			}
		}
		
		
		// 겨울에 추가될 양분 정보와 초기 양분 설정
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j<= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				ground[i][j] = 5;
			}
			
		}
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			trees[r][c].add(age);
		}
		
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j<= N; j++) {
//				System.out.print(ground[i][j] + " ");
//			}
//			System.out.println();
//		}
//		
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j<= N; j++) {
//				System.out.print(A[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		while (K-- > 0) {
			List<Tree> deadTrees = new ArrayList<>();
			
			// 1. 봄: 모든 땅에서 나무가 양분을 먹거나 죽는다.
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {		
					PriorityQueue<Integer> currentTrees = trees[r][c];
					List<Integer> survivedTrees = new ArrayList<>();
					
					// 봄
					while (!currentTrees.isEmpty()) {
						int age = currentTrees.poll();
						if (ground[r][c] >= age) {
							ground[r][c] -= age;
							survivedTrees.add(age + 1);
						} else {
							// 죽은 나무의 위치(r,c)와 나이(age)를 함께 기록
							deadTrees.add(new Tree(r, c, age));
						}
					}
					
					// 살아남은 나무들을 다시 큐에 넣어줌
					for (int survivedAge : survivedTrees) {
						currentTrees.add(survivedAge);
					}
					
				}
			}
			
			// 2. 여름: 모든 땅의 봄이 끝난 후, 죽은 나무들이 양분으로 변한다.
			for (Tree dead : deadTrees) {
				ground[dead.r][dead.c] += dead.age / 2;
			}
			
			
			
			// 3. 가을: 살아있는 나무들이 번식한다.
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					for (int age : trees[r][c]) {
						if (age % 5 == 0) {
							for (int d = 0; d < 8; d++) {
								int nr = r + dr[d];
								int nc = c + dc[d];
								if (nr >= 1 && nr <= N && nc >= 1 && nc <= N) {
									trees[nr][nc].add(1); // 나이가 1인 나무 추가								
								}
							}
						}
					}
				}
			}
			
			// 4. 겨울: 양분이 추가된다.
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					ground[r][c] += A[r][c];
				}
			}
		}
		
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				cnt += trees[i][j].size();
			}
		}
		System.out.println(cnt);

	}
}
