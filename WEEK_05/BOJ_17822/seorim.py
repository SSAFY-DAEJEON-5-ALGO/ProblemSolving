import sys

input = sys.stdin.readline

n, m, t = map(int, input().split())
stencil = [list(map(int, input().split())) for _ in range(n)]

def rotate():
    global n, x, d, k
    
    for i in range(x-1, n, x):
        if d == 0:  # 시계 방향
            stencil[i] = stencil[i][-k:] + stencil[i][:-k]
        else:  # 반시계 방향
            stencil[i] = stencil[i][k:] + stencil[i][:k]
            

def remove_same_number():
    global n, m
    
    remove = [[False] * m for _ in range(n)]
    
    for i in range(n):
        for j in range(m):
            if stencil[i][j] == 0: continue
            
            if stencil[i][j-1] == stencil[i][j]:
                remove[i][j-1] = True
                remove[i][j] = True
            if stencil[i][j] == stencil[i][(j+1)%m]:
                remove[i][j] = True
                remove[i][(j+1)%m] = True
                
            if i < n-1 and stencil[i][j] == stencil[i+1][j]:
                remove[i][j] = True
                remove[i+1][j] = True
            if i > 0 and stencil[i-1][j] == stencil[i][j]:
                remove[i-1][j] = True
                remove[i][j] = True
    
    flag = False
    for i in range(n):
        for j in range(m):
            if remove[i][j]:
                stencil[i][j] = 0
                flag = True
    
    return flag

def adjust_number():
    global n, m
    
    total = 0
    cnt = 0
    for i in range(n):
        for j in range(m):
            if stencil[i][j] != 0:
                total += stencil[i][j]
                cnt += 1
                
    avg = total / cnt if cnt > 0 else 0
    for i in range(n):
        for j in range(m):
            if stencil[i][j] != 0:
                if stencil[i][j] < avg:
                    stencil[i][j] += 1
                elif stencil[i][j] > avg:
                    stencil[i][j] -= 1
                
for i in range(t):
    x, d, k = map(int, input().split())
    rotate()
    if not remove_same_number():
        adjust_number()
    
print(sum(sum(line) for line in stencil))