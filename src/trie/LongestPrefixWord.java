package trie;

public class LongestPrefixWord {
    /**
     * Trie node class for storing characters and marking word endings
     */
    class Trie {
        public boolean isEndOfWord;
        public Trie[] children;
        
        public Trie() {
            isEndOfWord = false;
            children = new Trie[26];
        }
    };
    
    private Trie root;
    /**
     * Check if a word is valid (all prefixes exist in the trie)
     * @param word The word to validate
     * @return true if all prefixes of the word exist in the trie
     */
    public boolean isValid(String word){
        Trie curr = root;
        for(int i = 0; i < word.length(); i++){
            int idx = word.charAt(i) - 'a';
            curr = curr.children[idx];
            if(curr == null || !curr.isEndOfWord){
                return false;
            }
        }
        return true;
    }
    
    public String longestValidWord(String[] words) {
        // code here
        root = new Trie();
        Trie curr = root;
        for (String w : words) {
            curr = root;
            for (int i = 0; i < w.length(); i++) {
                int ind = w.charAt(i) - 'a';
                if (curr.children[ind] == null)
                    curr.children[ind] = new Trie();
                curr = curr.children[ind];
            }
            curr.isEndOfWord = true;
        }
        
        String res = "";
        for(String word : words){
             if(isValid(word)){
                 if(res.length() < word.length()){
                     res = word;
                 }else if(res.length() == word.length()){
                     if(res.compareTo(word) > 0){
                         res = word;
                     }
                 }
             }
        }
        
        return res ;
        
    }

    public static void main(String[] args) {
        LongestPrefixWord sol = new LongestPrefixWord();
        String[] words = {"p", "pr", "pro", "probl", "problem", "pros", "process", "processor"};
        System.out.println(sol.longestValidWord(words));
    }
}