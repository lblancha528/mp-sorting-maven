package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Lily Blanchard
 */

public class Quicksorter<T> implements Sorter<T> {
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
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    quickHelper(values, 0, values.length);
  } // sort(T[])

  /**
   * Does the recursion.
   * @param T[] the array to be sorted
   * @param int the lower bound
   * @param int the upper bound
   */
  public void quickHelper(T[]values, int lower, int upper) {
    int smaller = lower; // next index to place a smaller element at
    int bigger = upper - 1; // next index to place a bigger element at
    int current = lower; // index of next unsorted element

    Random rand = new Random();
    int randomNum = rand.nextInt(upper);
    T pivot = values[randomNum]; // value of the pivot

    while (current < bigger) {
      if (order.compare(values[current], pivot) < 0) {
        // if element is smaller than pivot, put in smaller section
        T first = values[current];
        T second = values[smaller];
        values[current] = second;
        values[smaller] = first;
        smaller++;
        current++;
      } else if (order.compare(values[current], pivot) > 0) {
        // if element is bigger than pivot, put in bigger section
        T first = values[current];
        T second = values[bigger];
        values[current] = second;
        values[bigger] = first;
        bigger--;
        current++;
      } else {
        // else, element is equal, inc current
        current++;
      } // if
    } // while

    quickHelper(values, lower, smaller);
    quickHelper(values, bigger + 1, upper);
  } // quickHelper(T[], int, int)
} // class Quicksorter
