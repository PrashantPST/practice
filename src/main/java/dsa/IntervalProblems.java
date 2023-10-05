package dsa;

import dsa.models.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dsa.Util.doesIntervalsOverlap;
import static java.util.Comparator.comparingInt;

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
}
