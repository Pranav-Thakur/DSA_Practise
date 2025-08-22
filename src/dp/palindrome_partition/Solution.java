package dp.palindrome_partition;

import java.util.*;

/**
 * Palindrome Partitioning Solution
 * 
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * 
 * Time Complexity: O(n * 2^n) where n is the length of the string
 * Space Complexity: O(n) for recursion stack
 */
public class Solution {
    
    /**
     * Check if a substring is a palindrome
     * @param s The input string
     * @param start Starting index
     * @param end Ending index
     * @return true if the substring is a palindrome, false otherwise
     */
    private boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Recursive helper method to find all palindrome partitions
     * @param result List to store all valid partitions
     * @param currentPartition Current partition being built
     * @param s Input string
     * @param startIndex Current starting index
     */
    private void findPalindromePartitions(List<List<String>> result, 
                                        List<String> currentPartition, 
                                        String s, 
                                        int startIndex) {
        // Base case: if we've processed the entire string
        if (startIndex == s.length()) {
            result.add(new ArrayList<>(currentPartition));
            return;
        }

        // Try all possible ending positions for the current partition
        for (int endIndex = startIndex; endIndex < s.length(); endIndex++) {
            if (isPalindrome(s, startIndex, endIndex)) {
                // Add the palindrome substring to current partition
                currentPartition.add(s.substring(startIndex, endIndex + 1));
                // Recursively process the remaining string
                findPalindromePartitions(result, currentPartition, s, endIndex + 1);
                // Backtrack: remove the last added substring
                currentPartition.remove(currentPartition.size() - 1);
            }
        }
    }

    /**
     * Find all possible palindrome partitions of the given string
     * @param s Input string
     * @return List of all valid palindrome partitions
     */
    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        
        List<List<String>> result = new ArrayList<>();
        findPalindromePartitions(result, new ArrayList<>(), s, 0);
        return result;
    }

    /**
     * Optimized version using dynamic programming for palindrome checking
     * @param s Input string
     * @return List of all valid palindrome partitions
     */
    public List<List<String>> partitionOptimized(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        
        // Precompute palindrome substrings using DP
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || isPalindrome[j + 1][i - 1])) {
                    isPalindrome[j][i] = true;
                }
            }
        }
        
        List<List<String>> result = new ArrayList<>();
        findPalindromePartitionsOptimized(result, new ArrayList<>(), s, 0, isPalindrome);
        return result;
    }
    
    /**
     * Optimized recursive helper using precomputed palindrome matrix
     */
    private void findPalindromePartitionsOptimized(List<List<String>> result,
                                                 List<String> currentPartition,
                                                 String s,
                                                 int startIndex,
                                                 boolean[][] isPalindrome) {
        if (startIndex == s.length()) {
            result.add(new ArrayList<>(currentPartition));
            return;
        }
        
        for (int endIndex = startIndex; endIndex < s.length(); endIndex++) {
            if (isPalindrome[startIndex][endIndex]) {
                currentPartition.add(s.substring(startIndex, endIndex + 1));
                findPalindromePartitionsOptimized(result, currentPartition, s, endIndex + 1, isPalindrome);
                currentPartition.remove(currentPartition.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test case 1: Basic case
        String test1 = "aab";
        System.out.println("Test 1 - Input: " + test1);
        System.out.println("Result: " + sol.partition(test1));
        
        // Test case 2: Single character
        String test2 = "a";
        System.out.println("\nTest 2 - Input: " + test2);
        System.out.println("Result: " + sol.partition(test2));
        
        // Test case 3: All same characters
        String test3 = "aaa";
        System.out.println("\nTest 3 - Input: " + test3);
        System.out.println("Result: " + sol.partition(test3));
        
        // Test case 4: Optimized version
        System.out.println("\nTest 4 - Optimized version for 'aab':");
        System.out.println("Result: " + sol.partitionOptimized(test1));
    }
}