package com.company;

import com.company.lineardatastructures.MyHashTable;
import com.company.lineardatastructures.utils.HashNode;

public class MyHashTableDemo {
    public static void main(String[] args) {
        MyHashTable<String, Integer> hashTable = new MyHashTable<>();
        hashTable.add("this", 1);
        hashTable.add("thisasd", 12);
        hashTable.add("thasdis", 13);
        hashTable.add("thasdais", 14);
        hashTable.add("thasdasis", 15);
        hashTable.add("thiadsas", 17);
        hashTable.add("thasdais", 19);
        hashTable.print();
        System.out.println("---------------------------");
        for (HashNode<String, Integer> hs : hashTable) {
            System.out.println(hs);
        }
        System.out.println(hashTable.containsKey("tis"));
        System.out.println(hashTable.remove("this"));
        System.out.println(hashTable.getValueByKey("thasdais"));
        System.out.println(hashTable.remove("thsadf"));
        System.out.println(hashTable.getSize());
    }
}
