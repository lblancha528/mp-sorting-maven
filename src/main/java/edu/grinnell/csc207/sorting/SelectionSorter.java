package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using selection sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Lily Blanchard
 */

public class SelectionSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator The order in which elements in the array should be ordered after sorting.
   */
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    int smallest = 0; // index of smallest element found

    // i = index of next unsorted element
    for (int i = 0; i < values.length; i++) {
      smallest = i;
      for (int j = i + 1; j < values.length; j++) {
        // if found an equals, stop and swap
        // if found a smaller, keep looking for more smaller!!!
        if (order.compare(values[i], values[j]) == 0) {
          // if equal, stop and swap
          smallest = j;
          break;
        } else if (order.compare(values[smallest], values[j]) > 0) {
          // when you find one that is smaller/ equal to that element
          smallest = j; // save its index
        } // if
      } // for
      T first = values[i];
      T second = values[smallest];
      values[i] = second;
      values[smallest] = first;
    } // for
  } // sort(T[])
} // class SelectionSorter
