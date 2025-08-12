import java.util.*;

public class Solution {
	static int V, E, p1, p2;
	static int[] parent;
	static List<Integer>[] child;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			V = sc.nextInt();
			E = sc.nextInt();
			p1 = sc.nextInt();
			p2 = sc.nextInt();
			
			parent = new int[V + 1];
			child = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++) {
				child[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < E; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				parent[b] = a;
				child[a].add(b);
			}
			
			int lca = findLCA(p1, p2);
			int size = getSubtreeSize(lca);
			
			System.out.println("#" + test_case + " " + lca + " " + size);
		}
		}
			
		
		// 최소 공통 조상 찾기
		static int findLCA(int a, int b) {
			Set<Integer> ancestors = new HashSet<>();
			while (a != 0) {
				ancestors.add(a);
				a = parent[a];
			}
			while (b != 0) {
				if (ancestors.contains(b)) return b;
				b = parent[b];
			}
			return 1;
		}
		
		static int getSubtreeSize(int root) {
			int count = 0;
			Stack<Integer> stack = new Stack<>();
			stack.push(root); // 시작 노드(root)부터 스택에 넣기
			
			while (!stack.isEmpty()) {
				int cur = stack.pop();
				count++;
				for (int next : child[cur]) { // 현재 노드의 자식 노드들을 스택에 추가
					stack.push(next);
				}
			}
			return count;
		}

}
