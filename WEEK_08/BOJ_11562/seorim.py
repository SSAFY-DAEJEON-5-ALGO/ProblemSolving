import sys
from math import inf

input = sys.stdin.readline

n, m = map(int, input().split())
dist = [[inf] * n for _ in range(n)]
for _ in range(m):
    u, v, b = map(int, input().split())
    dist[u-1][v-1] = 0
    dist[v-1][u-1] = 1-b

for i in range(n):
    for a in range(n):
        for b in range(n):
            if a == b:
                dist[a][b] = 0
            else:
                dist[a][b] = min(dist[a][b], dist[a][i] + dist[i][b])
                
k = int(input())
for _ in range(k):
    s, e = map(int, input().split())
    print(dist[s-1][e-1])