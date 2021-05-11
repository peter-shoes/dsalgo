import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class ReadCSVWithScanner {

  public LinkedList getFlightListFromCSV(String filePath) {
    LinkedList<Flight> fltList = new LinkedList<>();
    try {
      // open file input stream
      BufferedReader reader = new BufferedReader(new FileReader(
      filePath));

      // read file line by line
      String line = null;
      Scanner scanner = null;
      int index = 0;
      //  This flag is used if there is a header to ignore the first line of the CSV file
      //  If there is no header,  set this to false.
      boolean firstLine = true;
      while ((line = reader.readLine()) != null) {
        Flight flt = new Flight();
        scanner = new Scanner(line);
        scanner.useDelimiter(",");
        if (! firstLine)
        while (scanner.hasNext()) {
          String data = scanner.next();
          if (index == 0) {
            flt.setFlightNumber(data);
          }
          else if (index == 1) {
            flt.setAircraftNumber(data);
          }
          else if (index ==2) {
            flt.setDestinationOrigin(data);
          }
          else if (index ==3) {
            flt.setFlightType(data);
          }
          else if (index ==4) {
            flt.setSchedule(data);
          }
          else if (index == 5) {
            flt.setOperationStatus(data);
          }
          else
          System.out.println("invalid data::" + data);
          index++;
        }
        index = 0;
        if (! firstLine)
        fltList.add(flt);
        firstLine = false;
      }
      //close reader
      reader.close();
    }
    catch (IOException e) {
      System.out.println("File not found");
    }
    return fltList;
  }

}
