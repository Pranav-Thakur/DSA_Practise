package dp.lcs;

import java.util.Arrays;
import java.util.Iterator;

public class Solution {
    private final int[][] dp;

    public Solution() {
        dp = new int[1001][1001];
        Arrays.fill(dp[0], 0);
        for (int i = 0; i < 1001; i++) dp[i][0] = 0;
    }

    public int recur(String X, String Y, int n, int m) {
        if (n <= 0 || m <= 0) return 0;

        if (X.charAt(n-1) == Y.charAt(m-1)) return 1 + recur(X, Y, n-1, m-1);

        return Math.max(recur(X, Y, n, m-1), recur(X, Y, n-1, m));
    }

    public int memoRecur(String X, String Y, int n, int m) {
        if (n <= 0 || m <= 0) return 0;
        if (dp[n][m] != -1) return dp[n][m];

        if (X.charAt(n-1) == Y.charAt(m-1))
            return dp[n][m] = 1 + memoRecur(X, Y, n-1, m-1);

        return dp[n][m] = Math.max(memoRecur(X, Y, n, m-1), memoRecur(X, Y, n-1, m));
    }

    public int lcs(String X, String Y) {
        int n = X.length(), m = Y.length();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (X.charAt(i-1) == Y.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        return dp[n][m];
    }

    public void printLcs(String X, String Y) {
        int n = X.length(), m = Y.length();
        int k = dp[n][m];
        char[] str = new char[k];

        while (n > 0 && m > 0) {
            if (X.charAt(n-1) == Y.charAt(m-1)) {
                str[k-1] = X.charAt(n-1);
                k--; n--; m--;
            } else {
                int x = dp[n-1][m], y = dp[n][m-1];
                if (dp[n][m] == x) n--;
                else m--;
            }
        }

        System.out.println("The LCS for (" + X + ", " + Y + ") is " + Arrays.toString(str));
    }

    public int shortestCommonSuperSequence(String a, String b) {
        int lcsLen = lcs(a, b);
        return (a.length() + b.length() - lcsLen);
    }

    public void printSCS(String a, String b) {
        lcs(a, b);
        int i = a.length(), j = b.length();

        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (a.charAt(i-1) == b.charAt(j-1)) {
                sb.append(a.charAt(i-1));
                i--; j--;
            } else if (dp[i-1][j] > dp[i][j-1]) {
                sb.append(a.charAt(i-1));
                i--;
            } else {
                sb.append(b.charAt(j-1));
                j--;
            }
        }

        while (i > 0) {
            sb.append(a.charAt(i-1));
            i--;
        }

        while (j > 0) {
            sb.append(b.charAt(j-1));
            j--;
        }

        System.out.println("The SCS for the strings is " + sb.reverse());
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String X = "abcdgh";
        String Y = "abedfhr";

        System.out.println("The Solution for LCS is " + sol.recur(X, Y, X.length(), Y.length()));
        System.out.println("The Solution for LCS is " + sol.lcs(X, Y));
        sol.printLcs(X, Y);

        System.out.println("The Solution for SCS is " + sol.shortestCommonSuperSequence(X, Y));
        sol.printSCS(X, Y);
    }
}
