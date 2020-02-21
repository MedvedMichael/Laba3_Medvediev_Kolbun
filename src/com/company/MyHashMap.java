package com.company;


import java.util.HashMap;

public class MyHashMap<K, V> {
    KeyValueData<K, V>[] table;
    int capacity = 16;
    int size = 0;
    float loadFactor = 0.8f;

    MyHashMap() {
        table = new KeyValueData[capacity];
    }

    static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    static int getIndexFor(int hash, int length) {
        return hash & (length - 1);
    }

    void put(K key, V value) {
        if (key == null) {
            putWithNullKey(value);
            return;
        }

        int hash = hash(key.hashCode());

        int index = getIndexFor(hash, capacity);

        KeyValueData<K, V> data = table[index];
        if (data.mHash == hash && key.equals(data.mKey)) {
            data.mValue = value;
            return;
        }

        addKeyValueData(hash, key, value, index);


    }

    void putWithNullKey(V value) {

    }

    void addKeyValueData(int hash, K key, V value, int index) {
        KeyValueData<K, V> data = table[index];
        table[index] = new KeyValueData<>(hash, key, value, data);
    }

}
