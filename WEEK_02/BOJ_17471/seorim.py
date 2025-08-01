import sys
from itertools import combinations
from collections import deque
from math import inf

input = sys.stdin.readline


n = int(input())
people = [0] + list(map(int, input().split()))
adj = [[]]
for i in range(n):
    data = list(map(int, input().split()))[1:]
    adj.append(data)

def bfs(n, arr, flag):
    bfs_visited = [False] * (n+1)
    queue = deque()
    for i in range(1, n+1):
        if arr[i] == flag:
            bfs_visited[i] = True
            queue.append(i)
            break
    
    connected = 0
    while queue:
        x = queue.popleft()
        connected += 1
        
        for nxt in adj[x]:
            if not bfs_visited[nxt] and arr[nxt] == flag:
                queue.append(nxt)
                bfs_visited[nxt] = True
                
    return connected

ans = inf
for cnt in range(1, n//2+1):
    for combi in combinations(range(1, n+1), cnt):
        visited = [False] + [True if i in combi else False for i in range(1, n+1)]
        true_connected = bfs(n, visited, True)
        false_connected = bfs(n, visited, False)
        
        if true_connected == cnt and false_connected == n-cnt:
            a, b = 0, 0
            for i, v in enumerate(visited[1:], 1):
                if v:
                    a += people[i]
                else:
                    b += people[i]
                    
            ans = min(ans, abs(a-b))
            
print(ans if ans != inf else -1)