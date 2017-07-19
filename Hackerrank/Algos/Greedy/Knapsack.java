package Algos.Greedy;

import java.util.*;

/**
 * Created by mballa on 19-07-2017.
 * Given an arrays of weights and its values and capacity of Knapsack. O(NLOGN)
 * if its is 0-1 knapsack problem we do not consider the factional part
 */
public class Knapsack {
    static class Knap{
        int w;
        int v;

        public Knap(int i, int i1) {
            w=i;
            v=i1;
        }
    }

    public static void main(String[] args){
        //Sort them according to value/weight ratio in descending order
        Knap[] knap;
        int capacity = 4;
        double c=(double) capacity;
        knap = new Knap[5];
            knap[0]=new Knap(1,8);
            knap[1]=new Knap(2,4);
            knap[2]=new Knap(3,0);
            knap[3]=new Knap(2,3);
            knap[4]=new Knap(2,5);

        Arrays.sort(knap, new Comparator<Knap>() {
            @Override
            public int compare(Knap o1, Knap o2) {
                if((o1.v/o1.w) > (o2.v/o2.w))return -1;
                else if((o1.v/o1.w) < (o2.v/o2.w))return 1;
                return 0;
            }
        });
        for(int i=0;i<knap.length;i++)
        System.out.println(knap[i].w+"--"+knap[i].v);
        double result=0;
        double temp =0;

        for(int i=0;i<knap.length;i++){
            if(c==0)break;
            if(c >= knap[i].w){
                result+=knap[i].v;
                c-=knap[i].w;
            }else{
                temp = (c/(double)knap[i].w)*(knap[i].v);
                result= result + temp;
                break;
                //c = c-(c/(double)knap[i].w);
            }

        }
        System.out.println("result:"+result);




    }
}
