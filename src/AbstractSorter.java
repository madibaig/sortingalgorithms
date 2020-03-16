import java.util.List;

public abstract class AbstractSorter {
  /** Using an abstract class rather than interface so that I can make the
   *  overwritten methods static for the implementations.
   *
   *  Sorters sort the list in ascending order.
   **/

  public abstract <E extends Comparable> List<E> insertionSort(List<E> list);

  public abstract <E extends Comparable> List<E> selectionSort(List<E> list);

  public abstract <E extends Comparable> List<E> mergeSort(List<E> list);

  public abstract <E extends Comparable> List<E> heapSort(List<E> list);

  public abstract <E extends Comparable> List<E> quickSort(List<E> list);

  public abstract <E extends Comparable> List<E> shellSort(List<E> list);

  public abstract <E extends Comparable> List<E> bubbleSort(List<E> list);

  public abstract <E extends Comparable> List<E> combSort(List<E> list);

  public abstract <E extends Comparable> List<E> countingSort(List<E> list);

  public abstract <E extends Comparable> List<E> bucketSort(List<E> list);

  public abstract <E extends Comparable> List<E> radixSort(List<E> list);

}
