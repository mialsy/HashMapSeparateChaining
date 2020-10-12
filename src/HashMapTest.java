import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    HashMap<Integer, String> map;

    @BeforeEach
    void setUp() {
        map = new HashMap<>();
    }

    @Test
    void putGetAndIterator() {
        System.out.println("Test put");
        for (int i = 0; i < 32; i++) {
            map.put(i, "string" + String.valueOf(i));
        }
        System.out.println("Test get");
        for (int i = 0; i < 32; i++) {
            assertEquals(map.get(i), "string" + String.valueOf(i));
        }

        int count = 0;
        System.out.println("Test iterator");
        Iterator<HashMap.Node<Integer, String>> iterator = map.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().value);
            count++;
        }
        assertEquals(count, 32);
    }

    @Test
    void size() {
        for (int i = 0; i < 32; i++) {
            map.put(i, "string" + String.valueOf(i));
        }
        System.out.println("Test size" + map.size());
        assertEquals(32, map.size());
    }

    @Test
    void isEmpty() {
        System.out.println("Test isEmpty");
        assertTrue(map.isEmpty());
        map.put(1,"String");
        assertFalse(map.isEmpty());
    }

    @Test
    void containsKey() {
        System.out.println("Test containsKey");

        map.put(1,"String");
        assertTrue(map.containsKey(1));
        assertFalse(map.containsKey(2));
    }

    @Test
    void remove() {
        System.out.println("Test remove");

        map.put(1,"String");
        assertTrue(map.containsKey(1));
        map.remove(1);
        assertFalse(map.containsKey(1));
    }
}