import java.util.*;
import java.io.*;

public class Main {
	private static char[][] map;
	private static int R;
	private static int C;
	// 상 하 좌 우
	private static int dr[] = {-1, 1, 0, 0};
	private static int dc[] = {0, 0, -1, 1};
	private static Map<Character, Integer> idx; // 블록과 pipes의 인덱스를 연결하기 위한 map
	
	static boolean[][] pipes = { 
			{true, true, false, false}, 
			{false, false, true, true}, 
			{true, true, true, true},
			{false, true, false, true}, 
			{true, false, false, true}, 
			{true, false, true, false},
			{false, true, true, false} 
			};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		initIdx();

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == '.') { // . 인 경우
					boolean[] ndir = new boolean[4];
					int cnt = 0;
					for (int d = 0; d < 4; d++) { // 4방향 탐색
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (nr > -1 && nc > -1 && nr < R && nc < C && map[nr][nc] != '.' && map[nr][nc] != 'Z'
								&& map[nr][nc] != 'M' && pipes[idx.get(map[nr][nc])][getOpp(d)]) { // 해당 방향의 블록과 연결 시
							cnt++;
							ndir[d] = true;
						}
					}
					if (cnt == 4) { // 4방향 다 연결이면 +블록
						System.out.println((r + 1) + " " + (c + 1) + " +");
						return;
					} else if (cnt == 2) { // 2개 연결 시 
						printResult(r + 1, c + 1, ndir);
						return;
					}
				}
			}
		}
	}

	private static void printResult(int r, int c, boolean[] ndir) {
		// pipes과 인접한 방향(ndir)를 비교
		for (int i = 0; i < pipes.length; i++) {
			if (i == 2)
				continue;
			boolean flag = true;
			for (int j = 0; j < 4; j++) {
				if (pipes[i][j] != ndir[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println(r + " " + c + " " + origin(i));
			}
		}

	}

	private static char origin(int i) {
		// map을 다시 블록으로 변환
		switch (i) {
		case 0:
			return '|';
		case 1:
			return '-';
		case 2:
			return '+';
		case 3:
			return '1';
		case 4:
			return '2';
		case 5:
			return '3';
		case 6:
			return '4';
		}
		return 0;
	}

	private static int getOpp(int d) { // 반대방향
		switch (d) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		case 3:
			return 2;
		}
		return d;
	}

	private static void initIdx() {
		idx = new HashMap<>();
		idx.put('|', 0);
		idx.put('-', 1);
		idx.put('+', 2);
		idx.put('1', 3);
		idx.put('2', 4);
		idx.put('3', 5);
		idx.put('4', 6);
	}
}
