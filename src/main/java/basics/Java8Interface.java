package basics;

public interface Java8Interface {

  // Static method
  static void staticMethod() {
    String info = "Static method in interface";
    System.out.println(info);
  }

  // Abstract method
  void abstractMethod(String input);

  // Default method
  default void defaultMethod() {
    String info = "Default implementation";
    privateMethod(info); // Calling a private method
  }

  // Private method
  private void privateMethod(String info) {
    System.out.println("Private method says: " + info);
  }
}

