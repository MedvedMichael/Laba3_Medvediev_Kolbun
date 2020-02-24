package com.company;


public class MyHashMap<K, V> {
    KeyValueData[] table; // KeyValueData - data like "key - value"
    int capacity = 16;
    int size = 0;
    float loadFactor = 0.8f;

    MyHashMap() {
        table = new KeyValueData[capacity];
    }

    // get hash from the key
    static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    //    get index to put from hash and current length
    static int getIndexFor(int hash, int length) {
        return hash & (length - 1);
    }

    //    push "key - value" into the table
    void put(K key, V value) {
        System.out.println(key + " " + value);
        if (key == null) {
            putWithNullKey(value);
            return;
        }

        int hash = hash(key.hashCode());
        System.out.println("Hash: " + hash);

        int index = getIndexFor(hash, capacity);
        System.out.println("Index: " + index);

        V checked = checkAllKeyValueData(hash, key, value, index);
        if (checked != null)
            return;


        addKeyValueData(hash, key, value, index);


    }

    //    check all "key - value" data if they have the same key
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

    // push "key - value" data if key == null
    private void putWithNullKey(V value) {
        V checked = checkAllKeyValueData(0, null, value, 0);
        if (checked == null)
            return;

        addKeyValueData(0, null, value, 0);

    }

    //  create KeyValueData element and put it with right index
    void addKeyValueData(int hash, K key, V value, int index) {
        KeyValueData<K, V> data = table[index];
        table[index] = new KeyValueData<>(hash, key, value, data);
        size++;
        if ((float) size / (float) capacity >= loadFactor) {
            resize(capacity * 2);
        }
    }

    // returns the value for given key
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

//    void remove(K key) {
//        int hash = hash(key.hashCode());
//
//        int index = getIndexFor(hash, capacity);
//
//        KeyValueData<K, V> data = table[index];
//        if (data.next != null) {
//
//        }
//    }

    void resize(int newCapacity) {
        KeyValueData[] table2 = new KeyValueData[newCapacity];
        capacity = newCapacity;
        transferData(table2);

    }

    private void transferData(KeyValueData<K, V>[] newTable) {
        KeyValueData[] copiedLastTable = this.table.clone();
        this.table = newTable;
        size = 0;
        for (int i = 0; i < copiedLastTable.length; i++) {
            KeyValueData<K, V> data = copiedLastTable[i];
            while (data != null) {
                put(data.mKey, data.mValue);
                data = data.next;
            }
        }
    }

    public void printAll() {
        for (int i = 0; i < table.length; i++) {
            KeyValueData<K, V> data = table[i];
            System.out.print("Box " + i + ": ");
            while (data != null) {
                System.out.print("<" + data.mKey + " - " + data.mValue + "> ");
                data = data.next;
            }
            System.out.println();
        }
        System.out.println();
    }


    static class KeyValueData<K, V> {
        K mKey;
        V mValue;
        int mHash;
        KeyValueData<K, V> next;

        KeyValueData(int hash, K key, V value, KeyValueData<K, V> next) {
            mHash = hash;
            mKey = key;
            mValue = value;
            this.next = next;
        }
    }
}





