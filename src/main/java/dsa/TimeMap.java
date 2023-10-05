package dsa;

/*
Design a time-based key-value data structure that can store multiple values for the same key at different time stamps
and retrieve the key's value at a certain timestamp.

String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp.
If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values,
it returns "".
 */

import dsa.models.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
public class TimeMap {

    HashMap<String, HashMap<Integer, String>> keyTimeMap;
    HashMap<String, TreeMap<Integer, String>> keyTimeMap1;
    Map<String, List<Pair<Integer, String>>> keyTimeMap2;
    public TimeMap() {
        keyTimeMap = new HashMap<>();
        keyTimeMap1 = new HashMap<>();
        keyTimeMap2 = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {

        if (!keyTimeMap.containsKey(key)) {
            keyTimeMap.put(key, new HashMap<>());
        }
        keyTimeMap.get(key).put(timestamp, value);

        if (!keyTimeMap1.containsKey(key)) {
            keyTimeMap1.put(key, new TreeMap<>());
        }
        keyTimeMap1.get(key).put(timestamp, value);

        if (!keyTimeMap2.containsKey(key)) {
            keyTimeMap2.put(key, new ArrayList<>());
        }
        // Store '(timestamp, value)' pair in 'key' bucket.
        keyTimeMap2.get(key).add(new Pair<>(timestamp, value));
    }

    public String get(String key, int timestamp) {

        /*
        Approach I
        if (!keyTimeMap.containsKey(key)) {
            return "";
        }
        // Iterate on time from 'timestamp' to '1.'
        for (int currTime = timestamp; currTime >= 1; --currTime) {
            // If a value for current time is stored in key's bucket, we return the value.
            if (keyTimeMap.get(key).containsKey(currTime)) {
                return keyTimeMap.get(key).get(currTime);
            }
        }
        // Otherwise, no time <= timestamp was stored in key's bucket.
        return "";
         */

        /*
        Approach II
        Integer floorKey = keyTimeMap1.get(key).floorKey(timestamp);
        // Return searched time's value, if exists.
        if (floorKey != null) {
            return keyTimeMap1.get(key).get(floorKey);
        }
        return "";
         */

        if (!keyTimeMap2.containsKey(key)) {
            return "";
        }
        List<Pair<Integer, String>> l = keyTimeMap2.get(key);
        int left = 0;
        int right = l.size() - 1;
        int mid;
        int ansIdx = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            int midTs = l.get(mid).getKey();
            if (midTs == timestamp) {
                return keyTimeMap2.get(key).get(mid).getValue();
            }
            else if (midTs < timestamp) {
                ansIdx = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return ansIdx == -1 ? "" : keyTimeMap2.get(key).get(ansIdx).getValue();
    }
}
