
/**
 * Created by mballa on 09-05-2017.
 */
//O(L) time complexity for search and insert
//trie node  -- it has data and collection of childnodes and marker to indicate End of string
    class TrieNode{
    TrieNode[] child ;
    Boolean is_end_of_string;
    public TrieNode(){
        this.child =  new TrieNode[26];
    }
}
public class Trie {
    private TrieNode root;
    public Trie(){
        root = new TrieNode();
    }

    //insert a word into trie
    public void insert(String word){
        TrieNode p = root;
        for(int i =0 ;i<word.length();i++){
            char c = word.charAt(i);
            int index = c-'a';
            if(p.child[index] == null){
                //create a child with data
                TrieNode temp  =  new TrieNode();
                p.child[index] = temp;
                p=temp;
            }else{
                p=p.child[index];
            }
            p.is_end_of_string = true;
        }
    }

    //search a word in trie
    public boolean search(String word){
        TrieNode p = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            int index = c-'a';
            if(p.child[index]==null){
                return false;
            }else{
                p = p.child[index];
            }
        }
        if(p == root) return false;
        return p.is_end_of_string;
    }


    //returns true if any ele with prefix -- same as search
    public boolean startsWith(String prefix){
        return  search(prefix);
    }

}
