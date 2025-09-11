import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Seorim {

    static int n;
    static int ans;
    static Map<Integer, Integer> cache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            ans = -1;

            System.out.println("#" + tc + " " + solve(n));
        }
    }

    static int solve(int num) {
        if (num < 10) {
            return 0;
        }

        if (cache.containsKey(num)) {
            return cache.get(num);
        }

        int k = String.valueOf(num).length();
        Set<Integer> nextNums = new HashSet<>();
        boolean[] touch = new boolean[k];
        split(1, k, num, nextNums, touch);

        int ret = -1;
        for (int next: nextNums) {
            ret = Math.max(ret, solve(next) + 1);
        }

        cache.putIfAbsent(num, ret);
        return ret;
    }

    static void split(int idx, int k, int num, Set<Integer> set, boolean[] touch) {
        if (idx == k) {
            if (!allFalse(touch)) {
                int mul = 1;
                int div = 1;
                for (int i = 1; i < k; i++) {
                    div *= 10;
                    if (touch[i]) {
                        mul *= num % div;
                        num /= div;
                        div = 1;
                    }
                }
                mul *= num;
                set.add(mul);
            }

            return;
        }

        touch[idx] = true;
        split(idx + 1, k, num, set, touch);
        touch[idx] = false;
        split(idx + 1, k, num, set, touch);
    }

    static boolean allFalse(boolean[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]) {
                return false;
            }
        }

        return true;
    }

}
