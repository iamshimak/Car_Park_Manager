package root;

import java.util.Date;

/**
 * Created by ShimaK on 11-Nov-16.
 */
class Car extends Vehicle {

    private int noOfDoors;
    private String colour;

    public Car(String ID, String brand, DateTime entryTime, int noOfDoors, String colour) {
        super(ID,brand,entryTime);
        this.noOfDoors = noOfDoors;
        this.colour = colour;
    }

    public int getNoOfDoors() {
        return noOfDoors;
    }

    public String getColour() {
        return colour;
    }

}
