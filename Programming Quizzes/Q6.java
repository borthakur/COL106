public class Heap {
    /**
     * DO NOT CHANGE THE FUNCTION DEFINITION
     * Write a routine to validate the heap properties for the above priority queue
     * @param x
     * @return
     */
     Boolean isHeap(HeapNode x){
         if(x==null) return true;
         if(x.iscor(x,x.getLeft())&& x.iscor(x,x.getRight())){
             return isHeap(x.getLeft())&&isHeap(x.getRight());
         }
         return false;
     }
     int height(HeapNode x){
         if(x==null)return 0;
         int m=height(x.getLeft());
         int n =height(x.getRight());
         if(m<n)m=n;
         return m+1;
     }
    Boolean isHeapValid(HeapNode x) {
        if(x == null) return true;
        // write your code here
        int num = x.countNodes(x);
        if(!x.isCompleteUntil(x,0,num)) return false;
        if(!isHeap(x))return false;
        
        HeapNode index=x;
        int h = height(x);
        int ind = -1;
        for(int i=1;i<h-1;i++){
            if(ind==-1){ind=1; index = index.getLeft();}
            else {ind=-1;index=index.getRight();}
        }
        if(ind==-1 && index.getLeft()==null) return false;
        if(ind==1 && index.getRight()==null) return false;
        
        return true;

    }
}

class HeapNode {
    
    public static int pri(int n){
    int j = 0;
    int k = 1;
    while( n>k){
        j++;
        k*=2;
    }
    
    int pr = 0;
    for(int i=j, pi = k;i>=0;i--,pi/=2){
        if(n>=pi){
            n=n-pi;
            pr++;
        }
    }
    return pr;
}



public static boolean iscor(HeapNode x, HeapNode y){
    if(y == null) return true;
    if(pri(x.getKey())>=pri(y.getKey())) return true;
    return false;
}





public int countNodes(HeapNode root){
    if(root==null) return 0;
    return(1+countNodes(root.getLeft())+countNodes(root.getRight()));
}


public boolean isCompleteUntil(HeapNode root, int index, int number_nodes){
    if(root==null) return true;
    if(index >= number_nodes) return false;
    return (isCompleteUntil(root.getLeft(),2*index+1,number_nodes) && isCompleteUntil(root.getRight(),2*index+2,number_nodes));
}


    
    private int key;
    private HeapNode left;
    private HeapNode right;

    public HeapNode(int key, HeapNode left, HeapNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public HeapNode(int key) {
        this.key = key;
        this.left = this.right = null;
    }

    public int getKey() {
        return key;
    }

    public HeapNode getLeft() {
        return left;
    }

    public HeapNode getRight() {
        return right;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setLeft(HeapNode left) {
        this.left = left;
    }

    public void setRight(HeapNode right) {
        this.right = right;
    }
}
