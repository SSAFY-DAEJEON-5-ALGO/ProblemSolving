import sys
from math import inf

input = sys.stdin.readline

n = int(input())
formula = input().rstrip()
ans = -inf

def calculate(a, op, b):
    return eval(f"{a}{op}{b}")

def bt(idx, curr):
    global ans
    
    if idx >= n:
        ans = max(ans, curr)
        return
    
    # 괄호 치지 않음
    bt(idx+2, calculate(curr, formula[idx], formula[idx+1]))
    
    # 괄호 침
    if idx + 3 < n:
        in_parentheses = calculate(formula[idx+1], formula[idx+2], formula[idx+3])
        bt(idx+4, calculate(curr, formula[idx], in_parentheses))
        
bt(1, int(formula[0]))
print(ans)