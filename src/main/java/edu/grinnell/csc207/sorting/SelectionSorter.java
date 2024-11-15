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
    // index where the next 'sorted' element will go
    int sorted = 0;
    for (int i = 0; i < values.length; i++) {
      int smallest = findSmallest(values, sorted);
      T first = values[sorted];
      T second = values[smallest];
      values[sorted] = second;
      values[smallest] = first;
      sorted++;
    } // for
  } // sort(T[])

  /**
  * Finds the next smallest element in the array.
  * @param T[] the array to be searched
  * @param int the 
  * @return int index of smallest element
  */
  public int findSmallest(T[] values, int start) {
    int smallest = start; // index of smallest element found

    for (int j = start + 1; j < values.length; j++) {
      if (order.compare(values[smallest], values[j]) == 0) {
        // if equal, stop and return
        smallest = j;
        return smallest;
      } else if (order.compare(values[smallest], values[j]) > 0) {
        // when you find one that is smaller/ equal to that element
        smallest = j; // save its index
      } // if
    } // for

    return smallest;
  } // findSmallest(T[], int)
  
} // class SelectionSorter
