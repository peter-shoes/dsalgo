import java.util.Comparator;
import java.util.Scanner;
import java.io.*;
import java.util.SortedSet;
import java.util.TreeSet;

class PhonebookData  implements Comparable {
  String name;
  String mobilePhone;

  public PhonebookData(String name, String mobilePhone) {
    this.name = name;
    this.mobilePhone = mobilePhone;
  }
  public String getName() {
    return name;
  }
  public String getMobilePhone() {
    return mobilePhone;
  }
  public String toString() {
    return name + " " + mobilePhone;
  }
  public static int compare(String str1, String str2) {
    int l1 = str1.length();
    int l2 = str2.length();
    int lmin = Math.min(l1, l2);
    for (int i = 0; i < lmin; i++) {
      int str1_ch = (int) str1.charAt(i);
      int str2_ch = (int) str2.charAt(i);
      if (str1_ch != str2_ch) {
        return str1_ch - str2_ch;
      }
    }
    if (l1 != l2) {
      return l1 - l2;
    }
    else {
      return 0;
    }
  }
  @Override
  public int compareTo(Object o) {
    PhonebookData pd = (PhonebookData)o;
    return  compare(this.name,pd.name);
  }
}

public class DoublyLinkedList {
  class Node{
    PhonebookData data;
    Node previous;
    Node next;
    public Node(PhonebookData data) {
      this.data = data;
    }
  }

  Node head, tail = null;

  public void addNode(PhonebookData data) {
    Node newNode = new Node(data);
    if(head == null) {
      head = tail = newNode;
      head.previous = null;
      tail.next = null;
    }
    else {
      tail.next = newNode;
      newNode.previous = tail;
      tail = newNode;
      tail.next = null;
    }
  }

  public void display() {
    Node current = head;
    if(head == null) {
      System.out.println("List is empty");
      return;
    }
    System.out.println("Nodes of doubly linked list: ");
    while(current != null) {
      System.out.println(current.data + " ");
      current = current.next;
    }
  }

  private void readIntoDLL(DoublyLinkedList dList) throws FileNotFoundException {
    File file = new File("PhonebookData.txt");
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()){
      String line = scanner.nextLine();
      String[] rawData = line.split(",");
      PhonebookData rawNode = new PhonebookData(rawData[0],rawData[1]);
      dList.addNode(rawNode);
    }
    scanner.close();
  }

  public SortedSet search(String searchItem){
    SortedSet sortedSet = new TreeSet();
    Node current = head;
    while (current!=null){
      if (current.data.name.toLowerCase().contains(searchItem.toLowerCase()) || current.data.mobilePhone.toLowerCase().contains(searchItem.toLowerCase())) {
        sortedSet.add((PhonebookData) current.data);
      }
      current = current.next;
    }
    if (!sortedSet.isEmpty()) return sortedSet;
    return null;
  }



  public SortedSet tailFirstSearch(String searchItem) {
    SortedSet sortedSet = new TreeSet();
    Node current = tail;
    while (current!=null){
      if (current.data.name.toLowerCase().contains(searchItem.toLowerCase()) || current.data.mobilePhone.toLowerCase().contains(searchItem.toLowerCase())) {
        sortedSet.add((PhonebookData) current.data);
      }
      current = current.previous;
    }
    if (!sortedSet.isEmpty()) return sortedSet;
    return null;
  }

  public static void main(String[] args)throws FileNotFoundException{
    DoublyLinkedList dList = new DoublyLinkedList();
    Scanner scanner = new Scanner(System.in);
    dList.readIntoDLL(dList);
    dList.display();
    System.out.println("");
    System.out.println("DOING A HEAD FIRST SEARCH!!!!");
    System.out.println("Enter a term to search for or 'quit' to quit:");
    String searchTerm = scanner.nextLine().trim();

    while (!searchTerm.equals("quit")){
      SortedSet sortedSet = dList.search(searchTerm);
      if (sortedSet!=null){
        System.out.println("Found the following for search term '"+searchTerm+"':");
        for(Object node: sortedSet) {
          System.out.println(((PhonebookData) node).toString());
        }
      }
      else System.out.println("No results for search term '"+searchTerm+"'. Sorry.");
      System.out.println("Enter a term to search for or 'quit' to quit:");
      searchTerm = scanner.nextLine().trim();
    }
    System.out.println("Goodbye!");
    System.out.println("");
    System.out.println("DOING A TAIL FIRST SEARCH!!!!");
    System.out.println("Enter a term to search for or 'quit' to quit:");
    searchTerm = scanner.nextLine().trim();

    while (!searchTerm.equals("quit")){
      SortedSet sortedSet = dList.tailFirstSearch(searchTerm);
      if (sortedSet!=null){
        System.out.println("Found the following for search term '"+searchTerm+"':");
        for(Object node: sortedSet) {
          System.out.println(((PhonebookData) node).toString());
        }
      }
      else System.out.println("No results for search term '"+searchTerm+"'. Sorry.");
      System.out.println("Enter a term to search for or 'quit' to quit:");
      searchTerm = scanner.nextLine().trim();
    }
    System.out.println("Goodbye! For Real!");
  }
}
