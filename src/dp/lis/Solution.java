package dp.lis;

public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {10,9,2,5,3,7,101,18};
        System.out.println("The Solution for lis " + sol.lis(arr));
    }

    private int lis(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n+1];
        dp[0] = 0;

        int mx = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i-1] > arr[j])
                    dp[i] = Math.max(dp[i], 1 + dp[j+1]);
            }
            mx = Math.max(mx, dp[i]);
        }

        return mx;
    }
}
