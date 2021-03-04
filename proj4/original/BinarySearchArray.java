import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

interface BinarySearch {
  public int binarySearch(int key);

  public void printElements();

  public void remove(int index);

  public void add(int value);

  public boolean contains(int value);

  public void initializeArray();

  public void sort();
}

class BinarySearchArrayList implements BinarySearch {

  private ArrayList<Integer> arrList = new ArrayList<>();

  @Override
  public int binarySearch(int key) {
    return Collections.binarySearch(arrList, key);
  }

  @Override
  public void sort() {
    Collections.sort(arrList);
  }

  @Override
  public void printElements() {
    for (Integer num : arrList) {
      System.out.print(num + " ");
    }
    System.out.println();
  }

  @Override
  public void remove(int index) {
    arrList.remove(index);
  }

  @Override
  public void add(int value) {
    if (!contains(value)) {
      arrList.add(value);
    } else {
      System.out.println("Item " + value + " already exists in list. Not added.");
    }
  }

  @Override
  public boolean contains(int value) {
    return arrList.contains(Integer.valueOf(value));
  }

  @Override
  public void initializeArray() {
    Random rand = ThreadLocalRandom.current();
    int max = 25, min = 1;
    int added = 0;
    while (added < 10) {
      int num = rand.nextInt((max - min) + 1) + min;
      if (!contains(num)) {
        arrList.add(num);
        added++;
      }
    }
  }
}

public class BinarySearchArray implements BinarySearch {

  int arr[] = new int[15];

  public int binarySearch(int key) {
    return Arrays.binarySearch(arr, key);
  }

  public void sort() {
    Arrays.sort(arr);
  }

  public void printElements() {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public void remove(int index) {
    for (int i = index; i < arr.length - 1; i++) {
      arr[i] = arr[i + 1];
    }
    arr[arr.length - 1] = 0;
    Arrays.sort(arr);
    printElements();
  }


  public void add(int value) {
    if (contains(value)) {
      return;
    }
    if (!contains(0)) {
      System.out.println("No space available.");
    }
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == 0) {
        arr[i] = value;
        break;
      }
    }
    sort();
    printElements();
  }

  public void initializeArray() {
    for (int i = 0; i < 15; i++) {
      arr[i] = 0;
    }
    Random rand = ThreadLocalRandom.current();
    int max = 25, min = 1;
    int numToAdd = 10, added = 0;
    while (added < 10) {
      int num = rand.nextInt((max - min) + 1) + min;
      if (!contains(num)) {
        for (int i = 0; i < arr.length; i++) {
          if (arr[i] == 0) {
            arr[i] = num;
            break;
          }
        }
        added++;
      }
    }
  }

  public boolean contains(int value) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == value)
      return true;
    }
    return false;
  }

  public static void testBinarySearchArray(BinarySearch searchObject) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("\nWelcome to the Array List Test.");
    int value = 0;

    try {
      System.out.print("Enter an integer to search (or -1 to quit): ");
      String ss = scanner.nextLine();
      if (ss.equals( "-1"))
      return;
      value = Integer.parseInt(ss);
      do {
        int index;
        if ((index = searchObject.binarySearch(value)) >= 0) {
          System.out.println("Value " + value + " found." + " Do you want to remove it? y/n? ");
          String answer = scanner.nextLine();
          if (answer.equals("y")) {
            searchObject.remove(index);
          }
        } else {
          System.out.println("Value " + value + " not found." + " Do you want to add it? y/n? ");
          String answer = scanner.nextLine();
          if (answer.equals("y"))
          searchObject.add(value);
        }
        System.out.print("Enter an integer to search (or -1 or <Control> D to quit): ");
        ss = scanner.nextLine();
        value = Integer.parseInt(ss);
      }
      while (value != -1);
      System.out.println("Goodbye...");
    } catch (NoSuchElementException e) {
      System.out.println("Goodbye...");
    }
  }

  public static void main(String[] args) {
    BinarySearchArray bsArr = new BinarySearchArray();
    bsArr.initializeArray();
    bsArr.sort();
    bsArr.printElements();
    BinarySearchArray.testBinarySearchArray(bsArr);

    BinarySearchArrayList bsArrList = new BinarySearchArrayList();
    bsArrList.initializeArray();
    bsArrList.sort();
    bsArrList.printElements();
    BinarySearchArray.testBinarySearchArray(bsArrList);
  }
}
