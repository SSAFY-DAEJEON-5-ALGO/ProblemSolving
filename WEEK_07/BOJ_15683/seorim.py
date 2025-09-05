import sys

input = sys.stdin.readline

class Direction():
    d = [(-1, 0), (0, -1), (1, 0), (0, 1)]  # 상 좌 하 우
    direction = {
        1: [[3], [2], [1], [0]],
        2: [[1, 3], [0, 2]],
        3: [[0, 3], [2, 3], [1, 2], [0, 1]],
        4: [[0, 1, 3], [0, 2, 3], [1, 2, 3], [0, 1, 2]],
        5: [[0, 1, 2, 3]]
    }
    rotate_info = [0] * 6
    
    def rotate(self, num):
        curr_r = self.rotate_info[num]
        next_r = 0
        if num in [1, 3, 4]:
            next_r = (self.rotate_info[num] + 1) % 4
        elif num == 2:
            next_r = (self.rotate_info[num] + 1) % 2
        
        self.rotate_info[num] = next_r
        return self._convert(self.direction[num][curr_r])
    
    def num_of_case(self, num):
        if num in [1, 3, 4]:
            return 4
        elif num == 2:
            return 2
        return 1
        
    def _convert(self, li):
        return [self.d[i] for i in li]


n, m = map(int, input().split())
room = [list(map(int, input().split())) for _ in range(n)]

cctv = [(i, j) for i in range(n) for j in range(m) if 1 <= room[i][j] and room[i][j] <= 5]
k = len(cctv)

dirt = Direction()

def deepcopy(li):
    return [line[:] for line in li]

ans = 100
def bt(status, idx):
    global n, m, k, ans
    
    if idx == k:
        blind_spot = sum([1 for line in status for i in line if i == 0])
        ans = min(ans, blind_spot)
        return
    
    x, y = cctv[idx]
    curr = status[x][y]
    for _ in range(dirt.num_of_case(curr)):
        new_room = deepcopy(status)
        for dx, dy in dirt.rotate(curr):
            p = 1
            nx, ny = x + dx*p, y + dy*p
            while 0 <= nx and nx < n and 0 <= ny and ny < m:
                if status[nx][ny] == 6:
                    break
                
                if 1 <= status[nx][ny] and status[nx][ny] <= 5:
                    p += 1
                    nx, ny = x + dx*p, y + dy*p
                    continue
                    
                new_room[nx][ny] = -1
                p += 1
                nx, ny = x + dx*p, y + dy*p
                
        bt(new_room, idx+1)
        
bt(room, 0)
print(ans if ans < 100 else 0)