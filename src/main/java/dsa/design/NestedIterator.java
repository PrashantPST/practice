package dsa.design;

import dsa.models.NestedInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedIterator implements Iterator<Integer> {

  Queue<Integer> q;

  public NestedIterator(List<NestedInteger> nestedList) {
    q = new LinkedList<>();
    dfs(nestedList);
  }

  private void dfs(List<NestedInteger> nestedList) {
    for (NestedInteger ni : nestedList) {
      if (ni.isInteger()) {
        q.add(ni.getInteger());
      } else {
        dfs(ni.getList());
      }
    }
  }

  @Override
  public Integer next() {
    return q.poll();
  }

  @Override
  public boolean hasNext() {
    return !q.isEmpty();
  }
}
