from collections import deque

ret = 0
stack = deque()
def cal_max_profit(i, j, c, m, k):
    global ret
    
    if k >= m:
        if sum(stack) <= c:
            ret = max(ret, sum(x**2 for x in stack))
        return
        
    stack.append(honey[i][j+k])
    cal_max_profit(i, j, c, m, k+1)
    
    stack.pop()
    cal_max_profit(i, j, c, m, k+1)

T = int(input())
for test_case in range(1, T+1):
    n, m, c = map(int, input().split())
    honey = [list(map(int, input().split())) for _ in range(n)]
    
    max_profit = []
    
    for i in range(n):
        for j in range(n-m+1):
            ret = 0
            cal_max_profit(i, j, c, m, 0)
            max_profit.append((ret, i, j))
            
    max_profit.sort(reverse=True)
    
    val1, x1, y1 = max_profit[0]
    val2, x2, y2 = 0, 0, 0
    
    idx1 = 0
    idx2 = 0
    for i in range(1, len(max_profit)):
        val2, x2, y2 = max_profit[i]
        if x1 == x2 and abs(y1-y2) < m:
            continue
        
        idx2 = i
        break
        
    ans = val1 + val2
    
    idx1 += 1
    idx2 -= 1
    while idx1 < idx2:
        val1, x1, y1 = max_profit[idx1]
        val2, x2, y2 = max_profit[idx2]
        
        idx1 += 1
        idx2 -= 1
        
        if x1 == x2 and abs(y1-y2) < m:
            continue
        
        ans = max(ans, val1 + val2)
        
    print(f"#{test_case} {ans}")