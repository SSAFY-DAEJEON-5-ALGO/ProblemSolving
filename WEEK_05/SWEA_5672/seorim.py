T = int(input())
for tc in range(1, T+1):
    n = int(input())
    
    names = [input().rstrip() for _ in range(n)]
    
    s, e = 0, n-1
    
    new_line = []
    
    while s <= e:
        if names[s] < names[e]:
            new_line.append(names[s])
            s += 1
        elif names[e] < names[s]:
            new_line.append(names[e])
            e -= 1
        else:
            ns, ne = s + 1, e - 1
            while ns < ne and names[ns] == names[ne]:
                ns += 1
                ne -= 1
            
            if names[ns] < names[ne]:
                new_line.append(names[s])
                s += 1
            else:
                new_line.append(names[e])
                e -= 1
        
    print(f"#{tc} {"".join(new_line)}")