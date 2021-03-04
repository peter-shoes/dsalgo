import java.util.ArrayList;

public class ElectricCarList {

    public static void main(String[] args) {
        ElectricCarList cars = new ElectricCarList();
        cars.addCars();
        cars.printList();
    }

    ArrayList<String> carModels = new ArrayList<>();
    ArrayList<ElectricCar> carList = new ArrayList<>();

    class ElectricCar {
        String modelName;
        double mfgPrice;
        int maxRange;
        double usNewsRating;
        int rank;

        public String getModelName() {
            return modelName;
        }

        public double getMfgPrice() {
            return mfgPrice;
        }

        public int getMaxRange() {
            return maxRange;
        }

        public double getUsNewsRating() {
            return usNewsRating;
        }

        public int getRank() {
            return rank;
        }

        public ElectricCar(String modelName, double mfgPrice, int maxRange, double usNewsRating, int rank) {
            this.modelName = modelName;
            this.mfgPrice = mfgPrice;
            this.maxRange = maxRange;
            this.usNewsRating = usNewsRating;
            this.rank = rank;
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "ElectricCar{" +
                    "modelName='" + modelName + '\'' +
                    ", mfgPrice=" + mfgPrice +
                    ", maxRange=" + maxRange +
                    ", usNewsRating=" + usNewsRating +
                    ", rank=" + rank +
                    '}';
        }
    }
    public void addCars(){
        carList.add(new ElectricCar("2020 Hyundai Kona Electric",37190,258,0.0,14));
        carList.add(new ElectricCar("2020 Nissan Leaf",31600,226,0.0,13));
        carList.add(new ElectricCar("2020 Mini Cooper SE Electric Hardtop",29900,110,0.0,12));
        carList.add(new ElectricCar("2020 BMW i3",44450,153,7.5,11));
        carList.add(new ElectricCar("2020 Kia Niro EV",39090,239,7.6,10));
        carList.add(new ElectricCar("2020 Chevrolet Bolt",36620,259,7.8,9));
        carList.add(new ElectricCar("2020 Tesla Model Y",49990,316,7.9,8));
        carList.add(new ElectricCar("2020 Hyundai Ioniq Electric",33045,170,8.0,7));
        carList.add(new ElectricCar("2020 Jaguar I-Pace",69850,234,8.2,6));
        carList.add(new ElectricCar("2020 Tesla Model X",79990,351,8.3,5));
        carList.add(new ElectricCar("2020 Audi e-tron",77400,204,8.3,4));
        carList.add(new ElectricCar("2020 Porsche Taycan",103800,201,8.6,3));
        carList.add(new ElectricCar("2020 Tesla Model 3",37990,322,8.8,2));
        carList.add(new ElectricCar("2020 Tesla Model S", 74990,402,9.0,1));
    }
    public void printList(){
        for(ElectricCar i:carList) System.out.println(i);
    }
}