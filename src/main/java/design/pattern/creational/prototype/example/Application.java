package design.pattern.creational.prototype.example;

public class Application {
    public static void main(String[] args) {
        Employee employee1 = Employee.builder().id(1).name("Lava Kumar").age(24).salary(10000000).build();
        System.out.println(employee1);
        System.out.println(employee1.hashCode());
        Employee employee2 = (Employee) employee1.clone();
        System.out.println(employee2);
        System.out.println(employee2.hashCode());
    }
}
