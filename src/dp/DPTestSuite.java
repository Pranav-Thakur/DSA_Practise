package dp;

/**
 * Comprehensive test suite for Dynamic Programming solutions
 */
public class DPTestSuite {
    
    public static void testCoinChange() {
        System.out.println("=== Testing Coin Change Solutions ===");
        
        dp.coin_change.Solution solution = new dp.coin_change.Solution();
        
        // Test 1: Basic coin change
        int[] coins1 = {1, 2, 3};
        int amount1 = 4;
        System.out.println("Test 1 - Coins: " + java.util.Arrays.toString(coins1) + ", Amount: " + amount1);
        System.out.println("Ways to make change: " + solution.count(coins1, coins1.length, amount1)); // Expected: 4
        System.out.println("Min coins needed: " + solution.coinChangeInMinCoins(coins1, amount1)); // Expected: 2
        
        // Test 2: Impossible case
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Test 2 - Coins: " + java.util.Arrays.toString(coins2) + ", Amount: " + amount2);
        System.out.println("Min coins needed: " + solution.coinChangeInMinCoins(coins2, amount2)); // Expected: -1
        
        System.out.println();
    }
    
    public static void testLIS() {
        System.out.println("=== Testing Longest Increasing Subsequence ===");
        
        dp.lis.Solution solution = new dp.lis.Solution();
        
        // Test 1: Basic LIS
        int[] arr1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Test 1 - Array: " + java.util.Arrays.toString(arr1));
        System.out.println("LIS length: " + solution.lis(arr1)); // Expected: 4
        
        // Test 2: All decreasing
        int[] arr2 = {5, 4, 3, 2, 1};
        System.out.println("Test 2 - Array: " + java.util.Arrays.toString(arr2));
        System.out.println("LIS length: " + solution.lis(arr2)); // Expected: 1
        
        System.out.println();
    }
    
    public static void testLCS() {
        System.out.println("=== Testing Longest Common Subsequence ===");
        
        dp.lcs.Solution solution = new dp.lcs.Solution();
        
        // Test 1: Basic LCS
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println("Test 1 - Text1: " + text1 + ", Text2: " + text2);
        System.out.println("LCS length: " + solution.lcs(text1, text2)); // Expected: 3
        
        // Test 2: No common subsequence
        String text3 = "abc";
        String text4 = "def";
        System.out.println("Test 2 - Text1: " + text3 + ", Text2: " + text4);
        System.out.println("LCS length: " + solution.lcs(text3, text4)); // Expected: 0
        
        System.out.println();
    }
    
    public static void testKnapsack() {
        System.out.println("=== Testing 0/1 Knapsack ===");
        
        dp.knapsack.Solution solution = new dp.knapsack.Solution();
        
        // Test 1: Basic knapsack
        int[] weights = {1, 3, 4, 5};
        int[] values = {1, 4, 5, 7};
        int capacity = 7;
        System.out.println("Test 1 - Weights: " + java.util.Arrays.toString(weights));
        System.out.println("Values: " + java.util.Arrays.toString(values));
        System.out.println("Capacity: " + capacity);
        System.out.println("Max value: " + solution.knapsack01(weights, values, weights.length, capacity)); // Expected: 9
        
        System.out.println();
    }
    
    public static void testSubsetSum() {
        System.out.println("=== Testing Subset Sum ===");
        
        dp.subset_sum.Solution solution = new dp.subset_sum.Solution();
        
        // Test 1: Basic subset sum
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        System.out.println("Test 1 - Array: " + java.util.Arrays.toString(arr) + ", Sum: " + sum);
        System.out.println("Subset exists: " + solution.subsetSum(arr, sum)); // Expected: true
        
        // Test 2: No subset exists
        int sum2 = 30;
        System.out.println("Test 2 - Array: " + java.util.Arrays.toString(arr) + ", Sum: " + sum2);
        System.out.println("Subset exists: " + solution.subsetSum(arr, sum2)); // Expected: false
        
        System.out.println();
    }
    
    public static void main(String[] args) {
        testCoinChange();
        testLIS();
        testLCS();
        testKnapsack();
        testSubsetSum();
        
        System.out.println("All DP tests completed!");
    }
}
