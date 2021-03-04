interface RuminantTester {
  void testIfRuminant();
  void testHasMultipleStomachs();
}
abstract class Mammal {
  void nursesYoung(){
    String className = this.getClass().getSimpleName();
    String nurseString = "I am a "+ className + ". I am nursing.";
    System.out.println(nurseString);
  }
}
abstract class GrazingMammal extends Mammal implements RuminantTester {
  void grazes(){
    String className = this.getClass().getSimpleName();
    String grazeString = "I am a "+ className + ". I am grazing.";
    System.out.println(grazeString);
  }

  @Override
  public void testHasMultipleStomachs(){
    String className = this.getClass().getName();
    if (this instanceof Ruminant) {
      System.out.println("I am a "+className+". I have multiple stomachs.");
    }
    else {
      System.out.println("I am a "+className+". I do not have multiple stomachs.");
    }
  }

  @Override
  public void testIfRuminant(){
    String className = this.getClass().getName();
    if (this instanceof Ruminant) {
      System.out.println("I am a "+className+". I am a Ruminant.");
    }
    else {
      System.out.println("I am a "+className+". I am not a Ruminant.");
    }
  }
}
abstract class Ruminant extends GrazingMammal{
  void chewsCud(){
    String className = this.getClass().getSimpleName();
    String chewString = "I am a "+ className + ". I am chewing cud.";
    System.out.println(chewString);
  }
}
class Cow extends Ruminant{}
class Goat extends Ruminant {}
class Horse extends GrazingMammal{}

public class GrazingMammals{
  public static void main(String[] args) {
    Cow cow = new Cow();
    cow.nursesYoung();
    cow.grazes();
    cow.chewsCud();
    cow.testIfRuminant();
    cow.testHasMultipleStomachs();
    System.out.println("\n");
    Goat goat = new Goat();
    goat.nursesYoung();
    goat.grazes();
    goat.chewsCud();
    goat.testIfRuminant();
    goat.testHasMultipleStomachs();
    System.out.println("\n");
    Horse horse  = new Horse();
    horse.nursesYoung();
    horse.grazes();
    horse.testIfRuminant();
    horse.testHasMultipleStomachs();
  }
}
