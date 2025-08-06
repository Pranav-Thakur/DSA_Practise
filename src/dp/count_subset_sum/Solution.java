package dp.count_subset_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private final int[][] dp;

    public Solution() {
        dp = new int[201][10001];
        Arrays.fill(dp[0], 0);
        for (int i = 0; i < 201; i++) dp[i][0] = 1;
    }

    void print(int n, int m) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int countSubsetSum(int[] arr, int sum) {
        int n = arr.length;
        print(n, sum);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j)
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i-1][j];
                else dp[i][j] = dp[i - 1][j];
            }
            print(n, sum);
        }

        return dp[n][sum];
    }

    private void find(List<Integer> css, int[] arr, int n, int sum) {
        if (sum == 0) {
            css.forEach(x -> System.out.print(x + " "));
            System.out.println();
            return;
        }

        if (n <= 0 || sum < 0) return;

        if (dp[n][sum] > 0) {
            if (sum >= arr[n-1] && dp[n-1][sum - arr[n-1]] > 0) {
                css.add(arr[n-1]);
                find(css, arr, n-1, sum - arr[n-1]);
                css.remove(css.size() - 1);
            }
            find(css, arr, n-1, sum);
        }
    }

    public void printSubsetSum(int[] arr, int sum) {
        int n = arr.length;
        List<Integer> css = new ArrayList<>();
        find(css, arr, n, sum);
    }

    public int _1D_DP(int[] arr, int sum) {
        int n = arr.length;
        int[] curr = new int[sum+1];
        int[] prev = new int[sum+1];
        prev[0] = 1; // base case when sum = 0

        for (int i = 1; i <= n; i++) {
            System.arraycopy(prev, 0, curr, 0, sum+1);
            for (int j = 0; j <= sum; j++) {
                if (arr[i-1] <= j) curr[j] += prev[j - arr[i-1]];
            }
            System.arraycopy(curr, 0, prev, 0, sum+1);
        }

        return curr[sum];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {6, 3, 7, 4, 1, 6, 4, 3, 9};
        int sum = 10;
        System.out.println("Solution for count subsetSum is " + sol.countSubsetSum(arr, sum));
        System.out.println("Solution for count subsetSum is " + sol._1D_DP(arr, sum));
        sol.printSubsetSum(arr, sum);
    }
}