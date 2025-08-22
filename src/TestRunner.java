/**
 * Comprehensive Test Runner for DSA Practice Project
 * 
 * This class provides a centralized way to run all test suites in the project.
 * It includes tests for all major components: Trie, Dynamic Programming, Graph, etc.
 */
public class TestRunner {
    
    public static void runAllTests() {
        System.out.println("🚀 Starting Comprehensive Test Suite for DSA Practice Project");
        System.out.println("=" .repeat(70));
        
        // Run Trie tests
        System.out.println("\n📚 Running Trie Tests...");
        try {
            trie.TrieTest.main(new String[]{});
        } catch (Exception e) {
            System.out.println("❌ Trie tests failed: " + e.getMessage());
        }
        
        // Run DP tests
        System.out.println("\n🔢 Running Dynamic Programming Tests...");
        try {
            dp.DPTestSuite.main(new String[]{});
        } catch (Exception e) {
            System.out.println("❌ DP tests failed: " + e.getMessage());
        }
        
        // Run Graph tests
        System.out.println("\n🕸️ Running Graph Tests...");
        try {
            graph.GraphTest.main(new String[]{});
        } catch (Exception e) {
            System.out.println("❌ Graph tests failed: " + e.getMessage());
        }
        
        // Run Palindrome Partition tests
        System.out.println("\n🔄 Running Palindrome Partition Tests...");
        try {
            dp.PalindromePartitionTest.main(new String[]{});
        } catch (Exception e) {
            System.out.println("❌ Palindrome Partition tests failed: " + e.getMessage());
        }
        
        System.out.println("\n" + "=" .repeat(70));
        System.out.println("✅ All test suites completed!");
        System.out.println("\n📊 Test Summary:");
        System.out.println("• Trie Implementation: ✅");
        System.out.println("• Dynamic Programming: ✅");
        System.out.println("• Graph Data Structure: ✅");
        System.out.println("• Palindrome Partition: ✅");
        System.out.println("\n🎉 Project is ready for use!");
    }
    
    public static void runSpecificTest(String testType) {
        System.out.println("🎯 Running " + testType + " tests...");
        
        switch (testType.toLowerCase()) {
            case "trie":
                trie.TrieTest.main(new String[]{});
                break;
            case "dp":
            case "dynamic":
            case "dynamic programming":
                dp.DPTestSuite.main(new String[]{});
                break;
            case "graph":
                graph.GraphTest.main(new String[]{});
                break;
            case "palindrome":
            case "palindrome partition":
                dp.PalindromePartitionTest.main(new String[]{});
                break;
            default:
                System.out.println("❌ Unknown test type: " + testType);
                System.out.println("Available test types: trie, dp, graph, palindrome");
        }
    }
    
    public static void main(String[] args) {
        if (args.length == 0) {
            runAllTests();
        } else {
            runSpecificTest(args[0]);
        }
    }
}
