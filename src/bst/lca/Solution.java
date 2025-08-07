package bst.lca;

public class Solution {
    Solution root;
    int val;
    Solution left, right;

    public Solution() {
        root = null;
    }

    public Solution(int val) {
        this.val = val;
        left = right = null;
    }

    public Solution addToBST(Solution root, int val) {
        if (root == null) {
            root = new Solution(val);
            return root;
        }

        if (root.val > val)
            root.left = addToBST(root.left, val);
        else root.right = addToBST(root.right, val);
        return root;
    }

    public void inorder(Solution root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // p must be smaller than q val wise
    public Solution lca(Solution root, Solution p, Solution q) {
        if (root == null) return null;

        if (root.val == p.val || root.val == q.val) return root;

        // as bst so can use val comparison
        if (root.val > p.val && root.val > q.val) return lca(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lca(root.right, p, q);

        // other wise val has diverged from here so root is lca
        return root;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.root = sol.addToBST(sol.root, 5);
        sol.root = sol.addToBST(sol.root, 7);
        sol.root = sol.addToBST(sol.root, 3);
        sol.root = sol.addToBST(sol.root, 6);
        sol.root = sol.addToBST(sol.root, 9);
        sol.inorder(sol.root);
        System.out.println();

        Solution lca = sol.lca(sol.root, sol.root.left, sol.root.right.left);
        System.out.println("LCA is " + lca.val);
        lca = sol.lca(sol.root, sol.root.right.left, sol.root.right.right);
        System.out.println("LCA is " + lca.val);
    }
}
