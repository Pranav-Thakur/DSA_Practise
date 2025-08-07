package dp.rod_cutting;

public class Solution {

    public int rodCut(int[] price) {
        int n = price.length;
        int[] dp = new int[n+1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = price[i-1]; // base case no cut, ie j = 0 case
            for (int j = 1; j < i; j++)
                dp[i] = Math.max(dp[i], price[j-1] + dp[i - j]);
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int[] price = {3, 5, 8, 9, 10, 17, 17, 20};
        Solution sol = new Solution();
        System.out.println("The solution for rod cutting is " + sol.rodCut(price));
    }
}
