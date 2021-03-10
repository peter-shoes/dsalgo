import java.time.LocalDate;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Iterator;

enum Priority {
  URGENT,
  HIGH,
  NORMAL,
  LOW
}
enum Status {
  NOT_STARTED,
  IN_PROGRESS,
  WAITING,
  DEFERRED
}

// interface Comparable<Task> {}

class Task implements Comparable<Task> {
  int taskId;
  String subject;
  Priority priority;
  Status status;
  LocalDate startDate;
  LocalDate dueDate;

  public Task(int taskId, String subject, Priority priority, Status status, LocalDate startDate, LocalDate dueDate) {
    this.taskId = taskId;
    this.subject = subject;
    this.priority = priority;
    this.status = status;
    this.startDate = startDate;
    this.dueDate = dueDate;
  }
  public int getTaskId() {
    return taskId;
  }
  public Priority getPriority() {
    return priority;
  }
  @Override
  public java.lang.String toString() {
    return "\nTask Id: " + taskId +
            "\nSubject: " + subject +
            "\nPriority: " + priority +
            "\nStatus: " + status +
            "\nStart Date: " + startDate +
            "\nDue Date: " + dueDate + "\n";
  }
  @Override
  public int compareTo(Task task) {
    return this.getPriority().compareTo(task.getPriority());
  }
}

public class ToDoList {
  PriorityQueue<Task> taskPriorityQueue = new PriorityQueue<>();
  Scanner scanner = new Scanner(System.in);
  public static void main(String[] args) {
    ToDoList tdl = new ToDoList();
    tdl.testDriver();
  }
  void testDriver(){
    System.out.println("Welcome to My Task List\n");
    System.out.println("Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q): ");
    String menuItem = scanner.nextLine();
    do {
      switch (menuItem) {
        case "a":addTask();break;
        case "n":displayNextTask();break;
        case "l":showTaskList();break;
        case "e":
          System.out.println("Enter taskId: ");
          int taskId = scanner.nextInt();
          Task task = getTaskById(taskId);
          editTask(task);
          break;
        case "d":
          System.out.println("Enter taskId: ");
          taskId = scanner.nextInt();
          task = getTaskById(taskId);
          showTaskDetail(task);
          break;
        case "r":
          System.out.println("Enter taskId: ");
          taskId = scanner.nextInt();
          removeTask(taskId);
          break;
        case "q":
          break;
      }
      System.out.println("Choose action (Add(a),Next(n),List(l),Detail(d),Edit(e),Remove(r),Quit(q): ");
      menuItem = scanner.nextLine();
      if (menuItem.equals("q"))break;
    }
    while (menuItem != "q");System.out.println("Goodbye");
  }
  private void addTask(){
    int taskId = taskPriorityQueue.size()+1;
    System.out.println("Enter Subject:");
    String subject = scanForSubject();
    Priority priority = scanForPriority();
    Status status = scanForStatus();
    LocalDate startDate = scanForDate("Start");
    LocalDate dueDate = scanForDate("Due");
    Task task = new Task(taskId, subject, priority, status, startDate, dueDate);
    if (taskPriorityQueue.offer(task));
    else System.out.println("PriorityQueue full");
  }
  private Priority scanForPriority() {
    System.out.println("Enter priority abbreviation Normal=n,Low=l,High=h,Urgent=u): ");
    String abbrev = scanner.nextLine();
    Priority priority = Priority.NORMAL;
    switch (abbrev) {
      case "n":
        priority = Priority.NORMAL;break;
      case "l":
        priority = Priority.LOW;break;
      case "h":
        priority = Priority.HIGH;break;
      case "u":
        priority = Priority.URGENT;break;
    }
    return priority;
  }
  private Status scanForStatus() {
    System.out.println("Enter status abbreviation Not Started=n,In Progress=i,Waiting=w,Deferred=d): ");
    String abbrev = scanner.nextLine();
    Status status = Status.NOT_STARTED;
    switch (abbrev) {
      case "n":
        status = Status.NOT_STARTED;break;
      case "i":
        status = Status.IN_PROGRESS;break;
      case "w":
        status = Status.WAITING;break;
      case "d":
        status = Status.DEFERRED;break;
    }
    return status;
  }
  private LocalDate scanForDate(String typeOfDate){
    System.out.println("Enter "+typeOfDate+" Date (YYYY-MM-DD):");
    LocalDate date = LocalDate.parse(scanner.nextLine());
    return date;
  }
  String scanForSubject() {
    return scanner.nextLine();
  }

  public Task getTaskById(int taskId) {
    if (!taskPriorityQueue.isEmpty()){
      Iterator<Task> iter = taskPriorityQueue.iterator();
      while(iter.hasNext()){
        Task currentTask = iter.next();
        if (currentTask.taskId==taskId) return currentTask;
      }
      System.out.println("No task with Id "+taskId);
      return null;
    }
    System.out.println("PriorityQueue empty");
    return null;
  }

  public void displayNextTask() {
    Task nextTask = taskPriorityQueue.peek();
    if (nextTask != null) showTaskDetail(nextTask);
    else System.out.println("PriorityQueue is empty.");
  }
  public void showTaskList() {
    System.out.println(taskPriorityQueue);
  }
  public void editTask(Task task) {
    showTaskDetail(task);
    System.out.println("Edit what? (s)ubject, (p)riority, s(t)atus, st(a)rt date, (d)ue date");
    String choice = scanner.nextLine();
    System.out.println("Change to what?");
    String newVal = scanner.nextLine();
    switch(choice){
      case("s"):task.subject=newVal;break;
      case("p"):{
        switch (newVal) {
          case "n":
            task.priority = Priority.NORMAL;break;
          case "l":
            task.priority = Priority.LOW;break;
          case "h":
            task.priority = Priority.HIGH;break;
          case "u":
            task.priority = Priority.URGENT;break;
        }
        break;
      }
      case("t"):{
        switch (newVal) {
          case "n":
            task.status = Status.NOT_STARTED;break;
          case "i":
            task.status = Status.IN_PROGRESS;break;
          case "w":
            task.status = Status.WAITING;break;
          case "d":
            task.status = Status.DEFERRED;break;
        }
        break;
      }
      case("a"):task.startDate = LocalDate.parse(newVal);break;
      case("d"):task.dueDate = LocalDate.parse(newVal);break;
    }
  }
  public void showTaskDetail(Task task) {
    System.out.println(task);
  }
  public void removeTask(int taskId) {
    if (!taskPriorityQueue.isEmpty()){
      Task currentTask = taskPriorityQueue.peek();
      for (int i = 0; i<taskPriorityQueue.size()-1;i++) {
        if (currentTask.taskId == taskId) taskPriorityQueue.remove(currentTask);
      }
      System.out.println("No task with Id "+taskId);
    }
    System.out.println("PriorityQueue empty");
  }
}
