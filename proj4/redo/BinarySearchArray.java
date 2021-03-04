import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

interface BinarySearch{
  public int binarySearch(int key);
  public void add(int value);
  public void remove(int index);
  public boolean contains(int value);
  public void printElements();
  public void initializeArray();
  public void sort();
}

class BinarySearchArrayList implements BinarySearch {
  private ArrayList<Integer> arrList = new ArrayList<>();
  @Override
  public int binarySearch(int key){return Collections.binarySearch(arrList,key);}
  @Override
  public void add(int value){arrList.add(value);}
  @Override
  public void remove(int index){arrList.remove(index);}
  @Override
  public boolean contains(int value){return arrList.contains(Integer.valueOf(value));}
  @Override
  public void printElements(){System.out.println(Arrays.toString(arrList.toArray()));}
  @Override
  public void initializeArray(){
    Random rd = new Random();
    for(int i=0;i<10;i++){
      add(rd.nextInt(25));
    }
  }
  @Override
  public void sort(){Collections.sort(arrList);}
}

public class BinarySearchArray implements BinarySearch {
  int arr[] = new int[15];
  public int binarySearch(int key){return Arrays.binarySearch(arr, key);}
  public void add(int value){
    if(contains(0))arr[binarySearch(0)] = value;
    else System.out.println("No room in array");
    sort();
  }
  public void remove(int index){
    for (int i=index;i>0;i--){
      arr[i] = arr[i-1];
      if (i-1==0) arr[0]=0;
    }
  }
  public boolean contains(int value){return Arrays.binarySearch(arr, value) > 0 ? true : false;}
  public void printElements(){System.out.println(Arrays.toString(arr));}
  public void initializeArray(){
    Random rd = new Random();
    for(int i=0;i<10;i++){
      add(rd.nextInt(25));
    }
  }
  public void sort(){Arrays.sort(arr);}

  public static void testBinarySearchArray(BinarySearch searchObject) {
    Scanner scanner = new Scanner(System.in);
    String typeName = searchObject.getClass().getSimpleName();

    System.out.println(typeName+" tester");
    int testin = 0;
    while (true){
      System.out.println("Enter an integer to search (or -1 to quit): ");
      testin = scanner.nextInt();
      if (testin == -1) break;
      if (searchObject.contains(testin)){
        System.out.println(testin+" is present. Remove it? [y/n]");
        char response = scanner.next().charAt(0);
        if (response=='y') searchObject.remove(searchObject.binarySearch(testin));
      }
      else {
        System.out.println(testin+" is not present. Add it? [y/n]");
        char response = scanner.next().charAt(0);
        if (response=='y') searchObject.add(testin);
      }
      searchObject.printElements();
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
