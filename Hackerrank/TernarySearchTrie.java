import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mballa on 10-05-2017.
 */
public class TernarySearchTrie {
    private ArrayList<String> al;
    private Node root;

    private static class Node {
        char c;
        boolean isEnd;
        Node middle, left, right;

        public Node(char c) {
            this.c = c;
            this.isEnd = false;
            this.middle = null;
            this.left = null;
            this.right = null;
        }
    }

    public TernarySearchTrie() {
        root = null;
    }

    //insert a word
    public void insert(String word) {
        root= insert(root,word.toCharArray(),0);
    }

    public Node insert(Node r,char[] word,int pos) {
        if(r == null)
            r = new Node(word[pos]);

        if(word[pos] < r.c){
            r.left = insert(r.left,word,pos);
        }else if(word[pos] > r.c){
            r.right = insert(r.right,word,pos);
        }else{
            if(pos+1 < word.length){
                r.middle = insert(r.middle,word,pos+1);
            }else{
                r.isEnd = true;
            }
        }
            return r;
    }



    public boolean search(String word){
        return search(root,word.toCharArray(),0);
    }

    public boolean search(Node r,char[] word,int pos){
        if(r==null)return false;
        if(word[pos] < r.c){
           return search(r.left,word,pos);
        }else if(word[pos] > r.c){
            return search(r.right,word,pos);
        }else{
            if(r.isEnd && pos == word.length -1){
                return true;
            }else if(pos == word.length -1){
                return false ; //only full word search
            }else{
                return search(r.middle,word,pos+1);
            }
        }
    }

    public ArrayList<String> printAllstrings(){
        al = new ArrayList<String>();
        traverse(root,"");
        return al;
    }

    //traverse all the nodes below a given node(prefix)
    public ArrayList<String> collect(Node r,String prefix){
        al = new ArrayList<String>();
        traverse(r,prefix);
        return al;
    }

   public void traverse(Node r,String str){
       if(r !=null){ //inorder traversal gives sorted list of strings
           traverse(r.left,str);
           str+=r.c;
           if(r.isEnd){
               //add to list
               al.add(str);
           }
           traverse(r.middle,str);
           str=str.substring(0,str.length()-1);
           traverse(r.right,str);
       }
   }


    public void delete(String word){
        delete(root,word.toCharArray(),0);
    }


    public void delete(Node r,char[] word,int pos){
        if(r == null) return ;
        if(word[pos] < r.c){
            delete(r.left,word,pos);
        }else if(word[pos] > r.c){
            delete(r.right,word,pos);
        }else{
            if(r.isEnd &&   pos == word.length- 1){
                r.isEnd=false;
            }else if(pos +1 < word.length -1 ){
                delete(r.middle,word,pos+1);
            }
        }
    }

    //get subtrie of a given prefix
    public Node get(Node r,char[] prefix,int pos){
        if(r==null)return null;
        if(prefix.length < 1)return null;
        char c = prefix[pos];
        if(c < r.c)return get(r.left,prefix,pos);
        else if(c > r.c)return get(r.right,prefix,pos);
        else if(pos < prefix.length - 1)return get(r.middle,prefix,pos+1);
        else return r;
    }

    //match all keys with prefix(partial matches also- auto completion)
    public ArrayList<String> getKeysWithPrefix(String prefix){
        if(prefix.length() < 1)return null;
        ArrayList<String> keys = new ArrayList<>();
        Node r = get(root,prefix.toCharArray(),0);
        if(r == null)return null;
        if(r.isEnd)keys.add(prefix); //if prefix is a complete word
        keys.addAll(collect(r.middle,prefix));
        return keys;
    }

    //get all strings that match a pattern   with ? as wildcard char
    public ArrayList<String> getKeysThatMatch(String pattern){
        ArrayList<String> keys = new ArrayList<>();
        collectPatternMatch(root,keys,"",pattern,0);
        return keys;
    }

    private void collectPatternMatch(Node r,ArrayList<String> list, String prefix , String pattern, int pos) {
        if(r==null)return  ;
        char c = pattern.charAt(pos);
        if(c == '?' || c < r.c) collectPatternMatch(r.left,list,prefix,pattern,pos);
        if(c == '?' || c == r.c) {
            if(pos == pattern.length() -1 && r.isEnd) list.add(prefix+r.c);
            if(pos < pattern.length()-1){
                collectPatternMatch(r.middle,list,prefix+r.c,pattern,pos+1);
            }
        }
        if(c == '?' || c > r.c) collectPatternMatch(r.right,list,prefix,pattern,pos);
    }


    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        /* Creating object of TernarySearchTree */
        TernarySearchTrie tst = new TernarySearchTrie();
        System.out.println("Ternary Search Tree Test\n");

        char ch;
        /*  Perform tree operations  */
        do
        {
            System.out.println("\nTernary Search Tree Operations\n");
            System.out.println("1. insert word");
            System.out.println("2. search word");
            System.out.println("3. delete word");
            System.out.println("4. print all strings");
            System.out.println("5. Get all strings with prefix");
            System.out.println("6. Get all strings which matches pattern");

            int choice = scan.nextInt();
            switch (choice)
            {
                case 1 :
                    System.out.println("Enter word to insert");
                    tst.insert( scan.next() );
                    break;
                case 2 :
                    System.out.println("Enter word to search");
                    System.out.println("Search result : "+ tst.search( scan.next() ));
                    break;
                case 3 :
                    System.out.println("Enter word to delete");
                    tst.delete( scan.next() );
                    break;
                case 4 :
                    System.out.println("list strings : "+ tst.printAllstrings() );
                    for (String s:tst.printAllstrings()){
                        System.out.println(s);
                    }
                    break;
                case 5 :
                    System.out.println("Enter prefix:");
                    for (String s:tst.getKeysWithPrefix(scan.next())){
                        System.out.println(s);
                    }
                    break;
                case 6 :
                    System.out.println("Enter pattern to match:");
                    for (String s:tst.getKeysThatMatch(scan.next())){
                        System.out.println(s);
                    }
                    break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            //System.out.println(tst);

            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');
    }
}
