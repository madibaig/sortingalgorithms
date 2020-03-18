import java.util.List;

public interface Sorter {

  <E extends Comparable> void insertionSort(List<E> list);

  <E extends Comparable> void selectionSort(List<E> list);

  <E extends Comparable> void mergeSort(List<E> list);

  <E extends Comparable> void quickSort(List<E> list);

  <E extends Comparable> void bubbleSort(List<E> list);

  <E extends Comparable> void heapSort(List<E> list);

  <E extends Comparable> void shellSort(List<E> list);

  <E extends Comparable> void combSort(List<E> list);
}
