import sys
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().split())
europe = [list(input().rstrip()) for _ in range(n)]

direction = {
    '|': [(-1, 0), (1, 0)],
    '-': [(0, -1), (0, 1)],
    '+': [(-1, 0), (0, -1), (1, 0), (0, 1)],
    '1': [(1, 0), (0, 1)],
    '2': [(-1, 0), (0, 1)],
    '3': [(-1, 0), (0, -1)],
    '4': [(0, -1), (1, 0)],
    '.': [(-1, 0), (0, -1), (1, 0), (0, 1)],
    'M': [(-1, 0), (0, -1), (1, 0), (0, 1)],
    'Z': []
}

queue = deque()
visited = [[False] * m for _ in range(n)]
x, y = -1, -1
for i in range(n):
    for j in range(m):
        if europe[i][j] == 'M':
            visited[i][j] = True
            queue.append((i ,j))
            break
    else:
        continue
    break

def get_block_type(bx, by):
    connect = []
    for i in range(4):
        dx, dy = direction['+'][i]
        nx, ny = bx + dx, by + dy
        
        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            continue
        
        near_block = europe[nx][ny]
        near_dir = direction['+'][(i+2)%4]
        if near_block == '.':
            continue
        elif near_block in ['M', 'Z']:
            if has_near_block(nx, ny):
                connect.append((dx, dy))
        else:
            if near_dir in direction[near_block]:
                connect.append((dx, dy))

    for key, value in direction.items():
        if value == connect and not key in ['M', 'Z']:
            return key
    
    return -1

def has_near_block(x, y):
    for dx, dy in direction['+']:
        nx, ny = x + dx, y + dy
        
        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            continue
        
        if europe[nx][ny] != '.':
            return False
        
    return True

for dx, dy in direction['+']:
    nx, ny = x + dx, y + dy
    
    if nx < 0 or nx >= n or ny < 0 or ny >= m:
        continue
    
    if europe[nx][ny] != '.':
        queue.append((nx, ny))
        visited[nx][ny] = True

while queue:
    x, y = queue.popleft()
    block = europe[x][y]
    
    if europe[x][y] == '.':
        block_type = get_block_type(x, y)
        if block_type != -1:
            print(x+1, y+1, block_type)
            break
    
    for dx, dy in direction[block]:
        nx, ny = x + dx, y + dy
        
        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            continue
        
        if not visited[nx][ny] and europe[nx][ny] != 'Z':
            queue.append((nx, ny))
            visited[nx][ny] = True