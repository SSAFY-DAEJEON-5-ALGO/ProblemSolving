N = int(input())

arr = []
for i in range(N):
    arr.append(list(map(int, input().split())))

dr = [0, 1, 1]
dc = [1, 0, 1]

def dfs(r, c, d):
    if r == N - 1 and c == N - 1:  # 도착지점
        return 1
    
    count = 0                
    if d == 0:  # 가로
        if c + 1 < N and arr[r][c + 1] == 0:
            count += dfs(r, c + 1, 0)
        if r + 1 < N and c + 1 < N and arr[r + 1][c] == 0 and arr[r][c + 1] == 0 and arr[r + 1][c + 1] == 0:
            count += dfs(r + 1, c + 1, 2)
    elif d == 1:  # 세로
        if r + 1 < N and arr[r + 1][c] == 0:
            count += dfs(r + 1, c, 1)
        if r + 1 < N and c + 1 < N and arr[r][c + 1] == 0 and arr[r + 1][c] == 0 and arr[r + 1][c + 1] == 0:
            count += dfs(r + 1, c + 1, 2)
    else:  # 대각선
        if c + 1 < N and arr[r][c + 1] == 0:
            count += dfs(r, c + 1, 0)
        if r + 1 < N and arr[r + 1][c] == 0:
            count += dfs(r + 1, c, 1)
        if r + 1 < N and c + 1 < N and arr[r + 1][c] == 0 and arr[r][c + 1] == 0 and arr[r + 1][c + 1] == 0:
            count += dfs(r + 1, c + 1, 2)

    return count
  
print(dfs(0, 1, 0))