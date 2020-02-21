package com.company;



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

//        KeyValueData<K, V> data = table[index];
//        while(data != null) {
//            if (data.mHash == hash && key.equals(data.mKey)) {
//                data.mValue = value;
//                return;
//            }
//            data = data.next;
//        }

        V checked = checkAllKeyValueData(hash, key, value, index);
        if (checked != null)
            return;


        addKeyValueData(hash, key, value, index);
    }

    V checkAllKeyValueData(int hash, K key, V value, int index) {
        KeyValueData<K, V> data = table[index];
        while (data != null) {
            if (data.mHash == hash && key.equals(data.mKey)) {
                V deltaValue = data.mValue;
                data.mValue = value;
                return deltaValue;
            }
            data = data.next;
        }
        return null;
    }

    private void putWithNullKey(V value) {
        V checked = checkAllKeyValueData(0, null, value, 0);
        if (checked == null)
            return;

        addKeyValueData(0, null, value, 0);
    }

    void addKeyValueData(int hash, K key, V value, int index) {
        KeyValueData<K, V> data = table[index];
        table[index] = new KeyValueData<>(hash, key, value, data);

    }

    V get(K key) {
        int hash = hash(key.hashCode());

        int index = getIndexFor(hash, capacity);

        KeyValueData<K, V> data = table[index];

        while (data != null) {
            if (key.equals(data.mKey))
                return data.mValue;
            data = data.next;
        }

        return null;
    }

}




