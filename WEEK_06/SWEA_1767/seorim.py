def bt(idx, total, cnt):
    global k, max_cnt, min_total
    
    left = k - idx
    if cnt + left < max_cnt:
        return
    
    if idx == k:
        if cnt > max_cnt:
            max_cnt = cnt
            min_total = total
        elif cnt == max_cnt:
            min_total = min(min_total, total)
        
        return
    
    x, y = core[idx]
    if is_already_connected(x, y):
        bt(idx + 1, total, cnt + 1)
    else:
        for d in range(4):
            if can_connect(x, y, d):
                length = toggle(x, y, d, True)
                bt(idx + 1, total + length, cnt + 1)
                toggle(x, y, d, False)
            
            bt(idx + 1, total, cnt)
    
def toggle(x, y, d, b):
    global n
    
    if d == 0:  # up
        for i in range(x-1, -1, -1):
            is_line[i][y] = b
        return x
    elif d == 1:  # right
        for j in range(y+1, n):
            is_line[x][j] = b
        return n - y - 1
    elif d == 2:  # down
        for i in range(x+1, n):
            is_line[i][y] = b
        return n - x - 1
    else:  # left
        for j in range(y-1, -1, -1):
            is_line[x][j] = b
        return y
            
def can_connect(x, y, d):
    global n
    
    if d == 0:  # up
        if any(is_line[i][y] for i in range(x-1, -1, -1)):
            return False
    elif d == 1:  # right
        if any(is_line[x][j] for j in range(y+1, n)):
            return False
    elif d == 2:  # down
        if any(is_line[i][y] for i in range(x+1, n)):
            return False
    else:  # left
        if any(is_line[x][j] for j in range(y-1, -1, -1)):
            return False
    
    return True

def is_already_connected(x, y):
    return x == 0 or y == 0 or x == n-1 or y == n-1

T = int(input())
for tc in range(1, T+1):
    n = int(input())
    
    core = []
    is_line = [[False] * n for _ in range(n)]
    for i in range(n):
        line = list(map(int, input().split()))
        for j in range(n):
            if line[j] == 1:
                core.append((i, j))
                is_line[i][j] = True

    k = len(core)
    max_cnt = -1
    min_total = 1000
    
    bt(0, 0, 0)
    
    print(f"#{tc} {min_total}")