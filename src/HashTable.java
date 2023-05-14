import java.util.Random;

public class HashTable {

    public static int maxSize = 7;
    public static int tableCount = 3;
    final int EMPTY = -1;
    int [][] table = new int[tableCount][maxSize];
    int [] position = new int[tableCount];
    public static int totalNumberOfCollision = 0;


    void initTable()
    {
        for(int j = 0; j < maxSize; j++)
            for (int i = 0; i < tableCount; i++)
                table[i][j] = EMPTY;
    }


    int hash(int tableNumber, int value,boolean isRehash)
    {
        if(isRehash) value *= 2;

        int hash = new Random(value * tableNumber).nextInt(maxSize);

        if(0 < tableNumber && tableNumber <= tableCount)
            return hash;

        return EMPTY;
    }

    void printStatus()
    {
        System.out.println("Status : ");

        for(int i = 0; i < tableCount; i++) {
            System.out.print("Table " + (i+1) + ": | ");
            for (int j = 0; j < maxSize; j++) {
                if (table[i][j] == EMPTY)
                    System.out.print(" . | ");
                else
                    System.out.print(table[i][j] + " | ");
            }
            System.out.println(" Load factor : "+calculateLoadFactor(table[i]));
            //System.out.println("");
        }

        System.out.println("");
    }

    String calculateLoadFactor(int [] table){
        int occr = 0;
        for(int i = 0; i < table.length ; i++){
            if(table[i] != EMPTY)
                occr += 1;
        }
        return "" + occr + "/" + table.length;
    }


}
