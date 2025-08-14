from collections import deque

dirt = [(-1, 0), (0, -1), (1, 0), (0, 1)]  # 상좌하우
tunnel = [
    [],  # 0 : 터널 없음
    dirt[:],  # 1 : 상좌하우
    [dirt[0], dirt[2]],  # 2 : 상하
    [dirt[1], dirt[3]],  # 3 : 좌우
    [dirt[0], dirt[3]],  # 4 : 상우
    [dirt[2], dirt[3]],  # 5 : 하우
    [dirt[1], dirt[2]],  # 6 : 좌하
    [dirt[0], dirt[1]]  # 7 : 상좌
]

T = int(input())
for test_case in range(1, T+1):
    n, m, r, c, l = map(int, input().split())
    
    map_ = [list(map(int, input().split())) for _ in range(n)]
    
    def canGo(dx, dy, nx, ny):
        next_tunnel = map_[nx][ny]
        
        this_tunnel_idx = -1
        for i in range(4):
            if (dx, dy) == dirt[i]:
                this_tunnel_idx = i
                break
        
        if dirt[(this_tunnel_idx + 2) % 4] in tunnel[next_tunnel]:
            return True
        
        return False
    
    def bfs():
        global n, m, r, c, l
        
        queue = deque()
        visited = [[False] * m for _ in range(n)]
        
        # (r, c)에서 time t
        queue.append((r, c, 1))  
        visited[r][c] = True
        
        cnt = 1
        while queue:
            x, y, t = queue.popleft()
            
            if t == l:
                continue
            
            this_tunnel = map_[x][y]
            for dx, dy in tunnel[this_tunnel]:
                nx, ny = x + dx, y + dy
                
                if nx < 0 or nx >= n or ny < 0 or ny >= m:
                    continue
                
                if not visited[nx][ny] and canGo(dx, dy, nx, ny):
                    visited[nx][ny] = True
                    queue.append((nx, ny, t+1))
                    cnt += 1
                    
        return cnt
    
    print(f"#{test_case} {bfs()}")