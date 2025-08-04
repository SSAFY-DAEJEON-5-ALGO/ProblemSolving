import sys
from collections import deque

input = sys.stdin.readline

t = int(input())
for _ in range(t):
    n, c, i = map(int, input().split())
    program = input().rstrip()
    string = input().rstrip()

    arr = [0] * n
    loop_pair = {}
    pointer = 0
    cmd = 0
    char_idx = 0

    def find_loop_pair():
        stack = deque()
        for x, p in enumerate(program):
            if p == '[':
                stack.append(x)
            elif p == ']':
                opened = stack.pop()
                loop_pair[opened] = x
                loop_pair[x] = opened
    
    def cal(operator):
        global pointer, cmd, char_idx, loop_end
        
        match operator:
            case '-':
                arr[pointer] = (arr[pointer] - 1) % 256
            case '+':
                arr[pointer] = (arr[pointer] + 1) % 256
            case '<':
                pointer = (pointer - 1) % n
            case '>':
                pointer = (pointer + 1) % n
            case '[':
                if arr[pointer] == 0:
                    cmd = loop_pair[cmd]
            case ']':
                if arr[pointer] != 0:
                    cmd = loop_pair[cmd]
            case ',':
                if char_idx < len(string):
                    arr[pointer] = ord(string[char_idx])
                    char_idx += 1
                else:
                    arr[pointer] = 255
    
    find_loop_pair()
    
    cnt = 0
    max_idx = c
    while cmd < c:
        cal(program[cmd])
        cnt += 1
        
        if cnt >= 50_000_000:
            max_idx = min(max_idx, cmd)
        
        cmd += 1
        
        if cnt >= 2 * 50_000_000:
            print(f"Loops {max_idx} {loop_pair[max_idx]}")
            break
        
    else:
        print("Terminates")