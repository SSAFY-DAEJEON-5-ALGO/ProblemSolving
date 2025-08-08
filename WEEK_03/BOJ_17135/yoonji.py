import sys
input = sys.stdin.readline

N, M, D = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]

enemy = []
for i in range(N):
    for j in range(M):
        if arr[i][j] == 1:
            enemy.append((i, j))
enemy.sort(key=lambda x: x[1])

archers = [0, 0, 0]

def dfs(n, i):
    if n == 3:
        return game(archers, enemy)

    res = 0
    for j in range(i, M):
        archers[n] = j
        res = max(res, dfs(n + 1, j + 1))
    return res

def game(archers, enemy):
    from copy import deepcopy
    kill_cnt = 0
    enemies = deepcopy(enemy)

    while enemies:
        kill = set()
        for archer in archers:
            x, y, d = -1, -1, D + 1
            for r, c in enemies:
                n_d = (N - r) + abs(archer - c)
                if n_d <= D:
                    if n_d < d or (n_d == d and c < y):
                        x, y, d = r, c, n_d
            if x != -1:
                kill.add((x, y))

        temp = []
        for r, c in enemies:
            if (r, c) in kill:
                kill_cnt += 1
            elif r < N - 1:
                temp.append((r + 1, c))
        enemies = temp

    return kill_cnt

print(dfs(0, 0))
