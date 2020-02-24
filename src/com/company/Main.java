package com.company;

public class Main {

    public static void main(String[] args) {
        MyHashMap<String, String> testHashMap = new MyHashMap<>();
        for(int i=0;i<100;i++){
            testHashMap.put(Integer.toString(i),Integer.toString(i));
            testHashMap.printAll();
        }

    }
}
