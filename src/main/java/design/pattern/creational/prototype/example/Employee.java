package design.pattern.creational.prototype.example;

import lombok.Builder;

@Builder
public class Employee implements Prototype {
    private final Integer id;
    private final String name;
    private final Integer age;
    private final double salary;

    @Override
    public Prototype clone() {
        return Employee.builder().id(this.id).name(this.name).age(this.age).salary(this.salary).build();
    }
}
