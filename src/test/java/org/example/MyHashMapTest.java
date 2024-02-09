package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;

public class MyHashMapTest {

    @Test
    public void testPutAndGet() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        map.put("One", 1);
        hashMap.put("One", 1);

        assertEquals(hashMap.get("One"), map.get("One"));
        assertNull(map.get("none"));

        map.put("One", 10);
        hashMap.put("One", 10);


        assertEquals(hashMap.get("One"), map.get("One"));

        map.put("Two", 2);
        hashMap.put("Two", 2);
        assertEquals(hashMap.get("Two"), map.get("Two"));

    }

    @Test
    public void testSize() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        assertEquals(0, map.size());

        map.put("One", 1);
        assertEquals(1, map.size());

        map.put("Two", 2);
        assertEquals(2, map.size());

        map.put("Three", 3);
        assertEquals(3, map.size());

        map.remove("Two");
        assertEquals(2, map.size());
    }

    @Test
    public void testRemove() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        assertEquals(Integer.valueOf(2), map.remove("Two"));
        assertNull(map.get("Two"));
        assertEquals(2, map.size());
    }

    @Test
    public void testIsEmpty() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        assertTrue(map.isEmpty());

        map.put("One", 1);
        assertFalse(map.isEmpty());

        map.remove("One");
        assertTrue(map.isEmpty());
    }

    @Test
    public void testContainsKey() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        assertFalse(map.containsKey("One"));

        map.put("One", 1);
        assertTrue(map.containsKey("One"));

        map.remove("One");
        assertFalse(map.containsKey("One"));
    }

    @Test
    public void testIterator() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);


        int count = 0;
        for (MyMap.Entry<String, Integer> entry : map) {
            count++;
        }

        assertEquals(3, count);
    }


}