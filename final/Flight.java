enum FlightType {ARRIVAL, DEPARTURE};
class Flight {
  String flightNumber;
  FlightType flightType;
  int minuteInQueue;
  int minuteOutQueue;
  boolean denied=false;

  public Flight(String flightNumber, FlightType flightType) {
    this.flightNumber = flightNumber;
    this.flightType = flightType;
  }

  public String toString() {
    return flightType + ": " + flightNumber;
  }

  //  "minute" that flight entered the queue
  public void setMinuteInQueue(int minute) {
    this.minuteInQueue = minute;
  }

  // "minute" that flight exits the queue
  // difference is time in queue
  public void setMinuteOutQueue(int minute) {
    this.minuteOutQueue = minute;
  }

  public void setDenied(){
    denied = true;
  }

  public int timeInQueue() {
    return minuteOutQueue - minuteInQueue;
  }
}
