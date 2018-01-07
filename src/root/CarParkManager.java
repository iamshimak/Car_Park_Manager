package root;

import java.io.File;

/**
 * Created by ShimaK on 11-Nov-16.
 */
public interface CarParkManager {

    boolean addNewVehicle(Vehicle vehicle, int lotNo);

    Vehicle deleteVehicle(int lotNo);

    void parkedVehicles();

    void vehicleStats();

    Vehicle longTimeParkedVehicle(DateTime dateTime);

    Vehicle lastVehicle(DateTime dateTime);

    void sortVehicleByDate(DateTime dateTime, File file, String option);

    int carParkCharges(Vehicle vehicle, DateTime dateTime);
}
