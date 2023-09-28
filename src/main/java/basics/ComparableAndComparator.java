package basics;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

public class ComparableAndComparator {

    public static void main(String[] args) {
        Employee[] employees = new Employee[5];

        employees[0] = new Employee("Tom", "", 45, 80000);
        employees[1] = new Employee("Sam", "", 56, 75000);
        employees[2] = new Employee("Alex", "", 30, 120000);
        employees[3] = new Employee("Peter", "", 25, 60000);
        employees[4] = new Employee("Peter", "", 45, 60000);

        System.out.println("Before sorting: " + Arrays.toString(employees));
        Arrays.sort(employees, Comparator
                .comparing(Employee::getName)
                .thenComparing(Employee::getAge)
                .thenComparing(Employee::getSalary));
        System.out.println("After sorting: " + Arrays.toString(employees));
    }

    @AllArgsConstructor
    @Getter
    static
    class Employee {
        private final String name;
        private final String jobTitle;
        private final int age;
        private final int salary;

        public String toString() {
            return String.format("%s\t%s\t%d\t%d", name, jobTitle, age, salary);
        }
    }
}
