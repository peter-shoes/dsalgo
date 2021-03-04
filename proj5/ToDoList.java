import java.time.LocalDate;
import java.util.PriorityQueue;
import java.util.Scanner;

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

class Task implements Comparable<Task> {
  int taskId;
  String subject;
  Priority priority;
  Status status;
  LocalDateTime startDate;
  LocalDateTime dueDate;

  public Task(int taskId, String subject, Priority priority, Status status, LocalDateTime startDate, LocalDateTime dueDate) {
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
    return "Task{" +
            "taskId=" + taskId +
            ", subject='" + subject + '\'' +
            ", priority=" + priority +
            ", status=" + status +
            ", startDate=" + startDate +
            ", dueDate=" + dueDate +
            '}';
  }
  @Override
  public int compareTo(Task task) {
    return this.getPriority().compareTo(task.getPriority());
  }
}

public class ToDoList {
  public static void main(String[] args) {
    PriorityQueue<Task> taskPriorityQueue = new PriorityQueue<>();
    Scanner scanner = new Scanner(System.in);
    ToDoList tdl = new ToDoList();
    tdl.testDriver();
  }
  void testDriver(){

  }
  void addTask(){
    
  }
  private Priority scanForPriority() {
    System.out.println("Enter priority abbreviation Normal=n,Low=l,High=h,Urgent=u): ");String abbrev = scanner.nextLine();
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
    System.out.println("Enter status abbreviation Not Started=n,In Progress=i,Waiting=w,Deferred=d): ");String abbrev = scanner.nextLine();
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
}
