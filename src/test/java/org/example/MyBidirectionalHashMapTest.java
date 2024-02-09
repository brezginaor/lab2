package org.example;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;

public class MyBidirectionalHashMapTest {


    @Test
    public void testPut() {
        MyBidirectionalHashMap<String, Integer> map = new MyBidirectionalHashMap<>();

        // Добавление элементов
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        // Проверка наличия добавленных элементов и их значений
        assertEquals(Integer.valueOf(1), map.get("One"));
        assertEquals(Integer.valueOf(2), map.get("Two"));
        assertEquals(Integer.valueOf(3), map.get("Three"));

        // Проверка размера отображения после добавления
        assertEquals(3, map.size());

        // Добавление элемента с существующим ключом (должно обновить значение)
        map.put("One", 10);
        assertEquals(Integer.valueOf(10), map.get("One"));

        // Проверка размера отображения после обновления значения
        assertEquals(3, map.size());

        // Добавление элемента с null ключом (должно выбросить NullPointerException)
        try {
            map.put(null, 5);
            fail("Expected NullPointerException for null key");
        } catch (NullPointerException e) {
            // Проверка на успешное выбрасывание исключения
            assertNotNull(e);
        }
    }

    @Test
    public void testGet() {
        MyBidirectionalHashMap<String, Integer> map = new MyBidirectionalHashMap<>();

        // Добавление элементов
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        // Проверка значений по ключам
        assertEquals(Integer.valueOf(1), map.get("One"));
        assertEquals(Integer.valueOf(2), map.get("Two"));
        assertEquals(Integer.valueOf(3), map.get("Three"));

        // Проверка значения для несуществующего ключа
        assertNull(map.get("Four"));

        // Проверка значения для null ключа (должно выбросить NullPointerException)
        try {
            map.get(null);
            fail("Expected NullPointerException for null key");
        } catch (NullPointerException e) {
            // Проверка на успешное выбрасывание исключения
            assertNotNull(e);
        }
    }

    @Test
    public void testGetKey() {
        MyBidirectionalHashMap<String, Integer> map = new MyBidirectionalHashMap<>();

        // Добавление элементов
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        // Проверка ключей по значениям
        assertEquals("One", map.getKey(1));
        assertEquals("Two", map.getKey(2));
        assertEquals("Three", map.getKey(3));

        // Проверка ключа для несуществующего значения
        assertNull(map.getKey(4));
    }

    @Test
    public void testRemove() {
        MyBidirectionalHashMap<String, Integer> map = new MyBidirectionalHashMap<>();

        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        assertEquals(3, map.size());

        Integer removedValue = map.remove("Two");
        assertEquals(2, removedValue.intValue());

        assertNull(map.get("Two"));
        assertEquals(2, map.size());
    }

    @Test
    public void testContainsKey() {
        MyBidirectionalHashMap<String, Integer> map = new MyBidirectionalHashMap<>();

        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        assertTrue(map.containsKey("Two"));
        assertFalse(map.containsKey("Four"));
    }

    @Test
    public void testSize() {
        MyBidirectionalHashMap<String, Integer> map = new MyBidirectionalHashMap<>();

        assertEquals(0, map.size());

        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        assertEquals(3, map.size());

        map.remove("Two");
        assertEquals(2, map.size());
    }

    @Test
    public void testIsEmpty() {
        MyBidirectionalHashMap<String, Integer> map = new MyBidirectionalHashMap<>();

        assertTrue(map.isEmpty());

        map.put("One", 1);

        assertFalse(map.isEmpty());
    }

    @Test
    public void testIterator() {
        MyBidirectionalHashMap<String, Integer> map = new MyBidirectionalHashMap<>();

        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);


        Iterator<MyMap.Entry<String, Integer>> iterator = map.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;

        }



        assertEquals(count, 3);
    }
}
