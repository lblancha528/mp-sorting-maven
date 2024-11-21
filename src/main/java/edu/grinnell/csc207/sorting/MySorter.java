package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;
import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using version of Quicksort with an improved pivot, ie pivot is the average
 * of 3 random indices within range.
 *
 * @param <T> the type of values to be sorted
 *
 * @author Lily Blanchard
 * @author Samuel Rebelsky Based on Quicksorter framework written by SamR
 */
public class MySorter<T> implements Sorter<T> {
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
  public MySorter(Comparator<? super T> comparator) {
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
   * @param values the array to be sorted
   * @param lower the lower bound
   * @param upper the upper bound
   */
  public void sort(T[] values, int lower, int upper) {
    if (lower < upper && lower > -1 && upper > -1) {
      int bigger = partition(values, lower, upper);

      sort(values, lower, bigger);
      sort(values, bigger + 1, upper);
    } // if bounds are valid
  } // quickHelper(T[], int, int)

  /**
   * Does the partitioning.
   *
   * @param values the array
   * @param lower lower bound
   * @param upper upper bound
   * @return the index before the bigger range
   */
  public int partition(T[] values, int lower, int upper) {
    int bigger = upper - 1; // next index to place a bigger element at
    int current = lower; // index of next unsorted element

    if (bigger == lower) {
      return lower;
    } // if only sorting one element

    // three indices to be averaged, cannot be upper
    int randomNum1 = rand.nextInt(upper - lower - 1);
    int randomNum2 = rand.nextInt(upper - lower - 1);
    int randomNum3 = rand.nextInt(upper - lower - 1);
    int pivotIndex = (randomNum1 + randomNum2 + randomNum3) / 3;
    T pivot = values[pivotIndex]; // value of pivot

    // put pivot at front
    ArrayUtils.swap(values, pivotIndex, lower);

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
