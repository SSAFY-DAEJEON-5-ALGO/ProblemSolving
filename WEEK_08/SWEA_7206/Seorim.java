import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Seorim {

    static int n;

    static Map<Integer, Integer> cache = new HashMap<>();
    static Set<Integer> nextNums;
    static boolean[] isTouch;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            System.out.println("#" + tc + " " + solve(n));
        }
        System.out.println();
    }

    static int solve(int num) {
        if (num < 10) {
            return 0;
        }

        if (cache.containsKey(num)) {
            return cache.get(num);
        }

        int between = String.valueOf(num).length() - 1;
        nextNums = new HashSet<>();
        isTouch = new boolean[between];
        split(0, between, num);

        int ret = -1;
        for (int next: nextNums) {
            ret = Math.max(ret, solve(next) + 1);
        }

        cache.put(num, ret);
        return ret;
    }

    static void split(int idx, int between, int num) {
        if (idx == between) {
            if (anyTrue(isTouch)) {
                int mul = 1, div = 1;
                for (boolean touched: isTouch) {
                    div *= 10;
                    if (touched) {
                        mul *= num % div;
                        num /= div;
                        div = 1;
                    }
                }
                mul *= num;
                nextNums.add(mul);
            }

            return;
        }

        isTouch[idx] = true;
        split(idx + 1, between, num);
        isTouch[idx] = false;
        split(idx + 1, between, num);
    }

    static boolean anyTrue(boolean[] arr) {
        for (boolean b : arr) {
            if (b) return true;
        }
        return false;
    }

}
