package dp.coin_change;

import java.util.Arrays;

public class Solution {

    public int count(int[] coins, int N, int amount) {
        Arrays.sort(coins);

        // Sort by score using a lambda expression (ascending)
        //Arrays.sort(students, (s1, s2) -> Integer.compare(s1.score, s2.score));

        int[] dp = new int[amount+1];
        dp[0] = 1;
        int mod = (int) (10e9 + 7);

        for (int i = 0; i < N; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
                dp[j] %= mod;
            }
        }

        return dp[amount];
    }

    public int coinChangeInMinCoins(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        //Arrays.stream(dp).forEach(x -> System.out.print(x + " "));
        //System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i] && dp[j - coins[i]] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j], 1 + dp[j - coins[i]]);
            }
            //Arrays.stream(dp).forEach(x -> System.out.print(x + " "));
            //System.out.println();
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int amount = 3;
        Solution sol = new Solution();
        System.out.println("The solution for coin change is " + sol.count(coins, coins.length, amount));
        System.out.println("The solution for coin change in min number is " + sol.coinChangeInMinCoins(coins, amount));
    }
}
