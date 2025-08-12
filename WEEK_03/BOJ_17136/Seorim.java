import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n = 10;
    static int[][] board = new int[n][n];
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static int ans = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTracking(0, 0);

        if (ans == 100) System.out.println(-1);
        else System.out.println(ans);
    }

    static void backTracking(int pos, int cnt) {
        while (pos < 100) {
            int x = pos / n, y = pos % n;
            if (board[x][y] == 1) {
                for (int p=5; p>=1; p--) {
                    if (paper[p] >= 1 && check(x, y, p)) {
                        paper[p]--;
                        toggle(x, y, p, 0);
                        backTracking(pos+p, cnt+1);
                        toggle(x, y, p, 1);
                        paper[p]++;
                    }
                }
                return;
            }
            pos++;
        }

        ans = Math.min(ans, cnt);
    }

    static boolean check(int x, int y, int size) {
        if (x + size > n || y + size > n) {
            return false;
        }

        for (int i=x; i<x+size; i++) {
            for (int j=y; j<y+size; j++) {
                if (board[i][j] == 0) return false;
            }
        }

        return true;
    }

    static void toggle(int x, int y, int size, int to) {
        for (int i=x; i<x+size; i++) {
            for (int j=y; j<y+size; j++) {
                board[i][j] = to;
            }
        }
    }
}
