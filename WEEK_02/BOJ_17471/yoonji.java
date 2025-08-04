import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int min = Integer.MAX_VALUE;
	static int[] population;
	static List<Integer>[] graph;	
	static boolean[] select;
	
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        population = new int[N + 1];
        graph = new ArrayList[N + 1];
        select = new boolean[N + 1];
        
        // 인구 수
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
        	population[i] = Integer.parseInt(st.nextToken());
        	graph[i] = new ArrayList<>();
        }
        
        // 인접 정보
        for (int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int cnt = Integer.parseInt(st.nextToken());
        	for (int j = 0; j < cnt; j++) {
        		int to = Integer.parseInt(st.nextToken());
        		graph[i].add(to);
        	}
        }
        
        powerset(1);
        
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);

    }
    
    static void powerset(int idx) {
    	if (idx == N + 1) {
    		boolean hasA = false, hasB = false;
    		for (int i = 1; i <= N; i++) {
    			if (select[i]) hasA = true;
    			else hasB = true;
    		}
    		if (!hasA || !hasB) return;
    		
    		if (isConnected(true) && isConnected(false)) {
    			int sumA = 0, sumB = 0;
    			for (int i = 1; i <= N; i++) {
    				if (select[i]) sumA += population[i];
    				else sumB += population[i];
    			}
    			min = Math.min(min, Math.abs(sumA - sumB));
    		}
    		return;
    	}
    	
    	select[idx] = true;
    	powerset(idx + 1);
    	
    	select[idx] = false;
    	powerset(idx + 1);
    }
    
    static boolean isConnected(boolean target) {
    	boolean[] visited = new boolean[N + 1];
    	Queue<Integer> q = new LinkedList<>();
    	int start = -1;
    	
    	for (int i = 1; i <= N; i++) {
    		if (select[i] == target) {
    			start = i;
    			break;
    		}
    	}
    	
    	if (start == -1) return false;
    	
    	q.offer(start);
    	visited[start] = true;
    	int count = 1;
    	
    	while (!q.isEmpty()) {
    		int current = q.poll();
    		for (int next : graph[current]) {
    			if (!visited[next] && select[next] == target) {
    				visited[next] = true;
    				q.offer(next);
    				count++;
    			}
    		}
    	}
    	
    	int total = 0;
    	for (int i = 1; i <= N; i++) {
    		if (select[i] == target) total++;
    	}
    	
    	return count == total;
    }
}