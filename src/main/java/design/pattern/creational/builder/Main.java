package design.pattern.creational.builder;

public class Main {
    public static void main(String[] args) {
        User user = new User.UserBuilder().setUsername("dgf").setAge(34).setActive(true).
                setEmail("sdfds").setEmail("sfd").setEmail("Sfsd").build();
        System.out.println(user);
    }
}
