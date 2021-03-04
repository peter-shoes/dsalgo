import java.util.Arrays;

public class ex2{
  public static void main(String[] args){
    String[] arr = {"Egypt", "Switzerland", "Argentina", "Spain", "Portugal", "Luxemburg"};
    System.out.println(Arrays.toString(arr));
    Arrays.sort(arr);
    System.out.println(Arrays.toString(arr));
  }
}
