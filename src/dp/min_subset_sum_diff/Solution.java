package dp.min_subset_sum_diff;

import java.util.Arrays;

public class Solution {

    // 2D dp solution
    // boolean[][] dp = new boolean[n+1][tar+1];
    // dp[i][j] = dp[i-1][j]; // so later dp[i][j] used, no else required in the 2nd loop
    // for i 1 to n then for j = 0 to tar
    // if (j >= arr[i-1]) dp[i][j] = dp[i][j] || dp[i-1][j - arr[i-1]]

    public int _1D_DP(int arr[]) {
        int n = arr.length;
        int sum = 0;
        for (int x : arr) sum += x;

        int tar = sum/2 + 1;
        boolean[] curr = new boolean[tar+1];
        boolean[] prev = new boolean[tar+1];
        prev[0] = true;

        for (int i = 0; i < n; i++) {
            System.arraycopy(prev, 0, curr, 0, tar+1);
            for (int j = 0; j <= tar; j++) {
                if (j >= arr[i]) curr[j] = curr[j] || prev[j - arr[i]];
            }
            System.arraycopy(curr, 0, prev, 0, tar+1);
        }

        int ans = Integer.MAX_VALUE;
        for (int j = tar; j >= 0; j--) {
            if (prev[j]) {
                ans = Math.min(ans, Math.abs(sum - 2*j));
            }
        }

        return ans;
    }

    // for arr having negative elements also
    public int minimumDifference(int[] arr) {
        // to handle negative case, and make -x to x as 1 to 2x+1
        int minEle = arr[0];
        for (int x : arr) minEle = Math.min(minEle, x);
        if (minEle < 0)
            minEle = Math.abs(minEle) + 1;
        else minEle = 0;

        if (minEle != 0) {
            final int y = minEle;
            arr = Arrays.stream(arr).map(x -> x + y).toArray();
        }

        int sm = Arrays.stream(arr).sum();

        int tar = sm/2 + 1;
        boolean[] curr = new boolean[tar+1];
        boolean[] prev = new boolean[tar+1];
        prev[0] = true;

        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(prev, 0, curr, 0, tar+1);
            for (int j = 0; j <= tar; j++) {
                if (j >= arr[i]) curr[j] = curr[j] || prev[j - arr[i]];
            }
            System.arraycopy(curr, 0, prev, 0, tar+1);
        }

        int ans = Integer.MAX_VALUE;
        for (int j = tar; j >= 0; j--) {
            if (prev[j]) {
                ans = Math.min(ans, Math.abs(sm - 2*j));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {76,8,45,20,74,84,28,1};
        System.out.println("Solution for min subsetSum diff is " + sol.minimumDifference(arr));
        System.out.println("Solution for min subsetSum diff is " + sol._1D_DP(arr));
        //sol.printSubsetSum(arr, sum);
    }
}
