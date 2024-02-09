package org.example;

import java.util.Iterator;

public class MyBidirectionalHashMap<Key, Value> implements MyMap<Key, Value>{

    private final MyMap<Key, Value> keyToValueMap = new MyHashMap<>();
    private final MyMap<Value, Key> valueToKeyMap = new MyHashMap<>();


    @Override
    public Value get(Key key) {
        return keyToValueMap.get(key);
    }
    @Override
    public boolean containsKey(Key key) {
        return keyToValueMap.containsKey(key);
    }

    @Override
    public void put(Key key, Value value) {
        keyToValueMap.put(key, value);
        valueToKeyMap.put(value, key);
    }

    @Override
    public Value remove(Key key) {
        Value value = keyToValueMap.remove(key);
        valueToKeyMap.remove(value);
        return value;
    }

    @Override
    public int size() {
        return keyToValueMap.size();
    }

    @Override
    public boolean isEmpty() {
        return keyToValueMap.isEmpty();
    }

    public Key getKey(Value value) {
        return valueToKeyMap.get(value);
    }

    @Override
    public String toString() {
        return "BidirectionalHashMap{" +
                "keyToValueMap=" + keyToValueMap +
                ", valueToKeyMap=" + valueToKeyMap +
                '}';
    }

    public Iterator<Entry<Key, Value>> iterator() {
        return new EntryIterator();
    }

    private class EntryIterator implements Iterator<Entry<Key, Value>> {


        private final Iterator<Entry<Key, Value>> keyToValueIterator = keyToValueMap.iterator();
        private final Iterator<Entry<Value, Key>> valueToKeyIterator = valueToKeyMap.iterator();

        @Override
        public boolean hasNext() {
            return keyToValueIterator.hasNext();
        }

        @Override
        public Entry<Key, Value> next() {
            return keyToValueIterator.next();
        }
    }
}

