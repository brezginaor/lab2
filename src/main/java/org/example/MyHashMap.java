package org.example;

import java.util.Iterator;

public class MyHashMap<Key, Value> implements MyMap<Key, Value>, Iterable<MyMap.Entry<Key, Value>> {

    private static final int[] PRIME_CAPACITIES = {
            17, 31, 61, 127, 257, 509, 1021, 2053, 4099, 8209, 16411, 32771, 65537,
            131101, 262147, 524309, 1048583, 2097169, 4194319, 8388617, 16777259,
            33554467, 67108879, 134217757, 268435459, 536870923, 1073741827
    };


    private static final int DEFAULT_INITIAL_CAPACITY = PRIME_CAPACITIES[0];

    private static final double LOAD_FACTOR = 0.75;

    int size;

    Node[] table;

    int capacityIndex = 0;

    public MyHashMap() {
    }


    @Override
    public Value get(Key key) {
        if (table == null) {
            return null;
        }

        final int hash = key.hashCode();
        final Node bucket = table[index(hash)];

        if (bucket == null) {
            return null;
        } else {
            return bucket.get(hash, key);
        }
    }


    @Override
    public void put(Key key, Value value) {
        if (table == null || size >= LOAD_FACTOR * capacity()) {
            table = resize();
        }

        final int hash = key.hashCode();
        final int index = index(hash);

        if (table[index] == null) {
            table[index] = new Node(hash, key, value);
            size++;
            return;
        }

        table[index].put(hash, key, value);
    }



    @Override
    public Value remove(Key key) {
        if (table == null) {
            return null;
        }

        final int hash = key.hashCode();
        final int index = index(hash);
        Node current = table[index];

        if (current == null) {
            return null;
        }

        if (current.matches(hash, key)) {
            table[index] = current.next;
            size--;
            return current.value;
        }

        while (!current.isLast() && !current.next.matches(hash, key)) {
            current = current.next;
        }

        if (!current.next.isLast()) {
            size--;
            Value previous = current.next.value;
            current.next = current.next.next;
            return previous;
        }

        return null;
    }


    @Override
    public int size() {

        return size;
    }

    @Override
    public boolean isEmpty() {

        return size() == 0;
    }

    @Override
    public Iterator<Entry<Key, Value>> iterator() {
        return new EntryIterator();
    }

    private int capacity() {

        return table != null ? table.length : DEFAULT_INITIAL_CAPACITY;
    }


    int index(int hash) {

        return Math.floorMod(hash, capacity());
    }


    private Node[] resize() {
        if (capacityIndex >= PRIME_CAPACITIES.length) {
            throw new IllegalStateException("Хеш-таблица достигла максимальной емкости.");
        }

        if (table != null) {
            capacityIndex++;
        }

        final int newCapacity = PRIME_CAPACITIES[capacityIndex];
        final Node[] newTable = (Node[]) new MyHashMap<?, ?>.Node[newCapacity];

        if (table == null) {
            return newTable;
        }

        for (Node bucket : table) {
            Node node = bucket;
            while (node != null) {
                final Node current = node;
                node = node.next;

                final int index = Math.floorMod(current.hash, newCapacity);
                current.next = newTable[index];
                newTable[index] = current;
            }
        }

        return newTable;
    }





    public  class EntryIterator implements Iterator<Entry<Key, Value>> {

        private int index = 0;

        private Node currentNode = table[0];

        {
            findNextNonNullNode();
        }

        private void findNextNonNullNode() {
            while (currentNode == null && index < table.length - 1) {
                index++;
                currentNode = table[index];
            }
        }


        @Override
        public boolean hasNext() {
            return !(index == table.length - 1 && currentNode == null);
        }

        @Override
        public Entry<Key, Value> next() {
            final Node current = currentNode;

            if (currentNode != null) {
                currentNode = currentNode.next;
            }
            findNextNonNullNode();

            return current;
        }
    }

    class Node implements MyMap.Entry<Key, Value> {

        final Key key;
        final int hash;
        Value value;
        Node next;

        Node(int hash, Key key, Value value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        Node(int hash, Key key, Value value) {
            this(hash, key, value, null);
        }


        Value get(int hash, Key key) {
            Node node = findOrGetLast(hash, key);
            if (!node.isLast() || node.matches(hash, key)) {
                return node.value;
            }
            return null;
        }


        void put(int hash, Key key, Value value) {
            Node node = this;
            while (!node.isLast() && !node.matches(hash, key)) {
                node = node.next;
            }
            if (!node.isLast() || node.matches(hash, key)) {
                node.value = value;
                return;
            }
            node.next = new Node(hash, key, value);
            size++;
        }


        Node findOrGetLast(int hash, Key key) {
            Node node = this;
            while (!node.isLast() && !node.matches(hash, key)) {
                node = node.next;
            }
            return node;
        }


        boolean isLast() {
            return next == null;
        }


        boolean matches(int hash, Key key) {
            return this.hash == hash && this.key.equals(key);
        }


        @Override
        public Key getKey() {
            return key;
        }

        @Override
        public Value getValue() {
            return value;
        }


        @Override
        public Value setValue(Value value) {
            final Value previous = this.value;
            this.value = value;
            return previous;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return hash == node.hash;
        }

        @Override
        public int hashCode() {
            return hash;
        }
    }
}