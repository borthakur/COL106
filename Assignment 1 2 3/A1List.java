// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List newNode = new A1List(address, size, key);
    	if(this == null) return null;
	if(this.next == null) return null;
    	newNode.next = this.next;
    	newNode.prev = this;
    	this.next = newNode;
    	newNode.next.prev = newNode;
    	
        return newNode;
    }
	
    private boolean isSame(Dictionary index, Dictionary d)
    {	
	
	if(index.key != d.key) return false;
	if(index.address != d.address) return false;
	if(index.size != d.size) return false;
	return true;
    }

    public boolean Delete(Dictionary d) 
    {

	if(d == null) return false;
        A1List index = this;
    	if(index == null) return false;

	while(index.prev != null) index = index.prev;
	index = index.next;
	if(index == null) return false;
    	while(index.next != null ) {
    		if(isSame(index,d)) {
    			index.prev.next = index.next;
    			index.next.prev = index.prev;
    			return true;    			
    		}
    		index = index.next;
    	}
        return false;
    }

    public A1List Find(int k, boolean exact)
    { 
	A1List index = this;
	if(index == null) return null;
	while(index.prev != null) index = index.prev;
	index = index.next;
	if(index.next == null) return null;
        if(exact == true) {
        	while(index.next != null) {
        		if(index.key == k) return index;
        		index = index.next;
        	}
        	return null;
    	}
	while(index.next != null) {
    		if(index.key >= k) return index;
    		index = index.next;
    	}
    	return null;
    }

    public A1List getFirst()
    {
        A1List temp2 = this;
	if(temp2 == null) return null;
    	A1List temp1 = temp2.prev;
    	
    	while(temp1 != null) {
    		temp2 = temp1;
    		temp1 = temp2.prev;
    	}
    	
    	temp2 = temp2.next;
    	
    	if(temp2.next == null) {
    		return null;
    	}
    	
    	return temp2;
    }
    
    public A1List getNext() 
    {
        A1List temp = this;
	if(temp == null) return null;
    	
    	temp = temp.next;

	if(temp == null) return null;
    	
    	if(temp.next == null) return null;
    	
        return temp;
    }

    public boolean sanity()
    {
        A1List slow = this;
	if(slow == null) return false;
    	
    	A1List fast = this.prev;
    	
    	while(fast != null) {
    		fast = fast.prev;
    		if(fast == null) break;
    		fast = fast.prev;
    		slow = slow.prev;
    		if(fast == slow) return false;
    	}
    	
    	A1List index = this;
    	
    	while(index.prev != null) index = index.prev;
    	
    	if(index.key != -1 || index.address != -1 || index.size != -1) return false;
    	if(index.next == null) return false;
    	
    	A1List index2 = this;
    	while(index2 != index) {
    		if(index2.prev.next != index2) return false;
    		index2 = index2.prev;
    	}
    	
    	slow = this;
    	
    	fast = this.next;
    	
    	while(fast != null) {
    		fast = fast.next;
    		if(fast == null) break;
    		fast = fast.next;
    		slow = slow.next;
    		if(fast == slow) return false;
    	}
    	
    	index = this;
    	
    	while(index.next != null) index = index.next;
    	
    	if(index.key != -1 || index.address != -1 || index.size != -1) return false;
    	if(index.prev == null) return false;
    	
    	index2 = this;
    	while(index2 != index) {
    		if(index2.next.prev != index2) return false;
    		index2 = index2.next;
    	}
    	
        return true;
    }

}


