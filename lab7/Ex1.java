import java.util.Stack;
import java.util.Scanner;

public class Ex1 {
  public static void main(String[] args) {
    Ex1 program = new Ex1();
    Scanner s = new Scanner(System.in);
    int start = s.nextInt();
    int result = program.stackReverse(start);
    System.out.println(result);
  }
  int stackReverse(int n){
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    while (n>0){
      stack.push(n%10);
      n = n/10;
    }

    while (!stack.empty())stack2.push(stack.pop());
    int x=0;
    while (!stack2.empty()) {
      x= x*10;
      x += stack2.pop();
    }
    return x;
  }
}
