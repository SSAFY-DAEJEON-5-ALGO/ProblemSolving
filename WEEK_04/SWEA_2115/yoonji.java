import java.util.*;
 
public class Solution {
    static int N, M, C;
    static int maxProfit, tempMaxProfit;
    static int[][] map;
    static boolean[][] isSelected;
     
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int T;
            T=sc.nextInt();
         
            for(int test_case = 1; test_case <= T; test_case++)
            {
         
                N = sc.nextInt();
                M = sc.nextInt();
                C = sc.nextInt();
                map = new int[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        map[i][j]= sc.nextInt();
         
                    }
                }
                 
                int[][] blockProfits = new int[N][N];
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c <= N - M; c++) {
                        blockProfits[r][c] += getMaxProfit(r, c);
                    }
                }
                 
                int answer = 0;
                // 첫번째 벌통을 선택하는 경우
                for (int r1 = 0; r1 < N; r1++) {
                    for (int c1 = 0; c1 <= N-M; c1++) {
                         // 두번째 벌통: 첫번째 벌통과 같은 행에 있는 경우
                        for (int c2 = c1 + M; c2 <= N-M; c2++) {
                            int currentProfit = blockProfits[r1][c1] + blockProfits[r1][c2];
                            answer = Math.max(answer, currentProfit);
                        }
                         // 두번째 벌통: 첫번째 벌통과 서로 다른 행에 있는 경우
                        for (int r2 = r1 + 1; r2 < N; r2++) {
                            for (int c2 = 0; c2 <= N-M; c2++) {
                                int currentProfit = blockProfits[r1][c1] + blockProfits[r2][c2];
                                answer = Math.max(answer, currentProfit);
                            }
                        }
                    }
                }
                 
                System.out.println("#" + test_case + " " + answer);
         
            }
        }
     
        static int getMaxProfit(int r, int c) {
            // 가로로 연속된 M개의 벌통을 선택해야하는데 불가능하면 0을 반환
            if (c + M > N) {
                return 0;
            }
             
            int[] temp = Arrays.copyOfRange(map[r], c, c+M);
             
            tempMaxProfit = Integer.MIN_VALUE;
             
            findSubsetProfit(temp, 0, 0, 0);
             
            return tempMaxProfit;
        }
         
         
        static void findSubsetProfit(int[] block, int index, int currentHoneySum, int currentProfit) {
            // 기저 조건: 현재 벌통의 합이 C를 초과하면 더 이상 탐색하지 않음
            if (currentHoneySum > C) {
                return;
            }
            // 기저 조건: 벌통 2개를 선택
            if (index == M) {
                tempMaxProfit = Math.max(tempMaxProfit, currentProfit);
                return;
            }
                 
            int honey = block[index];
            // index번째 벌통을 선택하는 경우
            findSubsetProfit(block, index+1, currentHoneySum + honey, currentProfit + (honey * honey));
            // 선택하지 않는 경우
            findSubsetProfit(block, index+1, currentHoneySum, currentProfit);
     
        }   
 
}