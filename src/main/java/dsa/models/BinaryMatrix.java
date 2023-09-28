package dsa.models;

import java.util.List;

public interface BinaryMatrix {
    int get(int row, int col);

    List<Integer> dimensions();
}
