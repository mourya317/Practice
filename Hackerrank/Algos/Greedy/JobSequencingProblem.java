package Algos.Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;

/**
 * Created by mballa on 18-07-2017.
 */
public class JobSequencingProblem {

    public static void main(String[] args) {
        Integer[] startTimes = {0, 3, 1, 5, 5, 8};
        final ArrayList<Integer> start = new ArrayList<Integer>();
        start.add(2);
        start.add(1);
        start.add(2);
        start.add(1);
        start.add(3);
        //start.add();
        int[] finishTimes = {6, 4, 2, 9, 7, 9};
        final ArrayList<Integer> finish = new ArrayList<Integer>();
        finish.add(100);
        finish.add(19);
        finish.add(27);
        finish.add(25);
        finish.add(15);
        //finish.add(9);


        Collections.sort(start, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (finish.get(start.indexOf(o1)) < finish.get(start.indexOf(o2))) {
                    return 1;
                } else if (finish.get(start.indexOf(o1)) > finish.get(start.indexOf(o2))) {
                    return -1;
                }/*else{
                   if(o1>o2)return 1;
                    else if(o1<o2)return -1;
                       else return 0;
                }*/
                return 0;

            }
        });
        Collections.sort(finish,Collections.<Integer>reverseOrder());

        System.out.println("start:" + start);
        System.out.println("finish:" + finish);


        int[] result = new int[finish.size()];
        for(int i=0;i< finish.size();i++){
            result[i] = -1;
        }
        for(int i=0;i<start.size();i++){
            int k = start.get(i) - 1 ;
            for(int j = k;j>=0;j--){
                if(result[j] == -1){
                  result[j]=finish.get(i);
                    break;
                }
            }
        }

        for(int i:result) System.out.println("result:"+i);
    }


}
