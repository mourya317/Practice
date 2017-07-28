package Algos.Greedy;

import java.util.PriorityQueue;

/**
 * Created by mballa on 19-07-2017.
 */
public class Test {
    public static void main(String[] args){
        String scopeItems = "hi,hello 'E' world , awesome";
        String treatedScopes = "'" + scopeItems.replaceAll("'","\\\\'").replaceAll(",", "','") + "'";
        System.out.println("rsult:"+treatedScopes);
    }
}
