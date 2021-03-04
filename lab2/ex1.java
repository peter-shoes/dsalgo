import java.util.Arrays;
import java.util.Random;

public class ex1 {
  public static void main(String[] args){
    Random rd = new Random();
    int[] arr = new int[20];
    for (int i=0;i<20;i++){
      arr[i]=rd.nextInt();
    }
    System.out.println(Arrays.toString(arr));
    Arrays.sort(arr);
    System.out.println(Arrays.toString(arr));
  }
}
