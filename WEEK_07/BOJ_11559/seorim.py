import sys
from collections import deque

input = sys.stdin.readline

color_map = {'.': 0, 'R': 1, 'G': 2, 'B': 3, 'P': 4, 'Y': 5}
direction = [(-1, 0), (1, 0), (0, -1), (0, 1)]
n, m = 12, 6
board = [[] for _ in range(n)]

for i in range(n):
    line = list(input().rstrip())
    for c in line:
        board[i].append(color_map[c])

def play(i, j, visited):
    queue = deque([(i, j)])
    visited[i][j] = True
    group = [(i, j)]
    color = board[i][j]
    
    flag = False
    while queue:
        x, y = queue.popleft()
        
        for dx, dy in direction:
            nx, ny = x + dx, y + dy
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
                
            if not visited[nx][ny] and board[nx][ny] == color:
                visited[nx][ny] = True
                queue.append((nx, ny))
                group.append((nx, ny))
                
    if len(group) >= 4:
        flag = True
        for x, y in group:
            board[x][y] = 0
            
    return flag
            
def drop():
    for j in range(m):
        queue = deque()
        for i in range(n-1, -1, -1):
            if board[i][j] != 0:
                queue.append(board[i][j])
            board[i][j] = 0
        
        idx = n-1
        while queue:
            board[idx][j] = queue.popleft()
            idx -= 1
            
ans = 0
while True:
    visited = [[False] * m for _ in range(n)]
    
    isPop = False
    for i in range(n):
        for j in range(m):
            if board[i][j] != 0 and not visited[i][j]:
                if play(i, j, visited):
                    isPop = True
    
    if isPop:
        ans += 1
        drop()
    else:
        break

print(ans)