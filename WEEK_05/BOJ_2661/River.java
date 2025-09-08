import java.io.*;
import java.util.*;

public class River {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		dfs(0,"");
	}
	static boolean isSolved = false;
	static void dfs(int idx, String s) {
		if(isSolved) return;
		if(idx==N) {
			if(check(s)) {
				System.out.println(s);
				isSolved = true;
			}
			return;
		}
		
		if(idx==0) {
			dfs(idx+1, s+"1");
			dfs(idx+1, s+"2");
			dfs(idx+1, s+"3");
		}else {
			if(!check(s)) return;
			
			char c = s.charAt(s.length()-1);
			if(c=='1') {
				dfs(idx+1, s+"2");
				dfs(idx+1, s+"3");
			}else if(c=='2') {
				dfs(idx+1, s+"1");
				dfs(idx+1, s+"3");
			}else if(c=='3') {
				dfs(idx+1, s+"1");
				dfs(idx+1, s+"2");
			}
		}
		
	}
	static boolean check(String s) {
		for(int len=1;len<=s.length()/2;len++)
			if(!check2(s, len)) return false;
		
		return true;
	}
	static boolean check2(String s, int len) {
		String a = s.substring(s.length()-len);
		String b = s.substring(s.length()-len*2, s.length()-len);
		
		if(a.equals(b)) return false;
		return true;
	}
}
