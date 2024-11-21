package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;
import edu.grinnell.csc207.util.ArrayUtils;

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

  /**
   * The random number generator.
   */
  Random rand = new Random();

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
    sort(values, 0, values.length);
  } // sort(T[])

  /**
   * Does the recursion.
   * 
   * @param T[] the array to be sorted
   * @param int the lower bound
   * @param int the upper bound
   */
  public void sort(T[] values, int lower, int upper) {
    if (lower < upper && lower > -1 && upper > -1) {
      int bigger = partition(values, lower, upper);

      sort(values, lower, bigger);
      sort(values, bigger, upper);
    } // if bounds are valid
  } // quickHelper(T[], int, int)

  /**
   * Does the partitioning.
   * 
   * @param T[] the array
   * @param int lower bound
   * @param int upper bound
   * @return the index before the bigger range
   */
  public int partition(T[] values, int lower, int upper) {
    int bigger = upper - 1; // next index to place a bigger element at
    int current = lower; // index of next unsorted element
    
    if (bigger == lower) {
      return lower;
    } // if only sorting one element

    int randomNum = rand.nextInt(upper - lower - 1); // index of pivot, cannot be upper
    T pivot = values[randomNum + lower]; // value of pivot

    // put pivot at front
    ArrayUtils.swap(values, randomNum + lower, lower);

    while (current <= bigger) {
      if (order.compare(values[current], pivot) <= 0) {
        // if element is smaller than pivot, put in smaller section
        current++;
      } else if (order.compare(values[current], pivot) > 0) {
        // if element is bigger than pivot, put in bigger section
        ArrayUtils.swap(values, current, bigger);
        bigger--;
      } // if
    } // while

    // put pivot in place
    ArrayUtils.swap(values, bigger, lower);

    return bigger;
  } // sort(T[], int, int)
} // class Quicksorter
