import sys
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().split())
maze = []
sx, sy = 0, 0
direction = [(0, -1), (0, 1), (-1, 0), (1, 0)]

for i in range(n):
    line = list(input().rstrip())
    maze.append(line)
    for j in range(m):
        if line[j] == '0':
            sx, sy = i, j
            maze[i][j] = '.'

def add_key(keys, add):
    add = ord(add) - ord('a')
    return keys | (1 << add)

def has_key(keys, target):
    key = ord(str.lower(target)) - ord('a')
    return (keys >> key) & 1 == 1

def bfs():
    global n, m, sx, sy
    
    queue = deque([(sx, sy, 0, 0)])
    visited = [[[False] * (2**6) for _ in range(m)] for _ in range(n)]
    visited[sx][sy][0] = True
    
    while queue:
        x, y, keys, dist = queue.popleft()
        
        for dx, dy in direction:
            nx, ny = x + dx, y + dy
            
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            if visited[nx][ny][keys] or maze[nx][ny] == '#':
                continue
            
            if maze[nx][ny] == '.':
                queue.append((nx, ny, keys, dist + 1))
                visited[nx][ny][keys] = True
            elif 'a' <= maze[nx][ny] <= 'f':
                new_keys = add_key(keys, maze[nx][ny])
                queue.append((nx, ny, new_keys, dist + 1))
                visited[nx][ny][new_keys] = True
            elif 'A' <= maze[nx][ny] <= 'F':
                if has_key(keys, maze[nx][ny]):
                    queue.append((nx, ny, keys, dist + 1))
                    visited[nx][ny][keys] = True
            elif maze[nx][ny] == '1':
                return dist + 1
    
    return -1
                    
print(bfs())