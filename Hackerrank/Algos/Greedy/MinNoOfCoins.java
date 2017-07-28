package Algos.Greedy;

import java.util.Arrays;

/**
 * Created by mballa on 27-07-2017.
 * Problem : Min no of coins required to get V sum .
 * input is the demoninations of available coins
 */
public class MinNoOfCoins {
    public static void main(String[] args){
        int[] in = {1,5,10,20,50,100,500,1000};
        int V = 4;
        //get max denomination closest to V
        int maxDen = getMaxDenomination(in,V);
        int temp=0;
        int res=0;
        for(int i= in.length-1;i>=0;i--){
            temp = V/in[i];
            res+=temp;
            V-=(temp*in[i]);
        }
        System.out.print("maxDen:"+res);
    }



    private static int getMaxDenomination(int[] in, int v) {
        //sort the array
        Arrays.sort(in);
        //use binary search
        int l = 0; int r = in.length;
        int result=Integer.MAX_VALUE;
        int temp=0;
        int minDistSoFar=Integer.MAX_VALUE;

        while(l<r){

            int  mid = (l+r)/2;
            int e = in[mid];
            if(e==v)return v;
            if(v<e){
                r = mid-1;
                result = in[r];
            }else{
                l = mid+1;
                result = in[l];
            }
            result=Math.min(in[l],e);
            //temp = result - v;
            /*if(minDistSoFar > result-v){
                minDistSoFar = result-v;
                result=Math.min(in[l],e);
            }*/

        }
        return  result;
    }

}
