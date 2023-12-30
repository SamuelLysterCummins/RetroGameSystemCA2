package com.example.retrovideogamesystemca2;

import java.util.Iterator;

public class FastHashIterator<T> implements Iterator<T> {

    private FastHash.HashEntry<T>[] table;
    private int index;
    public FastHashIterator(FastHash.HashEntry<T>[] table) {
        this.table = table;
        this.index = 0;
        advanceIndex();
    }

    private void advanceIndex() {
        while (index < table.length && (table[index] == null || table[index].value == null)) {
            index++;
        }
    }
    @Override
    public boolean hasNext() {
        return index < table.length;
    }

    @Override
    public T next() {
        T value = table[index].value;
        index++;
        advanceIndex();
        return value;
    }
}
