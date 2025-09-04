import sys
sys.setrecursionlimit(10**6)

input = sys.stdin.readline

for _ in range(4):
    data = list(map(int, input().split()))
    result = [data[i*3:i*3+3] for i in range(6)]
    result.sort(key=lambda x: (-x[0], -x[1], -x[2]))
    
    def pre():
        for team in range(6):
            if sum(result[team]) != 5:
                return False
        return True
    
    if not pre():
        print(0, end=' ')
        continue
    
    status = [[0] * 3 for _ in range(6)]  # (win, draw, lose)
    games = []
    for i in range(6):
        for j in range(i+1, 6):
            games.append((i, j))
    
    flag = 0
    def bt(depth):
        global flag
        
        if depth == 15:
            if sorted(status) == sorted(result):
                flag = 1
            return
        
        a, b = games[depth]
        
        # team_a win
        if flag == 0:
            if status[a][0] < result[a][0] and status[b][2] < result[b][2]:
                status[a][0] += 1
                status[b][2] += 1
                bt(depth+1)
                status[a][0] -= 1
                status[b][2] -= 1
        
        # draw
        if flag == 0:
            if status[a][1] < result[a][1] and status[b][1] < result[b][1]:
                status[a][1] += 1
                status[b][1] += 1
                bt(depth+1)
                status[a][1] -= 1
                status[b][1] -= 1
        
        # team_b win
        if flag == 0:
            if status[a][2] < result[a][2] and status[b][2] < result[b][2]:
                status[a][2] += 1
                status[b][0] += 1
                bt(depth+1)
                status[a][2] -= 1
                status[b][0] -= 1
            
    bt(0)
    print(flag, end=' ')