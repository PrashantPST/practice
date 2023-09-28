package design.pattern.creational.builder;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {
    private final String username;
    private final int age;
    private final String email;
    private final boolean active;

    private User(UserBuilder userBuilder) {
        this.username = userBuilder.username;
        this.age = userBuilder.age;
        this.email = userBuilder.email;
        this.active = userBuilder.active;
    }
    public static class UserBuilder {

        private String username;
        private int age;
        private String email;
        private boolean active;

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setActive(boolean active) {
            this.active = active;
            return this;
        }

        public User build() {
            User user = new User(this);
            if (user.age < 18) {
                throw new IllegalArgumentException("User age must be over 18");
            }
            return user;
        }

    }
}
