import java.util.ArrayList;
import java.util.stream.IntStream;

public class RunSorterImplementations {

  public static void main(String[] args) {
    ArrayList<Integer> listToSort = new ArrayList<>();
    ArrayList<Integer> sortedList = new ArrayList<>();
    DefaultSorter defaultSorter = new DefaultSorter();

    listToSort.add(0, 3);
    listToSort.add(1, 10);
    listToSort.add(2, 4);
    listToSort.add(3, 1);
    listToSort.add(4, 7);
    listToSort.add(5, 6);
    listToSort.add(6, 5);
    listToSort.add(7, 8);
    listToSort.add(8, 9);
    listToSort.add(9, 2);
    IntStream.rangeClosed(1, 10).forEach(n -> {
          sortedList.add(n - 1, n);
        }
    );

    defaultSorter.mergeSort(listToSort);

    System.out.println(listToSort);
    System.out.println(listToSort.equals(sortedList));
  }

}
