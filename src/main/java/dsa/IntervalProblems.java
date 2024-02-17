package dsa;

import static dsa.Util.doesIntervalsOverlap;
import static java.util.Comparator.comparingInt;

import dsa.models.Interval;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class IntervalProblems {

  public static Interval[] mergeOverlappingIntervals(Interval[] intervals) {
    Arrays.sort(intervals, comparingInt(Interval::getStart));
    List<Interval> answer = new ArrayList<>();
    for (int i = 0; i < intervals.length; i++) {
      Interval currentInterval = intervals[i];
      // Merge until the list gets exhausted or no overlap is found.
      while (i < intervals.length && Util.doesIntervalsOverlap(currentInterval, intervals[i])) {
        currentInterval = Util.mergeIntervals(currentInterval, intervals[i]);
        i++;
      }
      answer.add(currentInterval);
      // Decrement to ensure we don't skip the interval due to outer for-loop incrementing.
      i--;
    }
    return answer.toArray(new Interval[0]);
  }

  public Interval[] insert(Interval[] intervals, Interval newInterval) {
    intervals = insertAnInterval(intervals, newInterval);
    return mergeOverlappingIntervals(intervals);
  }

  Interval[] insertAnInterval(Interval[] intervals, Interval newInterval) {
    boolean intervalInserted = false;
    List<Interval> list = new ArrayList<>();
    for (int i = 0; i < intervals.length; i++) {
      if (newInterval.getStart() < intervals[i].getStart()) {
        // Found the position, insert the interval and break from the loop.
        list.add(i, newInterval);
        intervalInserted = true;
        break;
      }
    }
    // If there is no interval ith a greater value of start value,
    // then the interval must be inserted at the end of the list.
    if (!intervalInserted) {
      list.add(newInterval);
    }
    return list.toArray(new Interval[0]);
  }

  public int eraseOverlapIntervals(Interval[] intervals) {
    int ans = 0;
    Arrays.sort(intervals, comparingInt(Interval::getEnd));
    Interval currentInterval = intervals[0];
    for (Interval interval : intervals) {
      if (!doesIntervalsOverlap(currentInterval, interval)) {
        currentInterval = interval;
        ans++;
      }
    }
    return intervals.length - ans - 1;
  }

  /*
  Meeting Rooms I
  Given an array of meeting time intervals where intervals[i] = [start, end],
  determine if a person could attend all meeting
  TC - O(nlogn)
  SC - O(1)
   */
  public static boolean canAttendAllMeetings(int[][] intervals) {
    Arrays.sort(intervals, comparingInt(a -> a[0]));
    for (int i = 0; i < intervals.length - 1; i++) {
      if (overlap(intervals[i], intervals[i + 1])) {
        return false;
      }
    }
    return true;
  }

  private static boolean overlap(int[] interval1, int[] interval2) {
    int prevMeetingEnd = interval1[1];
    int nextMeetingStart = interval2[0];
    return nextMeetingStart < prevMeetingEnd;
  }

  /*
  Meeting Rooms II
  Given an array of meeting time intervals where intervals[i] = [start, end],
  return the minimum number of conference rooms required
  TC - O(NlogN)
  SC - O(N)
   */
  public int minimumMeetingRooms(Interval[] intervals) {
    // Sort the given meetings by their start time.
    Arrays.sort(intervals, comparingInt(Interval::getStart));
    // we can keep all the rooms in a min heap where the key for the min heap would be the end time of meeting
    PriorityQueue<Integer> allocatedRooms = new PriorityQueue<>();
    for (Interval interval : intervals) {
      if (!allocatedRooms.isEmpty() && allocatedRooms.peek() <= interval.getStart()) {
        allocatedRooms.poll();
      }
      allocatedRooms.add(interval.getEnd());
    }
    return allocatedRooms.size();
  }

  public static int laptopRentals(List<List<Integer>> times) {
    times.sort(comparingInt(a -> a.get(0)));
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (List<Integer> time : times) {
      if (!pq.isEmpty() && pq.peek() <= time.get(0)) {
        pq.poll();
      }
      pq.add(time.get(1));
    }
    return pq.size();
  }
}
