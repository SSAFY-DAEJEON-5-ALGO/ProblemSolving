import sys
from collections import defaultdict

input = sys.stdin.readline

n, m, k = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(n)]
land = [[5] * n for _ in range(n)]
trees = defaultdict(list)
for _ in range(m):
    x, y, z = map(int, input().split())
    trees[(x-1, y-1)].append(z)
    
def spring_and_summer():
    for r, c in trees.keys():
        sqr = sorted(trees[(r, c)])
        survive = []
        food = land[r][c]
        idx = 0
        for tree_year in sqr:
            if tree_year <= food:
                food -= tree_year
                survive.append(tree_year + 1)
            else:
                break
            idx += 1
        
        trees[(r, c)] = survive
        land[r][c] = food + _summer(sqr[idx:])
    
def _summer(dead):
    ret = 0
    for d in dead:
        ret += d // 2
    
    return ret

def fall():
    global n
    
    d = [[-1, -1], [-1, 0], [-1, 1], [0, -1], [0, 1], [1, -1], [1, 0], [1, 1]]
    
    new_trees = []
    for r, c in trees.keys():
        for tree in trees[(r, c)]:
            if tree % 5 == 0:
                for dr, dc in d:
                    nr, nc = r + dr, c + dc
                    if 0 <= nr and nr < n and 0 <= nc and nc < n:
                        new_trees.append((nr, nc))

    for key in new_trees:
        trees[key].append(1)
    
def winter():
    for i in range(n):
        for j in range(n):
            land[i][j] += A[i][j]
                
for _ in range(k):
    spring_and_summer()
    fall()
    winter()

cnt = 0
for key, value in trees.items():
    for tree in value:
        if tree > 0:
            cnt += 1

print(cnt)