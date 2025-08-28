import sys

input = sys.stdin.readline

def deepcopy(li):
    return [line[:] for line in li]

r, c, n = map(int, input().split())
board = []
for _ in range(r):
    data = list(input().rstrip())
    line = []
    for d in data:
        if d == 'O':
            line.append(True)
        else:
            line.append(False)
    board.append(line)

if n % 2 == 0:
    for _ in range(r):
        print("O" * c)
    exit()

direction = [(0, -1), (0, 1), (1, 0), (-1, 0)]
status = [deepcopy(board)]

for _ in range(2):
    bomb = [[False] * c for _ in range(r)]
    for i in range(r):
        for j in range(c):
            if board[i][j]:
                bomb[i][j] = True
                for dx, dy in direction:
                    nx, ny = i + dx, j + dy
                    if (0 <= nx and nx < r) and (0 <= ny and ny < c):
                        bomb[nx][ny] = True

    for i in range(r):
        for j in range(c):
            board[i][j] = not bomb[i][j]
            
    status.append(deepcopy(board))

idx = 0
if n == 1:
    idx = 0
elif n % 4 == 3:
    idx = 1
else:
    idx = 2

for line in status[idx]:
    for b in line:
        print("O" if b else ".", end="")
    print()