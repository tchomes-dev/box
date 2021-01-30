package structures;

import java.util.Comparator;
import java.util.Iterator;

import comparators.ReverseIntegerComparator;

public class MinQueue<V> implements PriorityQueue<Integer, V> {
  private ReverseIntegerComparator comparator = new ReverseIntegerComparator();
  private StudentArrayHeap<Integer, V> heap = new StudentArrayHeap<>(comparator);

  @Override
  public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
    if (priority == null || value == null) {
      throw new NullPointerException();
    }
    heap.add(priority, value);
    // TODO Auto-generated method stub
    return this;
  }

  @Override
  public V dequeue() {
    // TODO Auto-generated method stub
    if (isEmpty()) {
      throw new NullPointerException();
    }
    V elem = heap.peek();
    heap.remove();
    return elem;
  }

  @Override
  public V peek() {
    // TODO Auto-generated method stub
    return heap.peek();
  }

  @Override
  public Iterator<Entry<Integer, V>> iterator() {
    // TODO Auto-generated method stub
    return heap.asList().iterator();
  }

  @Override
  public Comparator<Integer> getComparator() {
    // TODO Auto-generated method stub
    return this.comparator;
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return heap.size();
  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return heap.size() == 0;
  }
}
