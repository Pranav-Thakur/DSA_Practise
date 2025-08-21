package trie;

import java.util.Arrays;

/**
 * Comprehensive test suite for Trie implementations
 */
public class TrieTest {
    
    public static void testTrieAdv() {
        System.out.println("=== Testing TrieAdv Implementation ===");
        
        TrieAdv trie = new TrieAdv();
        
        // Test 1: Basic insert and count
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");
        
        System.out.println("Words equal to 'apple': " + trie.countWordsEqualTo("apple")); // Expected: 1
        System.out.println("Words starting with 'app': " + trie.countWordsStartingWith("app")); // Expected: 3
        
        // Test 2: Insert duplicate and count
        trie.insert("apple");
        System.out.println("Words equal to 'apple' after duplicate: " + trie.countWordsEqualTo("apple")); // Expected: 2
        
        // Test 3: Erase word
        trie.erase("apple");
        System.out.println("Words equal to 'apple' after erase: " + trie.countWordsEqualTo("apple")); // Expected: 1
        System.out.println("Words starting with 'app' after erase: " + trie.countWordsStartingWith("app")); // Expected: 2
        
        // Test 4: Non-existent words
        System.out.println("Words equal to 'banana': " + trie.countWordsEqualTo("banana")); // Expected: 0
        System.out.println("Words starting with 'ban': " + trie.countWordsStartingWith("ban")); // Expected: 0
        
        System.out.println();
    }
    
    public static void testLongestPrefixWord() {
        System.out.println("=== Testing LongestPrefixWord Implementation ===");
        
        LongestPrefixWord solver = new LongestPrefixWord();
        
        // Test 1: Basic case
        String[] words1 = {"p", "pr", "pro", "probl", "problem", "pros", "process", "processor"};
        String result1 = solver.longestValidWord(words1);
        System.out.println("Test 1 - Input: " + Arrays.toString(words1));
        System.out.println("Result: " + result1); // Expected: "problem"
        
        // Test 2: All words are valid
        String[] words2 = {"a", "ab", "abc"};
        String result2 = solver.longestValidWord(words2);
        System.out.println("Test 2 - Input: " + Arrays.toString(words2));
        System.out.println("Result: " + result2); // Expected: "abc"
        
        // Test 3: No valid words
        String[] words3 = {"abc", "def", "ghi"};
        String result3 = solver.longestValidWord(words3);
        System.out.println("Test 3 - Input: " + Arrays.toString(words3));
        System.out.println("Result: " + result3); // Expected: ""
        
        // Test 4: Lexicographically smaller word should be chosen
        String[] words4 = {"a", "ab", "abc", "abd"};
        String result4 = solver.longestValidWord(words4);
        System.out.println("Test 4 - Input: " + Arrays.toString(words4));
        System.out.println("Result: " + result4); // Expected: "abc" (not "abd")
        
        System.out.println();
    }
    
    public static void main(String[] args) {
        testTrieAdv();
        testLongestPrefixWord();
        
        System.out.println("All tests completed!");
    }
}
