import java.util.*;
import java.io.*;

public class River {
	static String res = "";
	static public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        String start = "ICN";
        Set<String> airports = new HashSet<>();
        for(String[] ticket : tickets) {
        	airports.add(ticket[0]);
        	airports.add(ticket[1]);
        }
        int N = tickets.length;
        
        backt(0, N, start, start, new boolean[N], tickets);
        
        String[] result = res.split(" ");
        answer = result;
        
        return answer;
    }
	private static void backt(int idx, int target, String start, String curr, boolean[] visited, String[][] tickets) {
		if(idx==target) {
			if(res.equals("")) res = curr;
			else if(res.compareTo(curr)>0)
				res = curr;
			return;
		}
		
		for(int i=0;i<tickets.length;i++) {
			String[] ticket = tickets[i];
			
			if(ticket[0].equals(start) && !visited[i]) {
				visited[i] = true;
				backt(idx+1, target, ticket[1], curr+" "+ticket[1], visited, tickets);
				visited[i] = false;
			}
		}
	}
	public static void main(String[] args) {
		String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
		String[] answer = solution(tickets);
		
		for(int i=0;i<answer.length;i++)
			System.out.println(answer[i]);
	}
}
