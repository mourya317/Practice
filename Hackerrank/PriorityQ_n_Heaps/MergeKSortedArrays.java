package PriorityQ_n_Heaps;

/**
 * Created by mballa on 24-07-2017.
 * Problem:we need to merge K sorted arrays having n elements each .
 * algo:
 * 1) create a min-heap of size k (we need to compare k elements at a time)
 * 2)* insert first element in each array into heap
 * 3) repeat below steps n*k times:
 * delete element from min-heap(gives min element of the k elements) and insert into output array
 * copy next element from the array in which the deleted element came from to root. if that array is empty make the root as infinity
 * and heapify the heap

 */
public class MergeKSortedArrays {
   static class HeapElement{
       int data;
       int i;//kth array element
       int j;//next element index in kth array
       HeapElement(int data,int i,int j){
           this.data=data;
           this.i=i;
           this.j=j;
       }

   }

    static class Heap{
        HeapElement[] heapElements;
        int size;
        int capacity;
        int FRONT =0 ;

        Heap(int capacity){
            this.capacity=capacity;
            this.size=0;
            this.heapElements = new HeapElement[this.capacity+1];
            HeapElement tmp = new HeapElement(Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
            //heapElements[0]=tmp;
        }

        void swap(int i,int j){
            HeapElement tmp = heapElements[i];
            heapElements[i]=heapElements[j];
            heapElements[j]=tmp;
        }

        void minHeapify(int i){
            if(!isLeaf(i)){
                if(heapElements[i].data > heapElements[leftchild(i)].data || heapElements[i].data > heapElements[rightChild(i)].data){
                    if(heapElements[(leftchild(i))].data < heapElements[rightChild(i)].data){
                        swap(i,leftchild(i));
                        minHeapify(leftchild(i));
                    }else{
                        swap(i,rightChild(i));
                        minHeapify(rightChild(i));
                    }
                }
            }
        }

        void insert(int data,int k,int j){
            HeapElement tmp = new HeapElement(data,k,j);
            heapElements[size++]=tmp;
            int current = size-1;
            while(heapElements[current].data < heapElements[getParent(current)].data){
                swap(current,getParent(current));
                current=getParent(current);
            }
        }

        HeapElement delete(){
            HeapElement popped = heapElements[FRONT];
            heapElements[FRONT]=heapElements[size--];
            minHeapify(FRONT);
            return popped;
        }

        Boolean isLeaf(int i){
            if(i >= (size-1)/2 && i<=size){
                return true;
            }
            return  false;
        }

        int leftchild(int i){
            //2*i is left child

            return 2*i+1;
        }

        int rightChild(int i){
            //2*i+1 is left child

            return 2*i+2;
        }

        int getParent(int i){

            return (i-1)/2;
        }

        HeapElement getMin(){
            return heapElements[FRONT];
        }

        // to replace root with new node x and heapify() new root
        void replaceMin(HeapElement x) { heapElements[FRONT] = x;  minHeapify(FRONT); }



    }

   static int[] mergeKSortedArrays(int[][] arrays,int k,int n){
        int[] output = new int[n*k];
        int o=0;
        //create a min heap of size k
        Heap heap = new Heap(k*n);
        //insert first elements from each of k arrays
        for(int i=0;i<k;i++){
            int next=Integer.MAX_VALUE;
            if(i<n-1)next=i+1;

            heap.insert(arrays[i][0],i,1);
        }

        for(int i=0;i<n*k;i++){
            int nextElementArr=0;
            //get first min element
            HeapElement min = heap.getMin();
            HeapElement root = new HeapElement(min.data,min.i,min.j);
            output[o++]=min.data;

            //insert next element
            if(root.j < n){
                //heap.delete();
                root.data = arrays[min.i][min.j];
                root.j+=1;
            }else{
                //heap.delete();
                root.data = Integer.MAX_VALUE;
                //min.j+=1;
                //heap.insert(arrays[min.i][min.j],min.i,Integer.MAX_VALUE);
            }
            //replace root element annd heapify it
            heap.replaceMin(root);
           // heap.insert(root.data,root.i,root.j);
        }
       return output;

    }

    public static void main(String[] args){
        int[][] in = {{1,3,9,64},
                {5,91,99,199},
                {11,92,95,97}};
        int[] out = mergeKSortedArrays(in,3,4);
        for(int i=0;i<out.length;i++){
            System.out.print(out[i]+",");
        }
    }


}
