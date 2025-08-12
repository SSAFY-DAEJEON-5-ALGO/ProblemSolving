import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, d;
    static int[][] board;
    static int[] archer = new int[3];
    static int ans = 0;

    static int[] dx = {0, -1, 0};
    static int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTracking(0, 0);
        System.out.println(ans);

    }

    static int[][] boardCopy() {
        int[][] copied = new int[n][];
        for (int i = 0; i < n; i++) {
            copied[i] = board[i].clone();
        }

        return copied;
    }

    static void backTracking(int idx, int num) {
        if (num == 3) {
            ans = Math.max(ans, game());
            return;
        }

        if (idx >= m) return;

        archer[num] = idx;
        backTracking(idx + 1, num + 1);
        backTracking(idx + 1, num);
    }

    static int game() {
        int cnt = 0;
        int[][] copiedBoard = boardCopy();

        for (int row=n; row>0; row--) {
            List<int[]> remove = new ArrayList<>();
            for (int arc: archer) {
                int[] enemy = getRemoveEnemy(copiedBoard, row, arc);
                if (enemy[0] == -1) continue;

                remove.add(enemy);
            }

            for(int[] r: remove) {
                if (copiedBoard[r[0]][r[1]] == 1) {
                    copiedBoard[r[0]][r[1]] = 0;
                    cnt++;
                }
            }

        }

        return cnt;
    }

    static int[] getRemoveEnemy(int[][] copiedBoard, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] polled = queue.poll();
            int cx = polled[0], cy = polled[1];

            for (int i = 0; i < 3; i++) {
                int nx = cx + dx[i], ny = cy + dy[i];

                if (nx < 0 || nx >= x || ny < 0 || ny >=m) continue;

                if (!visited[nx][ny] && Math.abs(x-nx) + Math.abs(y-ny) <= d) {
                    if (copiedBoard[nx][ny] == 1) {
                        return new int[]{nx, ny};
                    }
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return new int[]{-1, -1};
    }

}
