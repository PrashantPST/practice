package dsa.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BinaryTree {
    public BinaryTree left;
    public BinaryTree right;
    public int value;
}
