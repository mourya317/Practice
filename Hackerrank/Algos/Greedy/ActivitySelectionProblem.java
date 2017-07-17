package Algos.Greedy;

import java.util.*;

/**
 * Created by mballa on 15-07-2017.
 *
 *Given n activities with start and finish times ,, find the max no of activities that
 * can be performed by a person , assuming only 1 activity at a time
 *if sorted array O(N) , if unsorted O(NlogN)
 */
public class ActivitySelectionProblem {
// sort the activities based on finish times and scan the array
    public static void main(String[] args){
     //   [0,3,1,5,5]
        Integer[] startTimes = {0,3,1,5,5,8};
        final ArrayList<Integer> start = new ArrayList<Integer>();
        start.add(0);
        start.add(3);
        start.add(1);
        start.add(5);
        start.add(5);
        start.add(8);
        int[] finishTimes = {6,4,2,9,7,9};
        final ArrayList<Integer> finish = new ArrayList<Integer>();
        finish.add(6);
        finish.add(4);
        finish.add(2);
        finish.add(9);
        finish.add(7);
        finish.add(9);


        Collections.sort(start, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(finish.get(start.indexOf(o1)) > finish.get(start.indexOf(o2))){
                    return 1;
                }else if(finish.get(start.indexOf(o1)) < finish.get(start.indexOf(o2))){
                    return -1;
                }/*else{
                   if(o1>o2)return 1;
                    else if(o1<o2)return -1;
                       else return 0;
                }*/
                return 0;

            }
        });
        Collections.sort(finish);

        System.out.println("start:"+start);
        System.out.println("finish:"+finish);

        int count=0;
        ArrayList activities = new ArrayList();
        activities.add(start.get(0));
        int prevFinishTime = finish.get(0);
        for (int a:start){
            if(count == 0 ){
                count++;
                continue;
            }
            if(a > prevFinishTime){
                activities.add(a);
                prevFinishTime = finish.get(count);
            }
            count++;
        }

        System.out.println("activities:"+activities);

    }

}
