package practice;

import java.util.*;
import java.lang.*;
import java.io.*;

class Intersection {
    public static void main (String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		/*int T = Integer.parseInt(in.readLine());
		while(T-- > 0 ){
		    String s =  in.readLine();
		    
		    String str = rle(s);
		    System.out.println(str);
		}*/
        String[] a = in.readLine().split(" ");
        String[] b = in.readLine().split(" ");
        ArrayList<String> res = solve(a,b);
        for(String s : res){
            System.out.print(s+" " );
        }
    }

    private static ArrayList<String> solve(String[] a,String[] b){

        ArrayList<String> result = new ArrayList<>();
        //insert a into hm
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        for(int i = 0; i< a.length - 1  ; i++){
            if(!hm.containsKey(a[i])){
                hm.put(a[i],0);
            }
            hm.put(a[i],hm.get(a[i])+1);
        }

        for(int j=0;j< b.length - 1;j++){
            if(hm.containsKey(b[j])){
                if(hm.get(b[j]) > 0)result.add(b[j]);
                hm.put(b[j],hm.get(b[j])-1);
            }

        }

        return result ;
    }





}