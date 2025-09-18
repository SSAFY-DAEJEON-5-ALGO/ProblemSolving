import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node {
        int r, c, keys, steps;

        Node(int r, int c, int keys, int steps) {
            this.r = r;
            this.c = c;
            this.keys = keys;
            this.steps = steps;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][64]; // 6개의 열쇠 비트마스크 (2^6 = 64)

        int startR = 0, startC = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '0') {
                    startR = i;
                    startC = j;
                    map[i][j] = '.';
                }
            }
        }
        
        System.out.println(bfs(startR, startC));
    }

    static int bfs(int r, int c) {
        Queue<Node> q = new ArrayDeque<>();

        q.offer(new Node(r, c, 0, 0));
        visited[r][c][0] = true;

        while (!q.isEmpty()) {
            Node current = q.poll();

            if (map[current.r][current.c] == '1') {
                return current.steps;
            }

            for (int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == '#') {
                    continue;
                }

                char cell = map[nr][nc];
                int nextKeys = current.keys; 

                // 문을 만난 경우 : 현재 열쇠로 열 수 없으면 통과 불가
                if (cell >= 'A' && cell <= 'F') {
                    if ((current.keys & (1 << (cell - 'A'))) == 0) {
                        continue;
                    }
                } 
                // 열쇠를 만난 경우 : 다음 상태의 열쇠에 추가
                else if (cell >= 'a' && cell <= 'f') {
                    nextKeys |= (1 << (cell - 'a'));
                }

                // 방문하지 않은 상태라면 큐에 추가
                if (!visited[nr][nc][nextKeys]) {
                    visited[nr][nc][nextKeys] = true;
                    q.offer(new Node(nr, nc, nextKeys, current.steps + 1));
                }
            }
        }
        return -1;
    }
}