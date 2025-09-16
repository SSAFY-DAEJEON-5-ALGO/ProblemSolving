from collections import deque

T = int(input())
for tc in range(1, T+1):
    n, cmd = map(str, input().split())
    n = int(n)
    
    board = [list(map(int, input().split())) for _ in range(n)]
    
    if cmd == "up":
        for j in range(n):
            queue = deque()
            for i in range(n):
                if board[i][j] != 0:
                    queue.append(board[i][j])
                    board[i][j] = 0
            
            i = 0
            while queue:
                x = queue.popleft()
                if queue and x == queue[0]:
                    board[i][j] = x * 2
                    queue.popleft()
                else:
                    board[i][j] = x
                i += 1
    elif cmd == "down":
        for j in range(n):
            queue = deque()
            for i in range(n-1, -1, -1):
                if board[i][j] != 0:
                    queue.append(board[i][j])
                    board[i][j] = 0
            
            i = n-1
            while queue:
                x = queue.popleft()
                if queue and x == queue[0]:
                    board[i][j] = x * 2
                    queue.popleft()
                else:
                    board[i][j] = x
                i -= 1
    elif cmd == "left":
        for i in range(n):
            queue = deque()
            for j in range(n):
                if board[i][j] != 0:
                    queue.append(board[i][j])
                    board[i][j] = 0
            
            j = 0
            while queue:
                x = queue.popleft()
                if queue and x == queue[0]:
                    board[i][j] = x * 2
                    queue.popleft()
                else:
                    board[i][j] = x
                j += 1
    else:
        for i in range(n):
            queue = deque()
            for j in range(n-1, -1, -1):
                if board[i][j] != 0:
                    queue.append(board[i][j])
                    board[i][j] = 0
            
            j = n-1
            while queue:
                x = queue.popleft()
                if queue and x == queue[0]:
                    board[i][j] = x * 2
                    queue.popleft()
                else:
                    board[i][j] = x
                j -= 1
    
    print(f"#{tc}")
    for line in board:
        print(*line)