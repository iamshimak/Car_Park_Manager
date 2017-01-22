package root;

/**
 * Created by ShimaK on 11-Nov-16.
 */
class MotorBike extends Vehicle {

    private Double sizeOfEngine;

    public MotorBike(String ID, String brand, DateTime entryTime, Double sizeOfEngine) {
        super(ID,brand,entryTime);
        this.sizeOfEngine = sizeOfEngine;
    }

    public Double getSizeOfEngine() {
        return sizeOfEngine;
    }

}
