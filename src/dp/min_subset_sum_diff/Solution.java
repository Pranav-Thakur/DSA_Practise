package dp.min_subset_sum_diff;

import java.util.Arrays;

public class Solution {

    // 2D dp solution
    // boolean[][] dp = new boolean[n+1][tar+1];
    // dp[i][j] = dp[i-1][j]; // so later dp[i][j] used, no else required in the 2nd loop
    // for i 1 to n then for j = 0 to tar
    // if (j >= arr[i-1]) dp[i][j] = dp[i][j] || dp[i-1][j - arr[i-1]]

    public int _1D_DP(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for (int x : arr) sum += x;

        int tar = sum/2 + 1;
        boolean[] curr = new boolean[tar+1];
        boolean[] prev = new boolean[tar+1];
        prev[0] = true;

        for (int k : arr) {
            System.arraycopy(prev, 0, curr, 0, tar + 1);
            for (int j = 0; j <= tar; j++) {
                if (j >= k) curr[j] = curr[j] || prev[j - k];
            }
            System.arraycopy(curr, 0, prev, 0, tar + 1);
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

        for (int k : arr) {
            System.arraycopy(prev, 0, curr, 0, tar + 1);
            for (int j = 0; j <= tar; j++) {
                if (j >= k) curr[j] = curr[j] || prev[j - k];
            }
            System.arraycopy(curr, 0, prev, 0, tar + 1);
        }

        int ans = Integer.MAX_VALUE;
        for (int j = tar; j >= 0; j--) {
            if (prev[j]) {
                ans = Math.min(ans, Math.abs(sm - 2*j));
            }
        }

        return ans;
    }


    public int countMinDiff(int[] arr, int diff) {
        int n = arr.length;
        int sum = Arrays.stream(arr).sum();

        int tar = sum/2 + 1;
        int[] curr = new int[tar+1];
        int[] prev = new int[tar+1];
        prev[0] = 1;

        Arrays.stream(prev).forEach(x -> System.out.print(x + " "));
        System.out.println();

        for (int k : arr) {
            System.arraycopy(prev, 0, curr, 0, tar + 1);
            for (int j = 0; j <= tar; j++) {
                if (j >= k) curr[j] += prev[j - k];
            }
            System.arraycopy(curr, 0, prev, 0, tar + 1);
            Arrays.stream(prev).forEach(x -> System.out.print(x + " "));
            System.out.println();
        }

        // as s1 - s2 = diff and s1 + s2 = sum => 2s1 = sum + diff
        int ans = (sum+diff)/2;
        if (ans > tar) return 0;
        return prev[ans];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {1, 1, 2, 3};
        System.out.println("Solution for min subsetSum diff is " + sol.minimumDifference(arr));
        System.out.println("Solution for min subsetSum diff is " + sol._1D_DP(arr));
        System.out.println("Solution for count subsetSum with diff is " + sol.countMinDiff(arr, 0));
        //sol.printSubsetSum(arr, sum);
    }
}
