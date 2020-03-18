import java.util.ArrayList;
import java.util.List;

public class DefaultSorter implements Sorter {
  //Taken from https://en.wikipedia.org/wiki/Shellsort - Marcin Ciura's sequence
  private final int[] SHELLSORT_GAPS = {701, 301, 132, 57, 23, 10, 4, 1};
  //Taken from https://en.wikipedia.org/wiki/Comb_sort
  private final double COMBSORT_SHRINK = 1.3;

  @Override
  public <E extends Comparable> void insertionSort(List<E> list) {
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
  }

  @Override
  public <E extends Comparable> void selectionSort(List<E> list) {
    for (int i = 0; i < list.size(); i++) {
      int minimum = i;
      for (int j = i; j < list.size(); j++) {
        //find minimum
        if (list.get(j).compareTo(list.get(minimum)) < 0) {
          minimum = j;
        }
      }
      swap(list, minimum, i);
    }
  }

  @Override
  public <E extends Comparable> void mergeSort(List<E> list) {
    if (list.size() == 1) {
      return;
    }
    //Sort left side
    List<E> leftSide = list.subList(0, list.size() / 2);
    mergeSort(leftSide);
    //Sort right side
    List<E> rightSide = list.subList(list.size() / 2, list.size());
    mergeSort(rightSide);
    //Merge these two together
    merge(leftSide, rightSide);
  }

  private <E extends Comparable> void merge(List<E> leftList, List<E> rightList) {
    //Loop for the size of leftList and rightList
    //Keep putting the smallest of leftList/rightList (compare them) into merged
    //Add the other list's elements if the one is empty
    //Set the merged list elements to left and right lists in order

    int finalSize = leftList.size() + rightList.size();
    int leftI = 0, rightI = 0;
    List<E> mergedList = new ArrayList<>();

    for (int i = 0; i < finalSize; i++) {
      if (leftI >= leftList.size()) {
        mergedList.add(i, rightList.get(rightI));
        rightI++;
      } else if (rightI >= rightList.size()) {
        mergedList.add(i, leftList.get(leftI));
        leftI++;
      } else {
        if (leftList.get(leftI).compareTo(rightList.get(rightI)) > 0) {
          mergedList.add(i, rightList.get(rightI));
          rightI++;
        } else {
          mergedList.add(i, leftList.get(leftI));
          leftI++;
        }
      }
    }

    //Copy the stuff from, merged list to left and right
    for (int i = 0; i < finalSize; i++) {
      if (i >= leftList.size()) {
        rightList.set(i - leftList.size(), mergedList.get(i));
      } else {
        leftList.set(i, mergedList.get(i));
      }
    }
  }

  @Override
  public <E extends Comparable> void quickSort(List<E> list) {
    //If the size of list is 1 or 0, return it
    //Pick the pivot as the middle element
    //From the start and end of the list's elements go inwards
    //(using Hoare partition scheme as found on wikipedia for quicksort)

    if (list.size() == 1 || list.size() == 0) {
      return;
    }

    E pivot = list.get((list.size() / 2) - 1);
    int left = -1; int right = list.size();
    int nextPivot;
    while (true) {
      do {
        left++;
      } while (list.get(left).compareTo(pivot) < 0);
      do {
        right--;
      } while (list.get(right).compareTo(pivot) > 0);
      if (left >= right) {
        nextPivot = right + 1;
        break;
      }
      swap(list, left, right);
    }

    quickSort(list.subList(0, nextPivot));
    quickSort(list.subList(nextPivot, list.size()));
  }

  @Override
  public <E extends Comparable> void heapSort(List<E> list) {
    buildMaxHeap(list);

    int endOfUnsorted = list.size() - 1;

    for (E e : list) {
      swap(list, 0, endOfUnsorted);
      endOfUnsorted--;
      fixMaxHeap(list.subList(0, endOfUnsorted - 1));
    }
  }

  private <E extends Comparable> void buildMaxHeap(List<E> list) {
    //TODO: buildMaxHeap out of a list for use in heapsort
  }

  private <E extends Comparable> void fixMaxHeap(List<E> subList) {
    /*TODO: implement a fixMaxHeap algorithm where the heap is represented by a
      list */
  }

  @Override
  public <E extends Comparable> void shellSort(List<E> list) {
    for (int gap : SHELLSORT_GAPS) {
      if (gap >= list.size()) { //If gap is >= than the list size, skip it
        continue;
      }

      for (int i = gap; i < list.size(); i++) {
        E temp = list.get(i);
        int j;
        for (j = i;
            j >= gap && list.get(j - gap).compareTo(temp) > 0;
            j -= gap) {
          list.set(j, list.get(j - gap));
        }
        list.set(j, temp);
      }
    }
  }

  @Override
  public <E extends Comparable> void bubbleSort(List<E> list) {
    boolean swapped = false;
    do {
      swapped = false;
      for (int i = 1; i < list.size(); i++) {
        if (list.get(i - 1).compareTo(list.get(i)) > 0) {
          swap(list, i - 1, i);
          swapped = true;
        }
      }
    } while (swapped);
  }

  @Override
  public <E extends Comparable> void combSort(List<E> list) {
    int gap = list.size();
    boolean sorted = false;

    while (!sorted) {
      gap = (int) (gap / COMBSORT_SHRINK);

      if (gap < 1) {
        gap = 1;
        sorted = true;
      }

      int i = 0;
      while (i + gap < list.size()) {
        if (list.get(i).compareTo(list.get(i + gap)) > 0) {
          swap(list, i, i + gap);
          sorted = false;
        }
        i++;
      }
    }
  }

  /** isSorted takes a list and returns true if it is sorted in ascending order
   *  */
  public <E extends Comparable> boolean isSorted(List<E> list) {
    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i).compareTo(list.get(i + 1)) > 0) {
        return false;
      }
    }
    return true;
  }

  /** Swaps two elements in a list at the specified indexes.
   *  **/
  private <E> void swap(List<E> list, int i, int j) {
    E temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
  }
}
