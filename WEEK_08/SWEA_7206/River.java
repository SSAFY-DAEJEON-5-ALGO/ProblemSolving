import java.util.*;
import java.io.*;

public class River {
	static HashMap<Integer, Integer> memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			int N = Integer.parseInt(br.readLine());
			
			memo = new HashMap<>();
			int turn = sol(N);
			System.out.printf("#%d %d\n",tc,turn);
		}
	}
	private static int sol(int N) {
		if(N<10) return 0;
		
		int max = 0;
		String str = String.valueOf(N);
		int len = str.length()-1;
		
		for(int i=1, size=1<<len;i<size;i++) {
			int num = str.charAt(0)-'0';
			int mul = 1;
			for(int j=0;j<len;j++)
				if((i&(1<<j))==0)	//쪼개는 위치x
					num = num*10 + str.charAt(j+1)-'0';
				else {
					mul*=num;
					num = str.charAt(j+1)-'0';
				}
			mul *= num;
			int cnt = 0;
			if(memo.containsKey(mul))
				cnt = memo.get(mul);
			else {
				cnt = sol(mul);
				memo.put(mul, cnt);
			}
			max = Math.max(max, cnt);
		}
		return max+1;
	}
}