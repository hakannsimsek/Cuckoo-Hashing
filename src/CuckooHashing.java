import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CuckooHashing
{
    public HashTable HashTable;
    public int size;



    void Start()
    {
        HashTable = new HashTable();

        HashTable.initTable();
        //List<Integer> list= new ArrayList<>();

        while(true){
            System.out.println("1 - Insert a key");
            System.out.println("2 - Search a key");
            System.out.println("3 - Delete a key");
            System.out.println("0 - Exit");

            Scanner sc = new Scanner(System.in);
            int number = sc.nextInt();

            if(number == 1){
                System.out.println("Enter key to insert");
                Scanner scn = new Scanner(System.in);
                int key = scn.nextInt();
                set(key, 0, 0,false,false);
                System.out.println("Total number of collision : " + HashTable.totalNumberOfCollision);
            }
            else if (number == 2){
                takeKeyToSearch();
            }
            else if (number == 3){
                takeKeyToDeleted();
                HashTable.printStatus();
            }
            else{
                break;
            }
        }
    }

    private void takeKeyToDeleted() {
        System.out.println("Enter key to be deleted");
        Scanner sc = new Scanner(System.in);
        int key = sc.nextInt();
        keyDelete(key);
        System.out.println(key + " deleted. ");

    }

    private void takeKeyToSearch() {
        System.out.println("Enter key to be searched");
        Scanner sc = new Scanner(System.in);
        int key = sc.nextInt();
        int[] result = findKeyIndices(key);
        if(result == null) System.out.println("key not exist");
        else System.out.println("Index of "+ key + ": ( " + result[0] + " , " + result[1] + " )");
    }

    void set(int value, int tableNumber, int times,boolean isRehash,boolean isEvicted)
    {
        if (IsCycleExist(value, times)){
            isRehash = true;
        }
        else if(CheckIfHashedAlready(value)){
            System.out.println("Hashed already");
            return;
        }

        if (HashTable.table[tableNumber][HashTable.position[tableNumber]] != HashTable.EMPTY)
        {
            int displace = HashTable.table[tableNumber][HashTable.position[tableNumber]];
            System.out.println("Number : " + value + " is placed");
            placeValue(tableNumber,value);
            System.out.println("Number : " + displace + " is evicted");
            HashTable.totalNumberOfCollision += 1;
            set(displace, (tableNumber + 1) % HashTable.tableCount, times + 1,isRehash,true);
        }
        else{
            if(!isEvicted)
                System.out.println("Number : " + value + " is placed");
            placeValue(tableNumber,value);
        }
    }

    void placeValue(int tableNumber,int value)
    {
        HashTable.table[tableNumber][HashTable.position[tableNumber]] = value;
        HashTable.printStatus();
    }

    boolean IsCycleExist(int value, int times) {
        if (times == size)
        {
            System.out.println(value + "could not be placed");
            System.out.println("Cycle exist, rehashing again");
            return true;
        }
        return false;
    }

    boolean CheckIfHashedAlready(int value) {
        for (int i = 0; i < HashTable.tableCount; i++)
        {
            HashTable.position[i] = HashTable.hash(i + 1, value,false);
            if (HashTable.table[i][HashTable.position[i]] == value)
                return true;
        }
        return false;
    }

    int[] findKeyIndices(int key)
    {
        for (int i = 0; i < HashTable.tableCount; i++)
        {
            HashTable.position[i] = HashTable.hash(i + 1, key,false);
            if (HashTable.table[i][HashTable.position[i]] == key)
                return new int []{ i , HashTable.position[i] };
        }
        return null;
    }

    boolean keyDelete(int key)
    {
        int[] result = findKeyIndices(key);
        if(result == null)
            return false;

        HashTable.table[result[0]][result[1]] = HashTable.EMPTY;
        return true;
    }

}