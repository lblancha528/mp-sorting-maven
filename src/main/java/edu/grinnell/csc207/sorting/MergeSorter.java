package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Lily Blanchard
 */

public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    this.sections(values, 0, values.length);

  } // sort(T[])

  /**
   * Merge helper. Partitions sections.
   * 
   * @param start1 start of section 1
   * @param start2 start of section 2
   * @param mid the midpoint
   * @param end the endpoint
   * @param T[] the array
   */
  public void mergeHelper(int start1, int mid, int end, T[] values) {
    int start2 = mid + 1;

    if (order.compare(values[mid], values[start2]) <= 0) {
      // if direct merge is good
      return;
    } // if

    while (start1 <= mid && start2 <= end) {
      if (order.compare(values[start1], values[start2]) <= 0) {
        // if 1 <= 2, 1 is in the right spot, loop
        start1++;
      } else {
        T holdVal = values[start2];
        int movePos = start2;

        while (movePos != start1) {
          values[movePos] = values[movePos - 1];
          movePos--;
        } // while

        values[start1] = holdVal;
        start1++;
        start2++;
        mid++;
      } // if
    } // while
  } // mergeHelper(int, int, int, int, T[])

  /**
   * Tracks left and right index bounds for subsections.
   * 
   * @param values the array
   * @param l the left bound of array
   * @param r the right bound of array
   */
  public void sections(T[] values, int l, int r) {
    if (l < r) {
      int mid = l + (l - r) / 2;

      sections(values, l, mid);
      sections(values, mid + 1, r);

      this.mergeHelper(l, mid, r, values);
    } // if
  } // sections(T[], int, int)
} // class MergeSorter
