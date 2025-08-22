package dp;

import dp.palindrome_partition.Solution;
import java.util.List;
import java.util.ArrayList;

/**
 * Comprehensive test suite for Palindrome Partition solutions
 */
public class PalindromePartitionTest {
    
    public static void testBasicCases() {
        System.out.println("=== Testing Basic Palindrome Partition Cases ===");
        
        Solution solution = new Solution();
        
        // Test 1: Basic case
        String test1 = "aab";
        System.out.println("Test 1 - Input: " + test1);
        List<List<String>> result1 = solution.partition(test1);
        System.out.println("Result: " + result1);
        System.out.println("Expected: [[a, a, b], [aa, b]]");
        System.out.println("Correct: " + (result1.size() == 2));
        
        // Test 2: Single character
        String test2 = "a";
        System.out.println("\nTest 2 - Input: " + test2);
        List<List<String>> result2 = solution.partition(test2);
        System.out.println("Result: " + result2);
        System.out.println("Expected: [[a]]");
        System.out.println("Correct: " + (result2.size() == 1));
        
        // Test 3: All same characters
        String test3 = "aaa";
        System.out.println("\nTest 3 - Input: " + test3);
        List<List<String>> result3 = solution.partition(test3);
        System.out.println("Result: " + result3);
        System.out.println("Expected: [[a, a, a], [a, aa], [aa, a], [aaa]]");
        System.out.println("Correct: " + (result3.size() == 4));
        
        System.out.println();
    }
    
    public static void testEdgeCases() {
        System.out.println("=== Testing Edge Cases ===");
        
        Solution solution = new Solution();
        
        // Test 1: Empty string
        String test1 = "";
        System.out.println("Test 1 - Input: " + test1);
        List<List<String>> result1 = solution.partition(test1);
        System.out.println("Result: " + result1);
        System.out.println("Expected: []");
        System.out.println("Correct: " + (result1.size() == 0));
        
        // Test 2: Null string
        String test2 = null;
        System.out.println("\nTest 2 - Input: " + test2);
        List<List<String>> result2 = solution.partition(test2);
        System.out.println("Result: " + result2);
        System.out.println("Expected: []");
        System.out.println("Correct: " + (result2.size() == 0));
        
        // Test 3: No palindromes possible
        String test3 = "abc";
        System.out.println("\nTest 3 - Input: " + test3);
        List<List<String>> result3 = solution.partition(test3);
        System.out.println("Result: " + result3);
        System.out.println("Expected: [[a, b, c]]");
        System.out.println("Correct: " + (result3.size() == 1));
        
        System.out.println();
    }
    
    public static void testComplexCases() {
        System.out.println("=== Testing Complex Cases ===");
        
        Solution solution = new Solution();
        
        // Test 1: Longer string with multiple palindromes
        String test1 = "abba";
        System.out.println("Test 1 - Input: " + test1);
        List<List<String>> result1 = solution.partition(test1);
        System.out.println("Result: " + result1);
        System.out.println("Expected: [[a, b, b, a], [a, bb, a], [abba]]");
        System.out.println("Correct: " + (result1.size() == 3));
        
        // Test 2: String with overlapping palindromes
        String test2 = "aba";
        System.out.println("\nTest 2 - Input: " + test2);
        List<List<String>> result2 = solution.partition(test2);
        System.out.println("Result: " + result2);
        System.out.println("Expected: [[a, b, a], [aba]]");
        System.out.println("Correct: " + (result2.size() == 2));
        
        System.out.println();
    }
    
    public static void testOptimizedVersion() {
        System.out.println("=== Testing Optimized Version ===");
        
        Solution solution = new Solution();
        
        // Test 1: Compare basic case
        String test1 = "aab";
        System.out.println("Test 1 - Input: " + test1);
        
        List<List<String>> result1 = solution.partition(test1);
        List<List<String>> resultOptimized = solution.partitionOptimized(test1);
        
        System.out.println("Regular version: " + result1);
        System.out.println("Optimized version: " + resultOptimized);
        System.out.println("Results match: " + result1.equals(resultOptimized));
        
        // Test 2: Longer string
        String test2 = "aaa";
        System.out.println("\nTest 2 - Input: " + test2);
        
        List<List<String>> result2 = solution.partition(test2);
        List<List<String>> resultOptimized2 = solution.partitionOptimized(test2);
        
        System.out.println("Regular version: " + result2);
        System.out.println("Optimized version: " + resultOptimized2);
        System.out.println("Results match: " + result2.equals(resultOptimized2));
        
        System.out.println();
    }
    
    public static void performanceTest() {
        System.out.println("=== Performance Test ===");
        
        Solution solution = new Solution();
        
        // Test with a longer string
        String test = "aaaaaaaa";
        System.out.println("Testing with string: " + test);
        
        long startTime = System.currentTimeMillis();
        List<List<String>> result1 = solution.partition(test);
        long endTime = System.currentTimeMillis();
        long regularTime = endTime - startTime;
        
        startTime = System.currentTimeMillis();
        List<List<String>> result2 = solution.partitionOptimized(test);
        endTime = System.currentTimeMillis();
        long optimizedTime = endTime - startTime;
        
        System.out.println("Regular version time: " + regularTime + "ms");
        System.out.println("Optimized version time: " + optimizedTime + "ms");
        System.out.println("Results match: " + result1.equals(result2));
        System.out.println("Performance improvement: " + 
            String.format("%.2f", (double)regularTime / optimizedTime) + "x");
        
        System.out.println();
    }
    
    public static void main(String[] args) {
        testBasicCases();
        testEdgeCases();
        testComplexCases();
        testOptimizedVersion();
        performanceTest();
        
        System.out.println("All palindrome partition tests completed!");
    }
}
