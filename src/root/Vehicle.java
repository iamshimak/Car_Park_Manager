package root;

import java.io.Serializable;

/**
 * Created by ShimaK on 11-Nov-16.
 */
abstract class Vehicle implements Serializable{

    private String ID;
    private String brand;
    private DateTime entryTime;

    public Vehicle(String ID, String brand, DateTime entryTime) {
        this.ID = ID;
        this.brand = brand;
        this.entryTime = entryTime;
    }

    public String getID() {
        return ID;
    }

    public String getBrand() {
        return brand;
    }

    public DateTime getEntryTime() {
        return entryTime;
    }

}
