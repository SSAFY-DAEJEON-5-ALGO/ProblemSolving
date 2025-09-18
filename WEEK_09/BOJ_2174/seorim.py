import sys

input = sys.stdin.readline

dir_map = {'S': 0, 'E': 1, 'N': 2, 'W': 3}

m, n = map(int, input().split())
num_robot, num_cmd = map(int, input().split())
board = [[0] * m for _ in range(n)]
robots = dict()

for i in range(1, num_robot+1):
    y, x, d = map(str, input().split())
    x, y = int(x)-1, int(y)-1
    board[x][y] = i
    robots[i] = (x, y, dir_map[d])
    
def L(d, itr):
    return (d + itr) % 4

def R(d, itr):
    return (d + 3*itr) % 4

def F_dxdy(d):
    ret = [(-1, 0), (0, 1), (1, 0), (0, -1)]
    return ret[d]

def solve():
    for _ in range(num_cmd):
        robot, cmd, itr = map(str, input().split())
        robot, itr = int(robot), int(itr)
        x, y, d = robots[robot]
        
        if cmd == "F":  # 이동
            dx, dy = F_dxdy(d)
            board[x][y] = 0
            
            for _ in range(itr):
                nx, ny = x + dx, y + dy
                if nx < 0 or nx >= n or ny < 0 or ny >= m:
                    print(f"Robot {robot} crashes into the wall")
                    return
                
                if board[nx][ny] != 0:
                    print(f"Robot {robot} crashes into robot {board[nx][ny]}")
                    return

                x, y = nx, ny
            
            board[x][y] = robot
            robots[robot] = (x, y, d)
        
        elif cmd == "L":  # L 회전
            robots[robot] = (x, y, L(d, itr))
        else:  # R 회전
            robots[robot] = (x, y, R(d, itr))
            
    print("OK")
    return

solve()