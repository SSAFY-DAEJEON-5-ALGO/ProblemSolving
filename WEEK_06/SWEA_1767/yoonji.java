import java.util.*;
import java.io.*;
 
public class Solution {
    static int N;
    static int[][] arr;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<int[]> cores; // 탐색할 코어 목록
    static int maxCore; // 최대 연결 코어 수
    static int minWire; // 최소 전선 길이
     
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            cores = new ArrayList<>();
             
             
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                     
                    // 가장자리가 아닌 코어만 리스트에 추가
                    if (arr[i][j] == 1) {
                        if (i > 0 && i < N - 1 && j > 0 && j < N - 1) {
                            cores.add(new int[] {i, j});
                        }
                    }
                }
            }
             
            maxCore = 0;
            minWire = Integer.MAX_VALUE;
             
            dfs(0, 0, 0);
             
            System.out.println("#" + tc + " " + minWire);
        }
 
    }
     
    // idx: 현재 탐색할 코어의 인덱스, curCore: 현재까지 연결된 코어 수, curWireLen: 현재까지의 전선 길이
    static void dfs(int idx, int curCore, int curWireLen) {
        if (idx == cores.size()) {
            if (curCore > maxCore) {
                maxCore = curCore;
                minWire = curWireLen;
            }
            else if (curCore == maxCore) {
                minWire = Math.min(minWire, curWireLen);
            }
            return;
        }
         
        int r = cores.get(idx)[0];
        int c = cores.get(idx)[1];
         
        // 경우 1: 4방향으로 전선 연결 시도
        for (int d = 0; d < 4; d++) {
            if (isAvailable(r, c, d)) { // 해당 방향으로 전선 놓기가 가능하다면
                int len = setWire(r, c, d, 2); // 0: 빈칸  1: 코어  2: 전선
                dfs(idx + 1, curCore + 1, curWireLen + len);
                setWire(r, c, d, 0); // 원상 복구
            }
        }
          
        // 경우 2: 현재 코어를 연결하지 않음
        dfs(idx + 1, curCore, curWireLen);
    }
 
     
    // (r, c)에서 d방향에 전선을 놓을 수 있는지 확인
    private static boolean isAvailable(int r, int c, int d) {
        int nr = r, nc = c;
        while (true) {
            nr += dr[d];
            nc += dc[d];
             
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                break;
            }
            if (arr[nr][nc] != 0) { // 다른 코어나 전선을 만나면 전선을 놓을 수 없음.
                return false;
            }
        }
        return true;
    }
     
    // (r, c)에서 d방향으로 전선을 놓거나 2 치우고 0 그 길이를 반환
    private static int setWire(int r, int c, int d, int val) {
        int len = 0;
        int nr = r, nc = c;
        while (true) {
            nr += dr[d];
            nc += dc[d];
             
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                break;
            }
            arr[nr][nc] = val;
            len++;
        }
        return len;
    }
 
}
