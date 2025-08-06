package dp.subset_sum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private final Boolean[][] dp;

    public Solution() {
        dp = new Boolean[201][10001];
        Arrays.fill(dp[0], false);
        for (int i = 0; i < 201; i++) dp[i][0] = true;
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

    // arr is of positive numbers
    // determine if there is a subset of arr[] with sum equal to given sum.
    public Boolean subsetSum(int[] arr, int sum) {
        int n = arr.length;
        print(n, sum);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i-1] <= j)
                    dp[i][j] = dp[i-1][j - arr[i-1]] || dp[i-1][j];
                else dp[i][j] = dp[i-1][j];
            }
            print(n, sum);
        }

        return dp[n][sum];
    }

    private void findSS(List<Integer> ss, int[] arr, int n, int sum) {
        if (n < 0 || sum < 0) return;

        if (sum == 0) {
            ss.forEach(x -> System.out.print(x + " "));
            System.out.println();
            return;
        }

        if (dp[n][sum]) {
            if (sum >= arr[n-1] && dp[n-1][sum-arr[n-1]]) {
                ss.add(arr[n-1]);
                findSS(ss, arr, n-1, sum-arr[n-1]);
                ss.remove(ss.size()-1);
            }
            findSS(ss, arr, n-1, sum);
        }
    }

    public void printSubsetSum(int[] arr, int sum) {
        int n = arr.length;
        List<Integer> ss = new ArrayList<>();
        findSS(ss, arr, n, sum);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {6, 3, 7, 4, 1, 6, 4, 3, 7};
        int sum = 2;
        System.out.println("Solution for subsetSum is " + sol.subsetSum(arr, sum));
        sol.printSubsetSum(arr, sum);
    }
}
