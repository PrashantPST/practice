package basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Driver {

    public static void main(String[] args) {
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);


        List<Integer> listx = new ArrayList<>();
        listx.add(2);
        listx.add(1);
        listx.add(3);
        listx.add(4);

        System.out.println(list.equals(listx));






        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(1, 2, 3); // Same content as list1
        List<Integer> list4 = Arrays.asList(1, 3, 2);

        set.add(list1);
        set.add(list2);
        set.add(list3); // Will not be added because it's a duplicate of list1
        set.add(list4);

        System.out.println("Set size: " + set.size()); // Output: Set size: 2

//        for (List<Integer> list : set) {
//            System.out.println(list);
//        }

//        Printer p1 = new Printer(1, 20, 3);
//        Printer p2 = new Printer(2, 20, 3);
//        Printer p3 = new Printer(0, 20, 3);
//        Thread t1 = new Thread(p1, "Thread 1->");
//        Thread t2 = new Thread(p2, "Thread 2 ->");
//        Thread t3 = new Thread(p3, "Thread 3 ->");
//        t1.start();
//        t2.start();
//        t3.start();
    }
}
