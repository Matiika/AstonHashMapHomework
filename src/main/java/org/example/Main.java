package org.example;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap<>();
        myHashMap.put(1, "Mat");
        myHashMap.put(2, "Tru");
        System.out.println(myHashMap.get(1));
        System.out.println(myHashMap.get(2));
        System.out.println("Size: " + myHashMap.size());

        System.out.println("----------------");

        myHashMap.put(1, "Han");
        System.out.println(myHashMap.get(1));
        System.out.println("Size: " + myHashMap.size());

        System.out.println("----------------");
        myHashMap.put(3, "Obj");
        myHashMap.put(4, "Exam");
        System.out.println("Size: " + myHashMap.size());
    }
}