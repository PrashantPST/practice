package dsa.junits;

import dsa.TimeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeMapTest {

    private TimeMap timeMap;

    @BeforeEach
    public void setUp() {
        timeMap = new TimeMap();
    }

    @Test
    public void testSetAndGet() {
        timeMap.set("key1", "value1", 1);
        timeMap.set("key1", "value2", 2);
        timeMap.set("key2", "value3", 3);

        assertEquals("value1", timeMap.get("key1", 1));
        assertEquals("value2", timeMap.get("key1", 2));
        assertEquals("value2", timeMap.get("key1", 3));
        assertEquals("value3", timeMap.get("key2", 3));
    }

    @Test
    public void testGetNonExistentKey() {
        assertEquals("", timeMap.get("nonexistent", 1));
    }

    @Test
    public void testGetNonExistentTimestamp() {
        timeMap.set("key1", "value1", 1);
        assertEquals("value1", timeMap.get("key1", 2));
    }
}

