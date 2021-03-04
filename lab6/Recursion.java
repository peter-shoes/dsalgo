public class Recursion {
  public static void main(String[] args) {
    Recursion recursion = new Recursion();
    System.out.println(recursion.sum(6));
    System.out.println(recursion.factorial(6));
    System.out.println(recursion.powerOf10(6));
    System.out.println(recursion.powerOfN(4,2));
    System.out.println(recursion.bunnyEars(6));
  }
  int sum(int n){
    int recursiveSum=0;
    if (n>0) recursiveSum = n+sum(n-1);
    return recursiveSum;
  }
  int factorial(int n){
    int recursiveFactorial=1;
    if (n>0) recursiveFactorial = n*factorial(n-1);
    return recursiveFactorial;
  }
  int powerOf10(int n){
    int pow10 = 10;
    if (n>1) pow10 = pow10*powerOf10(n-1);
    return pow10;
  }
  int powerOfN(int x, int p){
    if (p>1) x = x*powerOfN(x,p-1);
    return x;
  }
  int bunnyEars(int n){
    // Not sure if you wanted this recursive
    // could be done as:
    // return n*2;
    if (n>0) n = 2+(bunnyEars(n-1));
    return n;
  }
}
