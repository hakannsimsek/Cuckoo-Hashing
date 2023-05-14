import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {

        System.out.println("Enter number of tables : ");
        Scanner sc = new Scanner(System.in);
        HashTable.tableCount = sc.nextInt();

        System.out.println("Enter size of table : ");
        Scanner scn = new Scanner(System.in);
        HashTable.maxSize = scn.nextInt();

        CuckooHashing cuckooHashing = new CuckooHashing();
        System.out.println("Cuckoo Hashing : ");

        cuckooHashing.size = HashTable.maxSize;
        cuckooHashing.Start();

    }
}
