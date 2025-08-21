package trie;

/*
 * Trie Implementation and Advanced Operations

Implement "TRIE” data structure from scratch with the following functions.

Trie(): Initialize the object of this “TRIE” data structure.
insert(“WORD”): Insert the string “WORD” into this “TRIE” data structure.
countWordsEqualTo(“WORD”): Return how many times this “WORD” is present in this “TRIE”.
countWordsStartingWith(“PREFIX”): Return how many words are there in this “TRIE” that have the string “PREFIX” as a prefix.
erase(“WORD”): Delete one occurrence of the string “WORD” from the “TRIE”.
 * 
 */

class TrieAdv {
    class TrieNode {
        public boolean end;
        public TrieNode[] childs;

        public TrieNode() {
            this(26);
        }

        public TrieNode(int childNum) {
            end = false;
            childs = new TrieNode[childNum];
        }
    };

    private int childNum;
    private TrieNode root;

    public TrieAdv() {
        this.childNum = 26;
        this.root = new TrieNode(childNum);    
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int ind = c - 'a';
            if (curr.childs[ind] == null) 
                curr.childs[ind] = new TrieNode();
            curr = curr.childs[ind];
        }
        curr.end = true;
    }

    /**
     * Recursively count all words in the subtree starting from the given node
     * @param curr Current trie node
     * @return Total count of words in the subtree
     */
    private int countWordsInSubtree(TrieNode curr) {
        if (curr == null) return 0;
        int count = 0;
        if (curr.end) count = 1;

        for (int i = 0; i < curr.childs.length; i++)
            count += countWordsInSubtree(curr.childs[i]);
        
        return count;
    }

    public int countWordsEqualTo(String word) {
        TrieNode curr = root;
        int ans = 0;
        for (char c : word.toCharArray()) {
            int ind = c - 'a';
            if (curr.childs[ind] == null) return ans;
            curr = curr.childs[ind];
        }

        if (curr == null) return ans;
        return countWordsInSubtree(curr);
    }

    public int countWordsStartingWith(String prefix) {
        TrieNode curr = root;
        int ans = 0;
        for (char c : prefix.toCharArray()) {
            int ind = c - 'a';
            if (curr.childs[ind] == null) return ans;
            curr = curr.childs[ind];
        }

        if (curr == null) return ans;
        return countWordsInSubtree(curr);
    }

    public void erase(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int ind = c - 'a';
            if (curr.childs[ind] == null) return;
            curr = curr.childs[ind];
        }
        curr.end = false;
    }

    public static void main(String[] args) {
        TrieAdv trie = new TrieAdv();
        trie.insert("apple");
        System.out.println(trie.countWordsEqualTo("apple"));  // return 1
        trie.insert("app");
        System.out.println(trie.countWordsStartingWith("app")); // return 2
        trie.erase("apple");
        System.out.println(trie.countWordsStartingWith("app"));   // return 1
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 */