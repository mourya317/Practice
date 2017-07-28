package PriorityQ_n_Heaps;

/**
 * Created by mballa on 24-07-2017.
 */
public class MinHeap {
    int[] array;
    int capacity;
    int size;
    String heap_type;
    int FRONT = 1;

    public MinHeap(int maxsize)
    {
        this.capacity = maxsize;
        this.size = 0;
        array = new int[this.capacity + 1];
        array[0] = Integer.MIN_VALUE;
    }

    int leftchild(int i){
        //2*i is left child

        return 2*i;
    }

    int rightChild(int i){
        //2*i+1 is left child

        return 2*i+1;
    }

    int getParent(int i){

        return (i)/2;
    }

    int getMax(){
        if(size == 0)return -1;
        return array[0];
    }

    Boolean isLeaf(int i){
        if(i >= size/2 && i <=size){
            return true;
        }
        return  false;
    }

    private void swap(int fpos, int spos)
    {
        int tmp;
        tmp = array[fpos];
        array[fpos] = array[spos];
        array[spos] = tmp;
    }

    void minHeapify(int i){
        if(!isLeaf(i)){
            if(array[i] > array[leftchild(i)] || array[i] > array[rightChild(i)]){
                if(array[(leftchild(i))] < array[rightChild(i)]){
                    swap(i,leftchild(i));
                    minHeapify(leftchild(i));
                }else{
                    swap(i,rightChild(i));
                    minHeapify(rightChild(i));
                }
            }
        }
    }

    void insertMinHeap(int data){
        //we add the element at the end and then percolate up the element
      array[++size]=data;
      int current = size;
        while(array[current] < array[getParent(current)]){ // to form a max heap change to >
            swap(current,getParent(current));
            current=getParent(current);
        }
    }

    public void minHeap()
    {
        for (int pos = (size / 2); pos >= 1 ; pos--)
        {
            minHeapify(pos);
        }
    }

    int deleteMin(){
        int popped = array[FRONT]; // array starts with 1
        array[FRONT]=array[size--];
        minHeapify(FRONT);
        return popped;
    }

    public void print()
    {
        for (int i = 1; i <= size / 2; i++ )
        {
            System.out.print(" PARENT : " + array[i] + " LEFT CHILD : " + array[2*i]
                    + " RIGHT CHILD :" + array[2 * i  + 1]);
            System.out.println();
        }
    }

    public static void main(String...arg)
    {
        System.out.println("The Min Heap is ");
        MinHeap minHeap = new MinHeap(15);
        minHeap.insertMinHeap(5);
        minHeap.insertMinHeap(3);
        minHeap.insertMinHeap(17);
        minHeap.insertMinHeap(10);
        minHeap.insertMinHeap(84);
        minHeap.insertMinHeap(19);
        minHeap.insertMinHeap(6);
        minHeap.insertMinHeap(22);
        minHeap.insertMinHeap(9);
        minHeap.insertMinHeap(900);
        //minHeap.minHeap();

        minHeap.print();
        System.out.println("The Min val is " + minHeap.deleteMin());
        System.out.println("The Min val is " + minHeap.deleteMin());
        System.out.println("The Min val is " + minHeap.deleteMin());
        System.out.println("The Min val is " + minHeap.deleteMin());
        System.out.println("The Min val is " + minHeap.deleteMin());
        System.out.println("The Min val is " + minHeap.deleteMin());
        System.out.println("The Min val is " + minHeap.deleteMin());
        System.out.println("The Min val is " + minHeap.deleteMin());
        System.out.println("The Min val is " + minHeap.deleteMin());
        System.out.println("The Min val is " + minHeap.deleteMin());
        System.out.println("The Min val is " + minHeap.deleteMin());
        System.out.println("The Min val is " + minHeap.deleteMin());
        System.out.println("The Min val is " + minHeap.deleteMin());
    }
}

