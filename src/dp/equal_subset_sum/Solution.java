package dp.equal_subset_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private final Boolean[][] dp;

    public Solution() {
        dp = new Boolean[101][10001];
        Arrays.fill(dp[0], false);
        for (int i = 0; i < 101; i++) dp[i][0] = true;
    }

    public Boolean equalSubsetSum(int[] arr, int n) {
        int sum = 0;
        for (int x : arr) sum += x;

        // if odd sum then 2 equal sum partition is not possible
        if (sum%2 == 1) return false;

        int sm = sum/2;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sm; j++) {
                if (arr[i-1] <= j)
                    dp[i][j] = dp[i-1][j - arr[i-1]] || dp[i-1][j];
                else dp[i][j] = dp[i-1][j];
            }
        }

        return dp[n][sm];
    }

    private void find(List<Integer> ss, int[] arr, int n, int sm) {
        if (sm == 0) {
            ss.forEach(x -> System.out.print(x + " "));
            System.out.println();
            return;
        }

        if (n <= 0 || sm < 0) return;

        if (dp[n][sm]) {
            if (sm >= arr[n-1] && dp[n-1][sm - arr[n-1]]) {
                ss.add(arr[n-1]);
                find(ss, arr, n-1, sm - arr[n-1]);
                ss.remove(ss.size() - 1);
            }
            find(ss, arr, n-1, sm);
        }
    }

    public void printEqualSubsetSum(int[] arr, int n) {
        int sum = 0;
        for (int x : arr) sum += x;

        // not possible case
        if (sum%2 == 1) {
            System.out.println("As sum is odd, so no equal subset sum partition is not possible.");
            return;
        }

        List<Integer> ss = new ArrayList<>();
        find(ss, arr, n, sum/2);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {1, 5, 11, 5, 10};
        System.out.println("Solution for Equal Subset Sum is " + sol.equalSubsetSum(arr, arr.length));
        sol.printEqualSubsetSum(arr, arr.length);
    }
}
