import java.util.Random;
import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class AirTrafficControlSim {
  // set up some color codes
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

  // set up both timers
  int timerCounter = 0;
  int timeInterval = 0;
  // set up the production queue
  ArrayDeque<Flight> arrivalQueue = new ArrayDeque<>();
  ArrayDeque<Flight> departureQueue = new ArrayDeque<>();
  // set up the stat queue
  ArrayList<Flight> arrivalStatistics = new ArrayList<>();
  ArrayList<Flight> departureStatistics =  new ArrayList<>();

  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);
    double meanArrivalFreq = 0.0;
    double meanDepartureFreq = 0.0;
    AirTrafficControlSim sim = new AirTrafficControlSim();
    System.out.println("Enter mean departure frequency (0.0 < df > 1.0): ");
    meanDepartureFreq = scnr.nextDouble();
    System.out.println("Enter mean arrival frequency   0.0 < af > 1.0): ");
    meanArrivalFreq =  scnr.nextDouble();
    //  Each i in the loop represents a minute, 720 x 2 = 1440 minutes in 24 hours
    for (int i = 0; i < 720; i++) {
      sim.processArrival(meanArrivalFreq);
      sim.processDeparture(meanDepartureFreq);
    }
    sim.printSimSummaryStatistics();
  }
  // set up random distribution
  // I did not use a random seeder because it kept giving me insane numbers that broke the program
  public int getPoissonRandom(double mean) {
    Random r = new Random();
    double L = Math.exp(-mean);
    int x = 0;
    double p = 1.0;
    do {
      p = p * r.nextDouble();
      x++;
    } while (p > L);
    return x - 1;
  }
  void processArrival(double meanArrivalFreq) {
    int count = 0;
    timerCounter++;
    timeInterval++;
    // if there is more than one plane coming, add that many to arrival queue
    if ((count = getPoissonRandom(meanArrivalFreq)) > 0) addToArrivalQueue(count);
    // if it has been longer than 10 minutes
    if (timerCounter >= 10) {
      // and if there are planes in the queue
      if (arrivalQueue.size() > 0) {
        // those planes are good to arrive
        removeFromArrivalQueue();
        // reset the timer counter
        timerCounter = 0;
      }
    }
  }
  // this is the same as above
  void processDeparture(double meanDepartureFreq) {
    int count = 0;
    timerCounter++;
    timeInterval++;
    if ((count = getPoissonRandom(meanDepartureFreq)) > 0) addToDepartureQueue(count);
    if (timerCounter >= 10) {
      if (departureQueue.size() > 0) {
        removeFromDepartureQueue();
        timerCounter = 0;
      }
    }
  }
  void addToArrivalQueue(int count){
    Random rnd = new Random();
    for (int i=0;i<count;i++){
      // generate name for the flight
      String flightNumber = "AA"+(rnd.nextInt(900)+100);
      // mark flight as arrival
      Flight flgt = new Flight(flightNumber, FlightType.ARRIVAL);
      // set the minute the plane arrives in the queue
      flgt.setMinuteInQueue(timeInterval);
      // if there are more than 5 planes in the queue
      if(arrivalQueue.size()>=5){
        // set the time out queue (this will end up being the same as the time in queue)
        flgt.setMinuteOutQueue(timeInterval);
        // mark as denied (new attribute I set up)
        flgt.setDenied();
        // print out the denial message
        System.out.println(ANSI_RED+"Arrival queue full. "+ANSI_BLUE+flgt.toString()+ANSI_RESET+" rerouted at: " +(timeInterval/60) + ":" + (String.format("%02d",timeInterval % 60))+" hrs");
        // add to the stats list
        arrivalStatistics.add(flgt);
      }
      else{
        // otherwise add it to the arrival queue
        arrivalQueue.add(flgt);
      }
    }
  }
  // same as arrival
  void addToDepartureQueue(int count){
    Random rnd = new Random();
    for (int i=0;i<count;i++){
      String flightNumber = "UA"+(rnd.nextInt(900)+100);
      Flight flgt = new Flight(flightNumber, FlightType.DEPARTURE);
      flgt.setMinuteInQueue(timeInterval);
      if(departureQueue.size()>=5){
        flgt.setMinuteOutQueue(timeInterval);
        flgt.setDenied();
        System.out.println(ANSI_RED+"Departure queue full. "+ANSI_YELLOW+flgt.toString()+ANSI_RESET+" denied at: "+(timeInterval/60) + ":" + (String.format("%02d",timeInterval % 60))+" hrs");
        departureStatistics.add(flgt);
      }
      else{
        departureQueue.add(flgt);
      }
    }
  }
  void removeFromArrivalQueue(){
    // set the current minute as the time it leaves the queue
    arrivalQueue.peek().setMinuteOutQueue(timeInterval);
    // print the arrival message
    System.out.println(ANSI_BLUE+arrivalQueue.peek()+ANSI_RESET+" arrived at: "+(timeInterval/60) + ":" + (String.format("%02d",timeInterval % 60))+" hrs");
    // remove it from the queue
    arrivalStatistics.add(arrivalQueue.remove());
  }
  // same as above
  void removeFromDepartureQueue(){
    departureQueue.peek().setMinuteOutQueue(timeInterval);
    System.out.println(ANSI_YELLOW+departureQueue.peek()+ANSI_RESET+" departed at: "+(timeInterval/60) + ":" + (String.format("%02d",timeInterval % 60))+" hrs");
    departureStatistics.add(departureQueue.remove());
  }
  void printSimSummaryStatistics(){
    // begin printing of the stats screen (WITH GRAPHICS)
    System.out.println("");
    System.out.println("======================================");
    System.out.println("SIMULATOR STATISTICAL SUMMARY");
    System.out.println("======================================");
    System.out.println("");

    // get the number of actual arrivals and departures
    // this means planes that weren't denied
    int actualArrivals=0;
    for (int i=0; i<arrivalStatistics.size();i++){
      if (arrivalStatistics.get(i).denied == false) actualArrivals++;
    }
    int actualDepartures=0;
    for (int i=0; i<departureStatistics.size();i++){
      if (departureStatistics.get(i).denied == false) actualDepartures++;
    }
    // convert the timeInterval to hrs
    System.out.println("Time Period Simulated: "+(timeInterval/60) + ":" + (String.format("%02d",timeInterval % 60))+" hrs");
    // print actual arrivals and departures
    System.out.println("Number of Arrivals: "+actualArrivals);
    System.out.println("Number of Departures: "+actualDepartures);
    System.out.println("Total Number of Flights: "+(actualArrivals+actualDepartures));
    // divide arrival and departure numbers by the number of hours in a day
    System.out.println("Average Arrivals Per Hour: "+String.format("%.2f",((double)actualArrivals/24)));
    System.out.println("Average Departures Per Hour: "+String.format("%.2f",((double)actualDepartures/24)));
    // get the size of the two production queues
    System.out.println("Arrivals Remaining in Queue: "+arrivalQueue.size());
    System.out.println("Departures Remaining in Queue: "+departureQueue.size());

    // if the flight in the statistic list has the denied attribute marked as true, count it here and print
    int noArrivalCount = 0;
    for (int i=0; i<arrivalStatistics.size(); i++){
      if(arrivalStatistics.get(i).denied == true) noArrivalCount++;
    }
    System.out.println("Number of Rereouted Arrivals: "+noArrivalCount);

    int noDepartureCount = 0;
    for (int i=0; i<departureStatistics.size(); i++){
      if (departureStatistics.get(i).denied == true) noDepartureCount++;
    }
    System.out.println("Number of Denied Departures: "+noDepartureCount);

    // This one was a bit weird, but I think I got it right
    // start by getting the minuteOutQueue for every flight that wasn't denied and add it to the list as an int
    ArrayList<Integer> runwayTimes = new ArrayList<>();
    for (int i =0;i<arrivalStatistics.size();i++){
      if (arrivalStatistics.get(i).denied != true) runwayTimes.add(arrivalStatistics.get(i).minuteOutQueue);
    }
    for (int i =0;i<departureStatistics.size();i++){
      if (departureStatistics.get(i).denied != true) runwayTimes.add(departureStatistics.get(i).minuteOutQueue);
    }
    // sort the list from smallest to largest in terms of minutes out of the queue
    Collections.sort(runwayTimes);
    int wastedTime = 0;
    for (int i=0;i<runwayTimes.size()-1;i++){
      // wasted time is the next minuteOutQueue minus the previous minuteOutQueue minus 10 mins, the time for a flight to arrive/takeoff
      wastedTime += (runwayTimes.get(i+1)-runwayTimes.get(i)-10);
    }
    // System.out.println(wastedTime);
    // System.out.println(timeInterval);
    // get the percentage of minutes wasted in the day
    System.out.println("Percent of Time Idle Runway: "+String.format("%.2f",((double)wastedTime/timeInterval)*100)+"%");

    int arrivalTiqSum = 0;
    int arrivalTiqCount = 0;
    for (int i=0; i<arrivalStatistics.size(); i++){
      // get the time in queue for all flights that were in queue for more than 0 mins
      int tiq = arrivalStatistics.get(i).timeInQueue();
      if (tiq>0) {
        arrivalTiqSum += tiq;
        arrivalTiqCount++;
      }
    }
    // this is just a failsafe to prevent errors, results are the same either way
    if (arrivalTiqCount==0) arrivalTiqCount=1;
    // get total time in queue divided by total planes that were in queue
    System.out.println("Average Arrival Time in Queue: "+(arrivalTiqSum/arrivalTiqCount)+" mins");

    // same as above
    int departureTiqSum = 0;
    int departureTiqCount = 0;
    for (int i=0; i<departureStatistics.size(); i++){
      int tiq = departureStatistics.get(i).timeInQueue();
      if (tiq>0) {
        departureTiqSum += tiq;
        departureTiqCount++;
      }
    }
    if (departureTiqCount==0) departureTiqCount=1;
    System.out.println("Average Departure Time in Queue: "+(departureTiqSum/departureTiqCount)+" mins");

    System.out.println(ANSI_RESET);
  }
}
