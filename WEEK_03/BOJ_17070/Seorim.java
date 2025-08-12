import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Seorim {

    static int n;
    static int[][] map;
    static int ans = 0;
    static int[][][] dp;

    // 가로 0, 세로 1, 대각선 2
    static int[][] next = {
            {0, 2},  // 가로에서 갈 수 있는
            {1, 2},  // 세로에서 갈 수 있는
            {0, 1, 2}  // 대각선에서 갈 수 있는
    };
    static int[][] dir = {
            {0, 1},  // 가로
            {1, 0},  // 세로
            {1, 1}  // 대각선
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][n][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        int result = backTracking(0, 1, 0);
        System.out.println(result);
    }

    static int backTracking(int x, int y, int d) {
        if (x == n-1 && y == n-1) {
            return dp[x][y][d] = 1;
        }

        if (dp[x][y][d] != -1) {
            return dp[x][y][d];
        }

        int cnt = 0;
        for (int nd : next[d]) {
            int nx = x + dir[nd][0];
            int ny = y + dir[nd][1];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                continue;
            }

            if (map[nx][ny] == 1
                    || (nd == 2 && (map[nx][ny] == 1 || map[nx-1][ny] == 1 || map[nx][ny-1] == 1))) {
                continue;
            }

            cnt += backTracking(nx, ny, nd);
        }

        return dp[x][y][d] = cnt;
    }

}