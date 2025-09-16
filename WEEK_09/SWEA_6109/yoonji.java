import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.ArrayDeque;

public class Solution {
	static int N;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if (dir.equals("up")) moveUp();
			else if (dir.equals("down")) moveDown();
			else if (dir.equals("left")) moveLeft(); 
			else if (dir.equals("right")) moveRight();
			
			sb.append("#"+tc+"\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(arr[i][j] + " ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
		

	private static void moveUp() {
		for (int j = 0; j < N; j++) {
			Queue<Integer> q = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				if (arr[i][j] != 0) q.offer(arr[i][j]);
				arr[i][j]=0;
			}
			int idx = 0;
			while (!q.isEmpty()) {
				int cur = q.poll();
				if (arr[idx][j]==0) { // 위가 비어 있으면
					arr[idx][j]=cur;
				} else if (arr[idx][j]==cur) { // 이동할 위치에 같은 숫자가 존재한다면 합치기
					arr[idx][j]*=2;
					idx++;
				} else {
					idx++;
					arr[idx][j]=cur;
				}
			}
		}
		
	}

	private static void moveDown() {
		for (int j = 0; j < N; j++) {
			Queue<Integer> q = new ArrayDeque<>();
			for (int i = N-1; i >=0; i--) {
				if (arr[i][j] != 0) q.offer(arr[i][j]);
				arr[i][j]=0;
			}
			int idx = N-1;
			while (!q.isEmpty()) {
				int cur = q.poll();
				if (arr[idx][j]==0) { // 아래가 비어 있으면
					arr[idx][j]=cur;
				} else if (arr[idx][j]==cur) { // 이동할 위치에 같은 숫자가 존재한다면 합치기
					arr[idx][j]*=2;
					idx--;
				} else {
					idx--;
					arr[idx][j]=cur;
				}
			}
		}
		
	}

	private static void moveLeft() {
		for (int i = 0; i < N; i++) {
			Queue<Integer> q = new ArrayDeque<>();
			for (int j = 0; j < N; j++) {
				if (arr[i][j]!=0) q.offer(arr[i][j]);
				arr[i][j]=0;
			}
			int idx = 0;
			while(!q.isEmpty()) {
				int cur = q.poll();
				if (arr[i][idx]==0) {
					arr[i][idx]=cur;
				} else if (arr[i][idx]==cur) {
					arr[i][idx] *= 2;
					idx++;
				} else {
					idx++;
					arr[i][idx]=cur;
				}
			}
		}
		
	}
	
	
	private static void moveRight() {
		for (int i = 0; i < N; i++) {
			Queue<Integer> q = new ArrayDeque<>();
			for (int j = N-1; j >= 0; j--) {
				if (arr[i][j]!=0) q.offer(arr[i][j]);
				arr[i][j]=0;
			}
			int idx = N-1;
			while (!q.isEmpty()) {
				int cur = q.poll();
				if (arr[i][idx]==0) {
					arr[i][idx]=cur;
				} else if (arr[i][idx]==cur) {
					arr[i][idx]*=2;
					idx--;
				} else {
					idx--;
					arr[i][idx]=cur;
				}
			}
		}
		
	}

}