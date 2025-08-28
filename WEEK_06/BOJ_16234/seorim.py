import sys
from collections import deque

input = sys.stdin.readline

n, l, r = map(int, input().split())
people = [list(map(int, input().split())) for _ in range(n)]
direction = [(0, -1), (0, 1), (1, 0), (-1, 0)]

def bfs(visited, x, y, idx):
    global n, l, r
    
    queue = deque([(x, y)])
    visited[x][y] = idx
    total = people[x][y]
    cnt = 1
    
    while queue:
        cx, cy = queue.popleft()
        
        for dx, dy in direction:
            nx, ny = cx + dx, cy + dy
            
            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                continue
            
            diff = abs(people[cx][cy] - people[nx][ny])
            if visited[nx][ny] != idx and l <= diff and diff <= r:
                visited[nx][ny] = idx
                queue.append((nx, ny))
                total += people[nx][ny]
                cnt += 1
                
    return total // cnt

day = 0
while True:
    visited = [[-1] * n for _ in range(n)]
    
    idx = 0
    is_move = False
    group_new = []
    for i in range(n):
        for j in range(n):
            if visited[i][j] == -1:
                avg = bfs(visited, i, j, idx)
                group_new.append(avg)
                idx += 1
                
    is_move = False
    for i in range(n):
        for j in range(n):
            curr = visited[i][j]
            if group_new[curr] != people[i][j]:
                people[i][j] = group_new[curr]
                is_move = True
                
    if not is_move:
        print(day)
        break
    day += 1