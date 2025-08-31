/**
 * Bit Array Implementation for Efficient Bit Counting
 * 
 * This class provides an optimized solution for counting set bits in numbers
 * using precomputed bit counts and efficient bit manipulation techniques.
 * 
 * Time Complexity: O(1) for queries after preprocessing
 * Space Complexity: O(SIZE) for the precomputed array
 */
public class BitArr {
    private static final int SIZE = 100007;
    private static final int[] BIT_COUNT_ARRAY = new int[SIZE];

    /**
     * Count the number of set bits in a number up to a given limit
     * @param number The number to count bits in
     * @param limit The limit up to which to count
     * @return Number of set bits
     */
    private static int countSetBits(int number, int limit) {
        int count = 0;
        while (number > 0) {
            if (number < limit) {
                return count + BIT_COUNT_ARRAY[number];
            }
            int lastBit = number & 1;
            if (lastBit > 0) {
                count++;
            }
            number >>= 1;
        }
        return count;
    }

    /**
     * Precompute bit counts for all numbers up to SIZE
     * This static block runs when the class is loaded
     */
    static {
        for (int i = 1; i < SIZE; i++) {
            BIT_COUNT_ARRAY[i] = countSetBits(i, i - 1);
        }

        // Print first 20 values for verification
        System.out.print("Precomputed bit counts (first 20): ");
        for (int i = 0; i < 20; i++) {
            System.out.print(BIT_COUNT_ARRAY[i] + " ");
        }
        System.out.println();
    }

    /**
     * Process queries to find the smallest number with given bit count
     * @param queries Array of queries to process
     */
    private static void processQueries(int[] queries) {
        int queryCount = queries.length;
        for (int i = 0; i < queryCount; i++) {
            int targetBitCount = queries[i];
            int result = findSmallestNumberWithBitCount(targetBitCount);
            System.out.println("Query " + (i + 1) + ": " + targetBitCount + " bits -> Number: " + result);
        }
    }

    /**
     * Find the smallest number that has the given number of set bits
     * @param targetBitCount The target number of set bits
     * @return The smallest number with the target bit count
     */
    private static int findSmallestNumberWithBitCount(int targetBitCount) {
        int number = 1;
        while (targetBitCount > 0) {
            targetBitCount -= BIT_COUNT_ARRAY[number++];
        }

        if (targetBitCount == 0) {
            return number;
        } else {
            return number - 1;
        }
    }

    /**
     * Alternative implementation using binary search for better performance
     * @param targetBitCount The target number of set bits
     * @return The smallest number with the target bit count
     */
    public static int findSmallestNumberWithBitCountOptimized(int targetBitCount) {
        int left = 1;
        int right = SIZE - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int bitCount = BIT_COUNT_ARRAY[mid];
            
            if (bitCount >= targetBitCount) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== Bit Array Implementation Test ===");
        
        // Test queries: find smallest numbers with 4, 7, 11, and 12 set bits
        int[] queries = {4, 7, 11, 12};
        System.out.println("Processing queries: " + java.util.Arrays.toString(queries));
        
        processQueries(queries);
        
        // Test optimized version
        System.out.println("\n=== Testing Optimized Version ===");
        for (int query : queries) {
            int result = findSmallestNumberWithBitCountOptimized(query);
            System.out.println("Optimized query " + query + " bits -> Number: " + result);
        }
        
        // Performance comparison
        System.out.println("\n=== Performance Test ===");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            findSmallestNumberWithBitCount(10);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Regular version time: " + (endTime - startTime) + "ms");
        
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            findSmallestNumberWithBitCountOptimized(10);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Optimized version time: " + (endTime - startTime) + "ms");
    }
}