import java.util.Stack;
import java.util.Scanner;
import java.util.ArrayList;

public class Ex2 {
  public static void main(String[] args) {
    Ex2 program = new Ex2();
    Scanner s = new Scanner(System.in);
    // String start = s.nextLine();
    String start = "Mary had a little lamb. His fleece was white as snow.";
    String result = program.sentenceReverse(start);
    System.out.println(result);
  }

  String sentenceReverse(String n) {
    Stack<String> sentence = new Stack<String>();
    Stack<String> fullText = new Stack<String>();

    Scanner scan = new Scanner(n);
    while (scan.hasNext()){
      sentence.push(scan.next());
    }

    String reverseSentence = "";
    boolean firstDot = false;
    while(!sentence.empty()){

      if (sentence.peek().contains(".") && firstDot==false){
        String newStr = sentence.pop();
        newStr = newStr.substring(0,1).toUpperCase() + newStr.substring(1, newStr.length()-1);
        reverseSentence += (newStr + " ");
        firstDot = true;
      }

      if (sentence.peek().contains(".") && firstDot==true){
        reverseSentence = reverseSentence.substring(0,reverseSentence.length()-1) + ". ";
        fullText.push(reverseSentence);
        reverseSentence = "";
        String newStr = sentence.pop();
        newStr = newStr.substring(0,1).toUpperCase() + newStr.substring(1, newStr.length()-1);
        reverseSentence += (newStr + " ");
      }

      else {
        String lowercase = sentence.pop().toLowerCase();
        reverseSentence += (lowercase + " ");
      }
    }

    reverseSentence = reverseSentence.substring(0,reverseSentence.length()-1) + ". ";
    fullText.push(reverseSentence);
    String reverseFullText = "";
    while(!fullText.empty()){
      reverseFullText += fullText.pop();
    }
    
    return reverseFullText;
  }
}
