from collections import defaultdict, deque

T = int(input())
for test_case in range(1, T+1):
    v, e, a, b = map(int, input().split())
    
    edges = list(map(int, input().split()))
    tree = defaultdict(list)
    for i in range(0, 2*e, 2):
        parent = edges[i]
        child = edges[i+1]
        
        tree[parent].append(child)
        
    def dfs(stack, root, target):
        if root == target:
            return stack
            
        stack.append(root)
        for child in tree[root]:
            ret = dfs(stack, child, target)
            if ret is None:
                stack.pop()
            else:
                return ret
    
    path_a = dfs(deque(), 1, a)
    path_b = dfs(deque(), 1, b)
    
    subtree_root = 0
    for x in reversed(path_a):
        if x in path_b:
            subtree_root = x
            break
        
    def countTree(root):
        if not tree[root]:
            return 1
        
        cnt = 1
        for child in tree[root]:
            cnt += countTree(child)
            
        return cnt
    
    print(f"#{test_case} {subtree_root} {countTree(subtree_root)}")