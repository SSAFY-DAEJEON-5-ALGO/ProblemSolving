import sys

input = sys.stdin.readline

n, m = map(int, input().split())
paper = [list(map(int, input().split())) for _ in range(n)]

ans = -1

# 하늘색
for line in paper:
    for j in range(m-3):
        ans = max(ans, sum(line[j:j+4]))
for j in range(m):
    for i in range(n-3):
        ans = max(ans, sum(paper[x][j] for x in range(i, i+4)))
        
# 노란색
for i in range(n-1):
    for j in range(m-1):
        ans = max(ans, paper[i][j] + paper[i][j+1] + paper[i+1][j] + paper[i+1][j+1])

# 주황색 + 핑크색
for i in range(n):
    for j in range(m-2):
        middle = sum(paper[i][j:j+3])
        for k in range(j, j+3):
            if i >= 1:
                ans = max(ans, middle + paper[i-1][k])
            if i < n-1:
                ans = max(ans, middle + paper[i+1][k])
for j in range(m):
    for i in range(n-2):
        middle = sum(paper[x][j] for x in range(i, i+3))
        for k in range(i, i+3):
            if j >= 1:
                ans = max(ans, middle + paper[k][j-1])
            if j < m-1:
                ans = max(ans, middle + paper[k][j+1])
      
# 초록색
for i in range(1, n-1):
    for j in range(m-1):
        middle = sum(paper[i][j:j+2])
        ans = max(ans, middle + paper[i-1][j] + paper[i+1][j+1])
        ans = max(ans, middle + paper[i-1][j+1] + paper[i+1][j])
for j in range(1, m-1):
    for i in range(n-1):
        middle = sum(paper[x][j] for x in range(i, i+2))
        ans = max(ans, middle + paper[i][j-1] + paper[i+1][j+1])
        ans = max(ans, middle + paper[i][j+1] + paper[i+1][j-1])
        
print(ans)