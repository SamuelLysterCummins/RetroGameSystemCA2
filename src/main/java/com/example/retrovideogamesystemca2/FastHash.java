package com.example.retrovideogamesystemca2;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Scanner;

public class FastHash<T> implements Iterable<T>, Serializable {
    private HashEntry<T>[] hashTable;

    @Override
    public Iterator<T> iterator() {
        return new FastHashIterator<>(hashTable);
    }

    public FastHash(int size) {
        hashTable = new HashEntry[size];
    }

    static class HashEntry<T> {
        String key;
        T value;
        HashEntry(String key, T value) {
            this.key = key;
            this.value = value;
        }

    }

    public int hashFunction(String key) {
        int hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            hashValue = (hashValue + key.charAt(i)) % hashTable.length;
        }
        return hashValue;
    }


    public boolean add(String key, T value) {
        int hashIndex = hashFunction(key);
        for (int i = 0; i < hashTable.length; i++) {
            int index = (hashIndex + i) % hashTable.length;
            if (hashTable[index] == null || hashTable[index].key.equals(key)) {
                hashTable[index] = new HashEntry<>(key, value);
                return true;
            }
        }
        return false;
    }

    public T get(String key) {
        int hashIndex = hashFunction(key);
        for (int i = 0; i < hashTable.length; i++) {
            int index = (hashIndex + i) % hashTable.length;
            if (hashTable[index] != null && hashTable[index].key.equals(key)) {
                return hashTable[index].value;
            }
        }
        return null;
    }

    public boolean remove(String key) {
        int hashIndex = hashFunction(key);
        for (int i = 0; i < hashTable.length; i++) {
            int index = (hashIndex + i) % hashTable.length;
            HashEntry<T> entry = hashTable[index];
            if (entry != null && entry.key.equals(key)) {
                hashTable[index] = null;
                return true;
            }
        }
        return false;
    }

}
