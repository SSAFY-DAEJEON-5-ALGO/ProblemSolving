import java.util.Scanner;

public class Main {
	static int N;
	static String current;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        backtrack("");

    }

    public static void backtrack(String current) {
        if (current.length() == N) {
        	System.out.println(current);
        	System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            String newSeq = current + i;
            if (isGood(newSeq)) {
                backtrack(newSeq);
            }
        }
    }

    public static boolean isGood(String seq) {
        int length = seq.length();
        // 수열의 맨 끝에서 그 길이만큼 두 덩어리를 잘라내어 같은지 확인
        for (int subLen = 1; subLen <= length / 2; subLen++) {
            String behind = seq.substring(length - subLen);
            // behind와 인접한 부분 수열 front
            String front = seq.substring(length - 2 * subLen, length - subLen);

            if (front.equals(behind)) {
                return false;
            }
        }
        return true;
    }
}
