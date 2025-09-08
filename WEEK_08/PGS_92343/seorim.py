def solution(info, edges):
    n = len(info)
    graph = [[] for _ in range(n)]
    
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)
    
    answer = 1
    visited = [False] * n
    visited[0] = True
    can_go_nodes = [False] * n
    can_go_nodes[0] = True
    def dfs(root, animal):
        nonlocal answer
        
        if animal[0] <= animal[1]:
            return
        
        answer = max(answer, animal[0])
        
        for nxt in graph[root]:
            if not visited[nxt]:
                can_go_nodes[nxt] = True
        
        for nxt in range(n):
            if can_go_nodes[nxt] and not visited[nxt]:
                visited[nxt] = True
                animal[info[nxt]] += 1
                dfs(nxt, animal)
                animal[info[nxt]] -= 1
                visited[nxt] = False
        
        for nxt in graph[root]:
            if not visited[nxt]:
                can_go_nodes[nxt] = False
                
    dfs(0, [1, 0])
                
    return answer