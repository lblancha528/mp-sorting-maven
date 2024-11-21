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
    this.sortHelper(values, 0, values.length - 1);

  } // sort(T[])

  /**
   * Merge helper. Partitions sections.
   *
   * @param start1 start of section 1
   * @param mid the midpoint
   * @param end the endpoint
   * @param values the array
   */
  public void mergeHelper(int start1, int mid, int end, T[] values) {

    // Build subarrays that are copies of what's to be merged
    int rightSize = mid - start1 + 1;
    int leftSize = end - mid;
    T[] rightSide = (T[]) new Object[rightSize];
    T[] leftSide = (T[]) new Object[leftSize];

    // fill right copy
    for (int i = 0; i < rightSide.length; i++) {
      rightSide[i] = values[i + start1];
    } // for
    // fill left copy
    for (int j = 0; j < leftSide.length; j++) {
      leftSide[j] = values[j + mid + 1];
    } // for

    // initial indexes for each subarray
    int r = 0;
    int l = 0;
    // initial index for main array
    int main = start1;

    while (r < rightSide.length && l < leftSide.length) {
      if (order.compare(leftSide[l], rightSide[r]) <= 0) {
        // if l <= r, move l to main, inc l
        values[main] = leftSide[l];
        main++;
        l++;
      } else {
        // if l > r, move r to main, inc r
        values[main] = rightSide[r];
        main++;
        r++;
      } // if
    } // while

    // move remaining elements to main, left side first
    while (l < leftSide.length) {
      values[main] = leftSide[l];
      main++;
      l++;
    } // while

    while (r < rightSide.length) {
      values[main] = rightSide[r];
      main++;
      r++;
    } // while
  } // mergeHelper(int, int, int, int, T[])

  /**
   * Tracks left and right index bounds for subsections.
   *
   * @param values the array
   * @param l the left bound of array
   * @param r the right bound of array
   */
  public void sortHelper(T[] values, int l, int r) {
    if (l < r) {
      int mid = l + (r - l) / 2;

      sortHelper(values, l, mid);
      sortHelper(values, mid + 1, r);

      this.mergeHelper(l, mid, r, values);
    } // if
  } // sections(T[], int, int)

  /*
   * 1: give the whole array with upper and lower bounds to sections 2: find the midpoint 3: give
   * the whole array
   */
} // class MergeSorter
