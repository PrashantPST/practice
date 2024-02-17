package dsa.design;


/**
 * You are given an object street of class Street that represents a circular street and a positive
 * integer k which represents a maximum bound for the number of houses in that street (in other
 * words, the number of houses is less than or equal to k). Houses' doors could be open or closed
 * initially. Initially, you are standing in front of a door to a house on this street. Your task is
 * to count the number of houses in the street. The class Street contains the following functions
 * which may help you: void openDoor(): Open the door of the house you are in front of. void
 * closeDoor(): Close the door of the house you are in front of. boolean isDoorOpen(): Returns true
 * if the door of the current house is open and false otherwise. void moveRight(): Move to the right
 * house. void moveLeft(): Move to the left house. Return the number of houses on this street. TC -
 * O(k) SC - O1
 */


public class Street {

  public Street(int[] doors) {

  }

  public void openDoor() {

  }

  public void closeDoor() {

  }

  public boolean isDoorOpen() {
    return true;
  }

  public void moveRight() {

  }

  public void moveLeft() {

  }

  public int houseCount(Street street, int k) {
    while (k-- > 0) {
      street.openDoor();
      street.moveLeft();
    }
    int ans = 0;
    while (street.isDoorOpen()) {
      ans++;
      street.closeDoor();
      street.moveLeft();
    }
    return ans;
  }
}