import sys
from collections import deque

input = sys.stdin.readline

n, m, k = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
operator = [list(map(int, input().split())) for _ in range(k)]
operator = [[r-1, c-1, s] for r, c, s in operator]
    
def rotate(A, r, c, s):
    for layer in range(1, s+1):
        top, bottom = r-layer, r+layer
        left, right = c-layer, c+layer
        
        nxt = A[top][left]
        for i in range(left+1, right+1):
            A[top][i], nxt = nxt, A[top][i]
        
        for i in range(top+1, bottom+1):
            A[i][right], nxt = nxt, A[i][right]
        
        for i in range(right-1, left-1, -1):
            A[bottom][i], nxt = nxt, A[bottom][i]
            
        for i in range(bottom-1, top-1, -1):
            A[i][left], nxt = nxt, A[i][left]

ans = 50 * 100 + 1
stack = deque()
def bt():
    global ans, k
    
    if len(stack) == k:
        A = [line[:] for line in arr]
        for x in stack:
            r, c, s = operator[x]
            rotate(A, r, c, s)
        
        ans = min(ans, min(map(sum, A)))
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