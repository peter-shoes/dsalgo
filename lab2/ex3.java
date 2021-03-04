import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

public class ex3{
  public static void main(String[] args){

    Random rd = new Random();
    ArrayList<Integer> nums = new ArrayList<Integer>();
    for (int i=0;i<20;i++){
      nums.add(rd.nextInt());
    }
    System.out.println(nums);
    Collections.sort(nums);
    System.out.println(nums);
    System.out.println("");

    ArrayList<String> cities = new ArrayList<String>(
      Arrays.asList("Egypt",
                    "Switzerland",
                    "Argentina",
                    "Spain",
                    "Portugal",
                    "Luxemburg")
    );
    System.out.println(cities);
    Collections.sort(cities);
    System.out.println(cities);
    System.out.println("");

    nums.add(rd.nextInt());
    cities.add("Philadelphia");
    Collections.sort(nums);
    Collections.sort(cities);
    System.out.println(nums);
    System.out.println(cities);
  }
}
