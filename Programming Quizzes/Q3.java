import java.util.Vector;

class Path{
    
    //Do not change the function signature
    String uniquePath(TreeNode root, int u, int v)throws Exception{
        if(root==null){
            throw new Exception();
        }
        if((root.getVal()>u)&&(root.getVal()>v)){
            return uniquePath(root.getLeft(),u,v);
        }
        if((root.getVal()<u)&&(root.getVal()<v)){
            return uniquePath(root.getRight(),u,v);
        }
        Vector<Integer> vec1 = new Vector<Integer>();
        int len = 0;
        TreeNode temp = root;
        if(u>v){
            int it = u;
            /*u=v;
            v=it;*/
        }
        vec1.add(temp.getVal());
        len++;
        while(temp.getVal()!=u){
            if(u<temp.getVal()){
                temp = temp.getLeft();
            }
            else{
                temp = temp.getRight();
            }
            if(temp==null){
                throw new Exception();
            }
            vec1.add(temp.getVal());
            len++;
        }
        String ans = new String();
        while(len>0){
            ans = ans + vec1.get(len-1) + " ";
            len--;
        }
        temp = root;
        while(temp.getVal()!=v){
            if(v<temp.getVal()){
                temp = temp.getLeft();
            }
            else{
                temp=temp.getRight();
            }
            if(temp==null){
                throw new Exception();
            }
            ans = ans + temp.getVal() + " ";
        }
        return ans;
        //Complete the code
    }
}

//Do not change anything below this
class TreeNode{
    private int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int v){
        this.val = v;
        this.left = null;
        this.right = null;
    }
    
    int getVal(){
        return this.val;
    }
    
    TreeNode getLeft(){
        return this.left;
    }
    
    TreeNode getRight(){
        return this.right;
    }
    
}



class BST{
    
    TreeNode root;

    public BST(){
        this.root = null;
    }    

    void insert(int v){
        
        TreeNode toInsert = new TreeNode(v);
        
        if(this.root == null){
            this.root = toInsert;
        }
        else{
            TreeNode prev = null;
            TreeNode temp = this.root;
            
            while(temp != null){
                
                prev = temp;
                if(v < temp.getVal())
                    temp = temp.left;
                else
                    temp = temp.right;
                
            }
            
            if(v < prev.getVal())
                prev.left = toInsert;
            else
                prev.right = toInsert;
        }
    }
}
