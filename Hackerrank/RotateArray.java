import java.util.*;
import java.lang.*;
import java.io.*;

class RotateArray {
    public static void main (String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0 ){
            String[] meta =  in.readLine().split(" ");
            int l = Integer.parseInt(meta[0]);
            int d = Integer.parseInt(meta[1]);
            String[] arr = in.readLine().split(" ");
            rotate(arr,l,d);
            print(arr);
        }
    }

    private static void rotate(String[] arr,int len , int d){
        for(int i=0;i<d;i++){
            rotateOneByOne(arr,len);
        }
    }

    private static void rotateOneByOne(String[] arr , int l){
        String temp = arr[0];
        int i=0;
        for( i=0;i<l-1;i++){
            arr[i]=arr[i+1];
        }
        arr[i]=temp;

    }


    private static void print(String[] arr){
        for(int i = 0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();

    }
}