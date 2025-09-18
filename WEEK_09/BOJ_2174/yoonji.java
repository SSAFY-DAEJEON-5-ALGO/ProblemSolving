import java.io.*;
import java.util.*;

public class Main {
    static int A, B, N, M;
    static int[][] map;
    static Robot[] robots;

    // 0:북(N), 1:동(E), 2:남(S), 3:서(W)
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Robot {
        int r, c, dir;
        Robot(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[B][A];
        robots = new Robot[N+1];

        Map<Character, Integer> dirMap = new HashMap<>();
        dirMap.put('N', 0);
        dirMap.put('E', 1);
        dirMap.put('S', 2);
        dirMap.put('W', 3);

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);
            
            int r = B-y;
            int c = x-1;
            robots[i] = new Robot(r, c, dirMap.get(d));
            map[r][c] = i; // 로봇 번호로 맵에 표시
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int robotNum = Integer.parseInt(st.nextToken());
            char command = st.nextToken().charAt(0);
            int repeat = Integer.parseInt(st.nextToken());

            Robot robot = robots[robotNum];

            for (int j = 0; j < repeat; j++) {
                if (command == 'L') {
                    robot.dir = (robot.dir + 3) % 4; // 왼쪽으로 90도 회전
                } else if (command == 'R') {
                    robot.dir = (robot.dir + 1) % 4; // 오른쪽으로 90도 회전
                } else if (command == 'F') {
                    int nr = robot.r + dr[robot.dir];
                    int nc = robot.c + dc[robot.dir];

                    // 벽에 부딪히는 경우
                    if (nr < 0 || nr >= B || nc < 0 || nc >= A) {
                        System.out.println("Robot " + robotNum + " crashes into the wall");
                        return;
                    }

                    // 다른 로봇에 부딪히는 경우
                    if (map[nr][nc] != 0) {
                        System.out.println("Robot " + robotNum + " crashes into robot " + map[nr][nc]);
                        return;
                    }

                    // 이동
                    map[robot.r][robot.c] = 0; // 이전 위치 비우기
                    robot.r = nr;
                    robot.c = nc;
                    map[nr][nc] = robotNum; // 새로운 위치에 로봇 번호 표시
                }
            }
        }    
        System.out.println("OK");
    }

}