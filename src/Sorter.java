import java.util.ArrayList;
import java.util.List;

public interface Sorter {

  default <E extends Comparable> List<E> insertionSort(List<E> list) {
    for (int i = 1; i < list.size(); i++) {
      E currElem = list.get(i);
      int j = i - 1;

      while (j >= 0 && currElem.compareTo(list.get(j)) < 0) {
        E temp = list.get(j);
        list.set(j, currElem);
        list.set(j + 1, temp);
        j--;
      }
    }

    return list;
  }

  default <E extends Comparable> List<E> selectionSort(List<E> list) {
    for (int i = 0; i < list.size(); i++) {
      int minimum = i;
      for (int j = i; j < list.size(); j++) {
        //find minimum
        if (list.get(j).compareTo(list.get(minimum)) < 0) {
          minimum = j;
        }
      }
      //swap minimum at i position
      E temp = list.get(i);
      list.set(i, list.get(minimum));
      list.set(minimum, temp);
    }

    return list;
  }

  default <E extends Comparable> List<E> mergeSort(List<E> list) {
    //Split the list into multiple sublists of one recursively
    //Merge them back together

    ArrayList<E>


    return null;
  }

  default <E extends Comparable> List<E> heapSort(List<E> list) {
    return null;
  }

  default <E extends Comparable> List<E> quickSort(List<E> list) {
    return null;
  }

  default <E extends Comparable> List<E> bubbleSort(List<E> list) {
    return null;
  }

  default <E extends Comparable> List<E> combSort(List<E> list) {
    return null;
  }

  default <E extends Comparable> List<E> countingSort(List<E> list) {
    return null;
  }

  default <E extends Comparable> List<E> bucketSort(List<E> list) {
    return null;
  }

  default <E extends Comparable> List<E> radixSort(List<E> list) {
    return null;
  }
}
