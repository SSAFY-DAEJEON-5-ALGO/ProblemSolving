import sys
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().split())
field = [list(map(int, input().split())) for _ in range(n)]

direction = [(0,1), (0,-1), (1,0), (-1,0)]

def mark_island():
    num = 2
    cnt = 0
    for i in range(n):
        for j in range(m):
            if field[i][j] == 1:
                cnt += 1
                field[i][j] = num
                queue = deque([(i, j)])
                while queue:
                    x, y = queue.popleft()
                    
                    for dx, dy in direction:
                        nx, ny = x + dx, y + dy
                        if nx < 0 or nx >= n or ny < 0 or ny >= m:
                            continue
                        if field[nx][ny] == 1:
                            queue.append((nx, ny))
                            field[nx][ny] = num
            
                num += 1
    
    return cnt

dist = set()
def dist_between_islands():
    for x in range(n):
        for y in range(m):
            if field[x][y] > 0:
                for dx, dy in direction:
                    d = 1
                    while True:
                        nx, ny = x + dx*d, y + dy*d
                        if nx < 0 or nx >= n or ny < 0 or ny >= m:
                            break
                        
                        if d <= 2 and field[nx][ny] != 0:
                            break
                        
                        if field[nx][ny] > field[x][y]:
                            dist.add((d-1, field[x][y], field[nx][ny]))
                            break
                        elif field[nx][ny] != 0 and field[nx][ny] <= field[x][y]:
                            break
                            
                        d += 1

class UnionFind:
    def __init__(self, n):
        self.parent = [i for i in range(n+2)]
    
    def find_parent(self, x):
        while x != self.parent[x]:
            self.parent[x] = self.parent[self.parent[x]]
            x = self.parent[x]
        
        return x
    
    def union(self, x, y):
        x_root = self.find_parent(x)
        y_root = self.find_parent(y)
        
        if x_root < y_root:
            self.parent[y_root] = x_root
        else:
            self.parent[x_root] = y_root
        
    def is_connected(self, x, y):
        return self.find_parent(x) == self.find_parent(y)

def kruskal(cnt, dist):
    total = 0
    num_bridge = 0
    uf = UnionFind(cnt)
    for d, a, b in dist:
        if not uf.is_connected(a, b):
            uf.union(a, b)
            total += d
            num_bridge += 1
    
    if num_bridge + 1 == cnt:
        print(total)
    else:
        print(-1)
                            
cnt = mark_island()
print()
for line in field:
    print(*line)

dist_between_islands()
dist = sorted(list(dist))
print(*dist)

kruskal(cnt, dist)