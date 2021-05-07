public class MyHashTable extends COL106HashTable {
    public MyHashTable(int n, int test_id) {
        super(n, test_id);
    }

    /*
     * DO NOT MODIFY CODE ABOVE THIS LINE
     * 
     * The following data members are derived from the parent class and available
     * for your use:
     * 
     * int[] table  // the table
     * int size     // size of the table
     * 
     * You also have at your disposal, hash functions h1 and h2:
     * 
     * int h1(int num)
     * int h2(int num)
     * 
     * All members have been defined and initialized. You must use these to define
     * only the following two methods.
     * insert() has been partially defined, it works for all but does not handle the case for tombstones. 
     * You must complete the code for it to include the case of tombstones.
     * delete() has to be defined completely
     * Both functions must return 0 on normal execution, and return -1 in case of any errors or exceptions arising
     * e.g. deleting a value not present in the table
     */

    int insert(int val) {
        int index = h1(val);
        if(table[index]!=0 && table[index]!=-1){
            int i;
            for(i = 1; i <=size; ++i){
                int index2 = h2(val);
                index2 = (index + i* index2)%size;
                if(table[index2]!=0 && table[index2]!=-1){
                    continue;
                }
                else{
                    table[index2] = val;
                    return 0;
                }
            }
            if(i>size){
               return -1;
            }
        }
        else{
            table[index] = val;
            return 0;
        }
        return -1;
    }

    int delete(int val) {
        int index = h1(val);
        int num = 0;
        while(table[index]!=0 && num<size){
            if(table[index]==val){
                table[index]=-1;
                return 0;
            }
            index = (index + h2(val))%size;
            num++;
        }
        return -1;
    }
}
