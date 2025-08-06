package dp.knapsack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private final int[][] dp;

    public Solution() {
        dp = new int[501][1001];
        Arrays.fill(dp[0], 0);
        for (int i = 0; i < 501; i++) dp[i][0] = 0;
    }

    public int knapsack01(int[] wt, int[] val, int n, int W) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (wt[i - 1] <= j) {
                    dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
                } else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][W];
    }

    private void find(List<Integer> ks, int[] wtt, int[] val, int n, int w) {
        if (w == 0) {
            ks.forEach(x -> System.out.print("[" + wtt[x] + "," + val[x] + "] "));
            System.out.println();
            return;
        }

        if (n <= 0 || w < 0) return;

        if (w >= val[n-1]) {
            ks.add(n-1);
            find(ks, wtt, val, n-1, w - val[n-1]);
            ks.remove(ks.size()-1);
        }
        find(ks, wtt, val, n-1, w);
    }

    public void printKnapsack(int[] wt, int[] val, int n, int W) {
        List<Integer> ks = new ArrayList<>();
        find(ks, wt, val, n, dp[n][W]);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] wt = {4, 5, 1, 2};
        int[] val = {1, 2, 3, 4};
        int W = 6;
        System.out.println("Solution for 01 Knapsack is " + sol.knapsack01(wt, val, wt.length, W));
        sol.printKnapsack(wt, val, wt.length, W);
    }
}
