import java.util.ArrayList;
import java.util.List;

public class DefaultSorter implements Sorter {

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
  }

  @Override
  public <E extends Comparable> void countingSort(List<E> list) {
  }

  @Override
  public <E extends Comparable> void bucketSort(List<E> list) {
  }

  @Override
  public <E extends Comparable> void radixSort(List<E> list) {
  }

  /** Swaps two elements in a list at the specified indexes.
   *  **/
  private <E> void swap(List<E> list, int i, int j) {
    E temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
  }
}
