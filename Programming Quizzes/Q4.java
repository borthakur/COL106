public class Balance {

    /**
     * Do not change the function signature
     * @param xyz - contains references to the three nodes x, y, and z in increasing order of their key values
     * @param Ts -  stores references to the roots of the subtrees T1, T2, T3, and T4 in increasing order of the keys of their respective root nodes
     * @param numTs - gives the number of non-null values
     * @return reference to the top node of the rebalanced subtree
     * 
     *
     */
     
    static int hi(TreeNode n){
        if(n==null){
            return -1;
        }
        return n.getHeight();
    }
     
     
    TreeNode rebalance(TreeNode[] xyz, TreeNode[] Ts, int numTs) {
        // Complete the code
        
        TreeNode z = xyz[0];
        TreeNode y = xyz[1];
        TreeNode x = xyz[2];
        
        z.setRight(y);
        y.setRight(x);
        
        z.setLeft(null);
        y.setLeft(null);
        x.setLeft(null);
        x.setRight(null);
        
        int i = 0;
        
        while(i<numTs){
            if(Ts[i].getKey()<z.getKey()){
                z.setLeft(Ts[i]);
                i++;
                continue;
            }
            if(Ts[i].getKey()<y.getKey()){
                y.setLeft(Ts[i]);
                i++;
                continue;
            }
            if(Ts[i].getKey()<x.getKey()){
                x.setLeft(Ts[i]);
                i++;
                continue;
            }
            x.setRight(Ts[i]);
            i++;
        }
        
        int temp = hi(x.getLeft());
        int temp2 = hi(x.getRight());
        if(temp<temp2) temp = temp2;
        x.setHeight(temp+1);
        
        temp=hi(y.getLeft());
        if(temp<hi(x)) temp = hi(x);
        y.setHeight(temp+1);
        
        
        temp=hi(z.getLeft());
        if(temp<hi(y)) temp = hi(y);
        z.setHeight(temp+1);
        
        
        
        
        if(hi(y)-hi(z.getLeft())>1){
            
            z.setRight(y.getLeft());
            y.setLeft(z);
            return y;
            
        }
        
        
        return z;
        

    }
}

// Do not change the code after this line
class TreeNode {
    private int key;
    private int height;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int key, int height) {
        this.key = key;
        this.height = height;
        this.left = null;
        this.right = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
