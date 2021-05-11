import java.util.ArrayList;
import java.util.Date;
import java.util.SimpleDateFormat;

class Flight {
  String flightNumber;
  String airline;
  String origin;
  String destination;
  AircraftType aircraftType;
  Date scheduledDeparture;
  Date scheduledArrival;
  String Gate;
}
class AircraftType {
  String manufacturer;
  String model;
  String typeDesignator;
  EngineType engineType;
  int engineNumber;
}
enum EngineType {
  JET,
  ROCKET,
  ELECTRIC,
  TURBOPROP,
  PISTON
}
public class FlightList {
  ArrayList<Flight> arrfl = new ArrayList<Flight>();
  public static void main(String[] args) {
    FlightList fl = new FlightList();
    fl.testDriver();
  }
  private void testDriver(){
    
  }
}
