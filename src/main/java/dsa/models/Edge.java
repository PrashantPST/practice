package dsa.models;

public class Edge implements Comparable<Edge> {

  public int pointIndex;
  public int weight;

  public Edge(int pointIndex, int weight) {
    this.pointIndex = pointIndex;
    this.weight = weight;
  }

  @Override
  public int compareTo(Edge another) {
    return this.weight - another.weight;
  }
}