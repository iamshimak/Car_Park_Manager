package root;

import java.io.File;

/**
 * Created by ShimaK on 11-Nov-16.
 */
public interface CarParkManager {

    //TODO Set suitable return type

    boolean addNewVehicle(Vehicle[] spaces, Vehicle vehicle, int lotNo);

    Vehicle deleteVehicle(Vehicle[] spaces, int lotNo);

    void parkedVehicles(Vehicle[] spaces);

    void vehicleStats(Vehicle[] spaces);

    Vehicle longTimeParkedVehicle(Vehicle[] spaces, DateTime dateTime);

    Vehicle lastVehicle(Vehicle[] spaces, DateTime dateTime);

    void sortVehicleByDate(DateTime dateTime, File file, String option);

    int carParkCharges(Vehicle vehicle, DateTime dateTime);
}
