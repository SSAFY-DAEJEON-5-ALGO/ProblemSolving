import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[][] A;
	static int[][] rotation;	
	static int result = Integer.MAX_VALUE;
	
    static void rotate(int[][] map, int r, int c, int s) {
    	for (int layer = 1; layer <= s; layer++) {
    		int top = r - layer;
    		int left = c - layer;
    		int right = c + layer;
    		int bottom = r + layer;
    		
    		int prev = map[top][left];
    		
    		// <- direction
    		for (int j = left + 1; j <= right; j++) {
    			int temp = map[top][j];
    			map[top][j] = prev;
    			prev = temp;
    		}
    		
    		// 위에서 아래
    		for (int i = top + 1; i <= bottom; i++) {
    			int temp = map[i][right];
    			map[i][right] = prev;
    			prev = temp;
    		}
    		
    		// ->
    		for (int j = right - 1; j >= left; j--) {
    			int temp = map[bottom][j];
    			map[bottom][j] = prev;
    			prev = temp;
    		}
    		
    		// 아래에서 위
    		for (int i = bottom - 1; i >= top; i--) {
    			int temp = map[i][left];
    			map[i][left] = prev;
    			prev = temp;
    		}
    	}
    }
    
    static int getRowSumMin(int[][] map) {
    	int min = Integer.MAX_VALUE;
    	for (int i = 0; i < map.length; i++) {
    		int sum = 0;
    		for (int j = 0; j < map[0].length; j++) {
    			sum += map[i][j];
    		}
    		min = Math.min(min, sum);
    	}
    	return min;
    }

	static void permute(List<Integer> order, boolean[] visited) {
	    if (order.size() == K) {
	        int[][] copied = copyMap(A);
	        for (int idx : order) {
	            int[] rot = rotation[idx];
	            rotate(copied, rot[0] - 1, rot[1] - 1, rot[2]);
	        }
	        result = Math.min(result, getRowSumMin(copied));
	        return;
	    }

	    for (int i = 0; i < K; i++) {
	        if (!visited[i]) {
	            visited[i] = true;
	            order.add(i);
	            permute(order, visited);
	            order.remove(order.size() - 1);
	            visited[i] = false;
	        }
	    }
	}
	
	static int[][] copyMap(int[][] map) {
	    int[][] newMap = new int[map.length][map[0].length];
	    for (int i = 0; i < map.length; i++) {
	        newMap[i] = map[i].clone(); 
	    }
	    return newMap;
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        A = new int[N][M];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < M; j++) {
        		A[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        rotation = new int[K][3];
        for (int i = 0; i < K; i++) {
        	st = new StringTokenizer(br.readLine());
        	rotation[i][0] = Integer.parseInt(st.nextToken());
        	rotation[i][1] = Integer.parseInt(st.nextToken());
        	rotation[i][2] = Integer.parseInt(st.nextToken());
        }
        
        boolean[] visited = new boolean[K];
        List<Integer> order = new ArrayList<>();
        permute(order, visited);
        
        System.out.println(result);

    }
}
