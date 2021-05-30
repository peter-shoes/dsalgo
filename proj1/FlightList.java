import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

class Flight {
  String airline;
  String flightNumber;
  String origin;
  String destination;
  Date scheduledDeparture;
  Date scheduledArrival;
  AircraftType aircraftType;
  String gate;

  @java.lang.Override
  public java.lang.String toString() {
    return airline+" "+flightNumber+" "+origin+"/"+destination+" Departs: "+scheduledDeparture+" Arrives: "+scheduledArrival+" Aircraft: "+aircraftType+" Gate: "+gate;
  }

  public Flight(String airline, String flightNumber, String origin, String destination, Date scheduledDeparture, Date scheduledArrival, AircraftType aircraftType, String gate) {
    this.airline = airline;
    this.flightNumber = flightNumber;
    this.origin = origin;
    this.destination = destination;
    this.scheduledDeparture = scheduledDeparture;
    this.scheduledArrival = scheduledArrival;
    this.aircraftType = aircraftType;
    this.gate = gate;
  }
}
class AircraftType {
  String manufacturer;
  String model;
  String typeDesignator;
  EngineType engineType;
  int engineNumber;

  public AircraftType(String manufacturer, String model, String typeDesignator, EngineType engineType, int engineNumber) {
    this.manufacturer = manufacturer;
    this.model = model;
    this.typeDesignator = typeDesignator;
    this.engineType = engineType;
    this.engineNumber = engineNumber;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return manufacturer+"-"+model+" EngineType/Num: "+engineType+"/"+engineNumber;
  }
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
  ArrayList<AircraftType> arrACT = new ArrayList<AircraftType>();
  public static void main(String[] args) {
    FlightList fl = new FlightList();
    fl.populateACT();
    fl.testDriver();
  }
  void testDriver() {
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
    // Date date = sdf.parse("12-10-2020 09:35");
    try{
      Flight fl1 = new Flight("AA", "101", "PHL","ORD", sdf.parse("12-10-2020 07:35"), sdf.parse("12-10-2020 09:35"), arrACT.get(1), "A27");
      Flight fl2 = new Flight("UA", "101", "PHL","SFO", sdf.parse("12-10-2020 08:35"), sdf.parse("12-10-2020 11:50"), arrACT.get(3), "G15");
      Flight fl3 = new Flight("DL", "233", "ATL","CDG", sdf.parse("12-10-2020 16:35"), sdf.parse("12-11-2020 07:50"), arrACT.get(3), "B15");
      Flight fl4 = new Flight("WN", "101", "PHL","LAX", sdf.parse("12-10-2020 08:35"), sdf.parse("12-10-2020 11:50"), arrACT.get(4), "G15");
      Flight fl5 = new Flight("AA", "221", "MCO","FLL", sdf.parse("12-10-2020 05:35"), sdf.parse("12-10-2020 07:33"), arrACT.get(2), "C11");
      Flight fl6 = new Flight("UA", "199", "PHL","JFK", sdf.parse("12-10-2020 08:35"), sdf.parse("12-10-2020 11:50"), arrACT.get(0), "D15");
      arrfl.add(fl1);
      arrfl.add(fl2);
      arrfl.add(fl3);
      arrfl.add(fl4);
      arrfl.add(fl5);
      arrfl.add(fl6);
    }
    catch (ParseException e) {
      System.out.println("not okay");
    }

    for (Flight flt:  arrfl) {
      System.out.println(flt);
    }
  }
  void populateACT(){
    AircraftType type1 = new AircraftType("Boeing", "737-600", "B736", EngineType.JET, 2);
    AircraftType type2 = new AircraftType("Boeing", "737-800", "B738", EngineType.JET, 2);
    AircraftType type3 = new AircraftType("Airbus", "A-310", "A310", EngineType.JET, 2);
    AircraftType type4 = new AircraftType("Airbus", "A-310B2", "A30B", EngineType.JET, 2);
    AircraftType type5 = new AircraftType("Airbus", "A-340-500", "A-340-500", EngineType.JET, 2);
    arrACT.add(type1);
    arrACT.add(type2);
    arrACT.add(type3);
    arrACT.add(type4);
    arrACT.add(type5);
  }
}
