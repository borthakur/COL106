public class Convert {
    /**
     * DO NOT CHANGE THE FUNCTION DEFINITION
     * You have to write a recursive routine MWtoRB to convert a Multiway (2-3-4) tree to a red-black tree.
     * @param mw_node
     * @return
     */
    public RBTree MWtoRB(MWTree mw_node) {
        // write your code here
        
        if (mw_node==null) return null;
        
        RBTree x = new RBTree(0,0);
        
        int n = mw_node.getNumKeys();
        
        if(n==3){
            x.setKey(mw_node.getKey(1));
        }
        else{
            x.setKey(mw_node.getKey(0));
        }
        
        x.setColor(1);
        
        if(n==1){
            x.setLeft(MWtoRB(mw_node.getChild(0)));
            x.setRight(MWtoRB(mw_node.getChild(1)));
            return x;
        }
        if(n==2){
            RBTree y = new RBTree(0,0);
            y.setKey(mw_node.getKey(1));
            
            y.setColor(0);
            x.setRight(y);
            x.setLeft(MWtoRB(mw_node.getChild(0)));
            y.setLeft(MWtoRB(mw_node.getChild(1)));
            y.setRight(MWtoRB(mw_node.getChild(2)));
            return x;
        }
        RBTree y = new RBTree(0,0);
        RBTree z = new RBTree(0,0);
        y.setKey(mw_node.getKey(0));
        z.setKey(mw_node.getKey(2));
        
        y.setColor(0);
        z.setColor(0);
        
        x.setLeft(y);
        x.setRight(z);
        y.setLeft(MWtoRB(mw_node.getChild(0)));
        y.setRight(MWtoRB(mw_node.getChild(1)));
        z.setLeft(MWtoRB(mw_node.getChild(2)));
        z.setRight(MWtoRB(mw_node.getChild(3)));
        return x;
        
        

    }
}

// Do not change anything beyond this line
class RBTree {
    int key;
    int color; /* 0 = red, 1= black */
    RBTree left;
    RBTree right;

    public RBTree(int key, int color) {
        this.key = key;
        this.color = color;
        this.left = null;
        this.right = null;
    }

    public RBTree(int key, int color, RBTree left, RBTree right) {
        this.key = key;
        this.color = color;
        this.left = left;
        this.right = right;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setLeft(RBTree left) {
        this.left = left;
    }

    public void setRight(RBTree right) {
        this.right = right;
    }

    public int getColor() {
        return color;
    }

    public int getKey() {
        return key;
    }

    public RBTree getLeft() {
        return left;
    }

    public RBTree getRight() {
        return right;
    }
}

class MWTree {
    int numKeys;
    MWTree parent;
    int[] key = new int[3]; /* indices start with 0, null item denoted by 0 */
    MWTree[] child = new MWTree[4]; /* indices start with 0 */

    public MWTree() {
        this.numKeys = 0;
    }

    public MWTree(int numKeys, int[] key, MWTree[] child) {
        this.numKeys = numKeys;
        this.key = key;
        this.child = child;
    }

    public int getNumKeys() {
        return numKeys;
    }

    public int[] getKeyArray() {
        return key;
    }

    public int getKey(int i) {
        return i>=0 && i<3 ? key[i] : 0;  /* indices start with 0, null item denoted by 0 */
    }

    public MWTree[] getChildArray() {
        return child;
    }

    public MWTree getChild(int i) {
        return i>=0 && i<4 ? child[i] : null;
    }

    public MWTree getParent() {
        return parent;
    }

    public boolean isLeaf() {
        return child[0] == null;
    }

    public boolean isFull() {
        return numKeys == 3;
    }

    /* You may skip reading everything beyond this line. These functions are used to construct the
    Multiway Tree and not required for the purposes of solving this problem */
    public int insertItem(int newKey)
    {
        // assumes node is not full
        numKeys++;                          // will add new item

        // Should start this for loop at numItems-1, rather than ORDER -2
        for(int j=2; j>=0; j--)        // start on right,
        {                                 //    examine items
            if(key[j] ==0)          // if item null,
                continue;                      // go left one cell
            else                              // not null,
            {                              // get its key
                int itsKey = key[j];
                if(newKey < itsKey)            // if it's bigger
                    key[j+1] = key[j]; // shift it right
                else
                {
                    key[j+1] = newKey;   // insert new item
                    return j+1;                 // return index to
                }                           //    new item
            }  // end else (not null)
        }  // end for                     // shifted all items,
        key[0] = newKey;              // insert new item
        return 0;
    }

    public void connectChild(int childNum, MWTree childItem)
    {
        this.child[childNum] = childItem;
        if(childItem != null)
            childItem.parent = this;
    }

    // disconnect child from this node, return it
    public MWTree disconnectChild(int childNum)
    {
        MWTree tempNode = child[childNum];
        child[childNum] = null;
        return tempNode;
    }

    public int removeItem()        // remove largest item
    {
        // assumes node not empty
        int temp = key[numKeys-1];  // save item
        key[numKeys-1] = 0;           // disconnect it. 0 denotes null node
        numKeys--;                             // one less item
        return temp;                            // return item
    }
}
