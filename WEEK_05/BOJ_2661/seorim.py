import sys

n = int(sys.stdin.readline())

seq = []
def bt(depth):
    global n
    
    if depth == n:
        print("".join(list(map(str, seq))))
        exit()
    
    depth += 1
    for num in range(1, 4):
        seq.append(num)
        for i in range(1, depth//2+1):
            if seq[depth-2*i:depth-i] == seq[depth-i:]:
                break
        else:
            bt(depth)
        seq.pop()
            
bt(0)