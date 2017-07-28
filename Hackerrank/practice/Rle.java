package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Rle {
    public static void main (String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0 ){
            String s =  in.readLine();

            String str = rle(s);
            System.out.println(str);
        }
    }

    private static String rle(String s){
        int c =0;
        String result = "";
        int j=-1;
        for(int i = 0;i< s.length()  ; i++,j++){
            result+=s.charAt(i);
            while(j+1 < s.length() ){
                if(s.charAt(i) == s.charAt(j+1)){
                    j++;
                    c++;

                }else{
                    break;
                }
            }
            i=j;
            result+=c;
            c=1;
        }
        return result;
    }





}