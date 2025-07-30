import sys
from collections import deque

input = sys.stdin.readline

n, m, k = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
operator = [list(map(int, input().split())) for _ in range(k)]
operator = [[r-1, c-1, s] for r, c, s in operator]
    
def rotate(A, r, c, s):
    tmp = 0
    for ly in range(1, s+1):
        nxt = A[r-ly][c-ly]
        for i in range(c-ly+1, c+ly+1):
            tmp = A[r-ly][i]
            A[r-ly][i] = nxt
            nxt = tmp
        
        for i in range(r-ly+1, r+ly+1):
            tmp = A[i][c+ly]
            A[i][c+ly] = nxt
            nxt = tmp
        
        for i in range(c+ly-1, c-ly-1, -1):
            tmp = A[r+ly][i]
            A[r+ly][i] = nxt
            nxt = tmp
            
        for i in range(r+ly-1, r-ly-1, -1):
            tmp = A[i][c-ly]
            A[i][c-ly] = nxt
            nxt = tmp

ans = 50 * 100 + 1
stack = deque()
def bt():
    global ans, k
    
    if len(stack) == k:
        A = [[x for x in line] for line in arr]
        for x in stack:
            r, c, s = operator[x]
            rotate(A, r, c, s)
        
        ans = min(ans, min(sum(line) for line in A))
        return
    
    for i in range(k):
        if not i in stack:
            stack.append(i)
            bt()
            stack.pop()
    
    if len(stack) < k:
        return

bt()    
print(ans)