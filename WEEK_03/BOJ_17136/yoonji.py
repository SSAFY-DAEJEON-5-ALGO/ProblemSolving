import sys
sys.setrecursionlimit(10**6)

arr = [list(map(int, sys.stdin.readline().split())) for _ in range(10)]
paper = [0, 5, 5, 5, 5, 5]  # 색종이 크기별 개수

def can_attach(x, y, size):
    if x + size > 10 or y + size > 10:
        return False
    for i in range(x, x + size):
        for j in range(y, y + size):
            if arr[i][j] != 1:
                return False
    return True

def attach(x, y, size, value):
    for i in range(x, x + size):
        for j in range(y, y + size):
            arr[i][j] = value

def dfs(x, y, used):
    if x >= 10:
        return used

    if y >= 10:
        return dfs(x + 1, 0, used)

    # 1인 경우: 색종이를 붙일 수 있는 모든 경우 탐색
    if arr[x][y] == 1:
        min_val = float('inf')
        for size in range(5, 0, -1):
            if paper[size] > 0 and can_attach(x, y, size):
                attach(x, y, size, 0)
                paper[size] -= 1

                temp = dfs(x, y + 1, used + 1)
                min_val = min(min_val, temp)

                attach(x, y, size, 1)
                paper[size] += 1
        return min_val
    else:
        return dfs(x, y + 1, used)

answer = dfs(0, 0, 0)
if answer == float('inf'):
    print(-1)
else:
    print(answer)
