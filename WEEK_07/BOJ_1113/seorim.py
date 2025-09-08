import sys
from collections import deque
import heapq

input = sys.stdin.readline
n, m = map(int, input().split())
pool = [list(map(int, list(input().rstrip()))) for _ in range(n)]

direction = [(-1,0),(1,0),(0,-1),(0,1)]
visited = [[False] * m for _ in range(n)]

heap = []
ans = 0
for i in range(n):
    for j in range(m):
        if i == 0 or i == n-1 or j == 0 or j == m-1:
            heapq.heappush(heap, (pool[i][j], i, j))
            visited[i][j] = True
            
while heap:
    height, x, y = heapq.heappop(heap)
    
    for dx, dy in direction:
        nx, ny = x + dx, y + dy
        
        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            continue
        
        if not visited[nx][ny]:
            visited[nx][ny] = True
            if pool[nx][ny] < height:
                ans += height - pool[nx][ny]
                pool[nx][ny] = height
            heapq.heappush(heap, (pool[nx][ny], nx, ny))
            
print(ans)