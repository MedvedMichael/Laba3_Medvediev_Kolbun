package com.company;

public class Main {

    public static void main(String[] args) {
        MyHashMap<String, String> testHashMap = new MyHashMap<>();
        testHashMap.put("0","zero");
        testHashMap.put("1","one");

        System.out.print("Test: " + testHashMap.get("1"));
    }
}
