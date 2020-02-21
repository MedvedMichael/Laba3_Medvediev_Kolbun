package com.company;

public class KeyValueData <K, V> {
    K mKey;
    V mValue;
    int mHash;
    KeyValueData<K,V> next;

    KeyValueData(int hash, K key, V value, KeyValueData<K, V> next){
        mHash = hash;
        mKey = key;
        mValue = value;
        this.next = next;
    }

}
