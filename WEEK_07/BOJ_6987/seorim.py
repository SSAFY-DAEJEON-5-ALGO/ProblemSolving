import sys
sys.setrecursionlimit(10**6)

input = sys.stdin.readline

for _ in range(4):
    data = list(map(int, input().split()))
    result = [data[i*3:i*3+3] for i in range(6)]
    
    def is_all_five_matches():
        for team in range(6):
            if sum(result[team]) != 5:
                return False
        return True
    
    if not is_all_five_matches():
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
        
        for ra, rb in [(0, 2), (1, 1), (2, 0)]:  # A win, draw, B win
            if flag == 0 and (status[a][ra] < result[a][ra] and status[b][rb] < result[b][rb]):
                status[a][ra] += 1
                status[b][rb] += 1
                bt(depth+1)
                status[a][ra] -= 1
                status[b][rb] -= 1
        
    bt(0)
    print(flag, end=' ')