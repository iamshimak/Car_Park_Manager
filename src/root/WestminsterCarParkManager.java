package root;

import java.io.*;
import java.util.*;

/**
 * Created by ShimaK on 12-Nov-16.
 */

class WestminsterCarParkManager implements CarParkManager {

    private Scanner sc = new Scanner(System.in);
    private Vehicle[] lot = new Vehicle[20];

    public void start() {
        System.out.println("===================================================");
        System.out.println("===== Welcome to Westminster Car Park Manager =====");
        boolean isFinished = false;
        boolean isValidated = false;
        DateTime dateTime;
        Vehicle vehicle;
        //TODO add Proper Messages before prompt for date (Sort Vehicle by date)
        //TODO display total income
        //TODO Change date input in check Vehicle
        getLotData(lot);
        do {
            switch (menu()) {
                case 'a':
                    do {
                        System.out.println("\n=================== Add Vehicle ===================");
                        System.out.println("===================================================\n");

                        String type;
                        type = String.valueOf(validation("[v,c,m]",
                                "Vehicle Types\nV - Van\nC - Car\nM - Motor Bike", "option"));

                        // Checks vehicles type
                        if (type.equals("v")) {
                            if (!isAvailableForVan(lot)) {
                                System.out.println("Lot is full");
                                break;
                            }
                        } else {
                            if (isFull(lot)) {
                                System.out.println("Lot is full");
                                break;
                            }
                        }

                        dateTime = getDateTimeFromUser();

                        boolean lotIsSelected = false;
                        int lotNo = -1;

                        do {
                            availableSlots(lot);
                            System.out.print("\nSelect Lot Number ");
                            try {
                                lotNo = sc.nextInt();
                                if (lotNo > 20 || lotNo < 1) { //Rang Check (Check 1 - 20 because User Friendly)
                                    System.out.println("Invalid input try again\n");
                                } else if (lot[lotNo - 1] != null) { //Check it's occupied
                                    System.out.println("Lot is already taken\n");
                                } else {
                                    if (type.equals("v")) {
                                        if (lotNo < 20 && (lot[lotNo - 1] == null && lot[lotNo] == null)) {
                                            lotIsSelected = true;
                                        } else {
                                            System.out.println("Can't park van here\n");
                                        }
                                    } else {
                                        lotIsSelected = true;
                                    }
                                }
                            } catch (InputMismatchException ex) {
                                System.out.println("Invalid input try again\n");
                                sc.next();
                            }
                        } while (!lotIsSelected);

                        String id;
                        String brand;
                        String colour;
                        Double sizeOfEngine = -1.0;
                        Double cargoVolume = -1.0;
                        int noOfDoors = -1;
                        vehicle = null;

                        do {
                            isValidated = true;

                            System.out.print("\nEnter ID plate number: ");
                            id = sc.next();

                            for (Vehicle slot : lot) {
                                if (slot != null && slot.getID().equals(id)) {
                                    isValidated = false;
                                    break;
                                }
                            }

                            if (!isValidated) {
                                System.out.println("ID plate is already entered try again\n");
                            }
                        } while (!isValidated);

                        System.out.print("Enter vehicle's brand: ");
                        brand = sc.next();

                        isValidated = false;
                        switch (type) {
                            case "v":
                                do {
                                    try {
                                        System.out.print("Enter cargo volume in feet: ");
                                        cargoVolume = sc.nextDouble();
                                        isValidated = true;
                                    } catch (InputMismatchException ex) {
                                        System.out.println("Invalid input try again");
                                        sc.next();
                                    }
                                } while (!isValidated);

                                vehicle = new Van(id, brand, dateTime, cargoVolume);
                                break;
                            case "m":
                                do {
                                    try {
                                        System.out.print("Enter size of engine: ");
                                        sizeOfEngine = sc.nextDouble();
                                        isValidated = true;
                                    } catch (InputMismatchException ex) {
                                        System.out.println("Invalid input try again");
                                        sc.next();
                                    }
                                } while (!isValidated);

                                vehicle = new MotorBike(id, brand, dateTime, sizeOfEngine);
                                break;
                            case "c":
                                do {
                                    try {
                                        System.out.print("Enter no of doors : ");
                                        noOfDoors = sc.nextInt();
                                        isValidated = true;
                                    } catch (InputMismatchException ex) {
                                        System.out.println("Invalid input try again");
                                        sc.next();
                                    }
                                } while (!isValidated);
                                System.out.print("Enter car's colour: ");
                                colour = sc.next();

                                vehicle = new Car(id, brand, dateTime, noOfDoors, colour);
                                break;
                        }

                        addNewVehicle(lot, vehicle, lotNo);

                        String choice = String.valueOf(validation("[y,n]",
                                "Do you want add another vehicle (Y/N) ?", "option"));
                        if (choice.equals("n")) {
                            isFinished = true;
                        }

                        System.out.println("\n=========== Vehicle added Successfully ============\n");

                    } while (!isFinished);
                    isFinished = false;
                    saveLotData(lot);
                    break;
                case 'r':
                    do {
                        System.out.println("\n================ Remove Vehicle ==================");
                        System.out.println("===================================================\n");

                        if (isEmpty(lot)) {
                            System.out.println("Cant't remove vehicle lot are empty");
                            break;
                        }

                        int slotNo = -1;
                        boolean isSelected = false;

                        do {
                            try {
                                parkedVehicles(lot);
                                System.out.print("\nEnter Slot No. to remove vehicle: ");
                                slotNo = sc.nextInt();
                                if ((slotNo > 20 || slotNo < 1)) {
                                    System.out.println("\nInvalid input try again");
                                } else if (lot[slotNo - 1] == null) {
                                    System.out.println("\nThis lot is empty");
                                } else {
                                    isSelected = true;
                                }
                            } catch (InputMismatchException ex) {
                                System.out.println("\nInvalid input try again");
                                sc.next();
                            }
                        } while (!isSelected);

                        dateTime = getDateTimeFromUser();

                        int hours = lot[slotNo - 1].getEntryTime().differenceInHours(dateTime);

                        System.out.println("\nVehicle Charge: " + calculateCharges(hours));

                        deleteVehicle(lot, slotNo);
                        String choice = String.valueOf(validation("[y,n]",
                                "Do you want delete another vehicle (Y/N) ?", "option"));
                        if (choice.equals("n")) {
                            isFinished = true;
                        }

                        System.out.println("\n========== Vehicle removed Successfully ===========\n");
                    } while (!isFinished);
                    isFinished = false;
                    break;
                case 'd':
                    parkedVehicles(lot);
                    break;

                case 't':
                    System.out.println("\n================== Vehicle Types ==================");
                    System.out.println("===================================================\n");
                    vehicleStats(lot);
                    break;

                case 'f':
                    System.out.println("\n============ Long Time Parked Vehicle =============");
                    System.out.println("===================================================\n");

                    dateTime = getDateTimeFromUser();

                    vehicle = longTimeParkedVehicle(lot, dateTime);

                    if (vehicle != null) {
                        System.out.println("Type         : " + vehicle.getClass().getSimpleName());
                        System.out.println("ID plate     : " + vehicle.getID());
                        System.out.println("Entry Time   : " + vehicle.getEntryTime().toString());
                        //System.out.println("Parked Hours : " + (minutes / 60));
                    } else {
                        System.out.println("Slots are empty");
                    }
                    break;

                case 'l':
                    System.out.println("\n=============== Last Parked Vehicle ===============");
                    System.out.println("===================================================\n");

                    dateTime = getDateTimeFromUser();
                    vehicle = lastVehicle(lot, dateTime);

                    if (vehicle != null) {
                        System.out.println("Type         : " + vehicle.getClass().getSimpleName());
                        System.out.println("ID plate     : " + vehicle.getID());
                        System.out.println("Entry Time   : " + vehicle.getEntryTime().toString());
                    } else {
                        System.out.println("Lot is empty");
                    }
                    break;

                case 'c':
                    System.out.println("\n============== Sort Vehicles By Date ==============");
                    System.out.println("===================================================\n");

                    int year, month, day;
                    isValidated = false;
                    dateTime = null;
                    //TODO validation has to be done
                    System.out.print("Do you want to sort by (day,month,year) ");
                    String answer = sc.next().toLowerCase();

                    do {
                        try {
                            year = 0;
                            month = 1;
                            day = 1;
                            switch (answer) {
                                case "day":
                                    System.out.print("Enter day ");
                                    day = sc.nextInt();
                                case "month":
                                    System.out.print("Enter month ");
                                    month = sc.nextInt();
                                case "year":
                                    System.out.print("Enter year ");
                                    year = sc.nextInt();
                            }

                            dateTime = new DateTime();
                            if (dateTime.setDateTime(year, month, day, 0, 0, 0)) {
                                isValidated = true;
                            } else {
                                System.out.println("Invalid date type\n");
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input try again\n");
                            sc.next();
                        }
                    } while (!isValidated);

                    System.out.println("Entered Vehicles");
                    File file = new File("src/Vehicles_Entered_Time.txt");
                    sortVehicleByDate(dateTime, file, answer);

                    System.out.println("\nLeft Vehicles");
                    file = new File("src/Vehicles_Left_Time.txt");
                    sortVehicleByDate(dateTime, file, answer);

                    break;

                case 'v':
                    System.out.println("\n================ Car Park Chargers ================");
                    System.out.println("===================================================\n");

                    if (isEmpty(lot)) {
                        System.out.println("Lot is empty");
                        break;
                    }

                    dateTime = getDateTimeFromUser();

                    for (Vehicle slot : lot) {
                        if (slot != null) {
                            int charge = carParkCharges(slot, dateTime);
                            if (charge > -1) {
                                System.out.println(slot.getClass().getSimpleName() + " " + slot.getID()
                                        + " Entry time : " + slot.getEntryTime().toString() + " : Charge " + charge);
                            }
                        }
                    }
                    break;

                case 'q':
                    saveLotData(lot);
                    isFinished = true;
                    break;
            }
        } while (!isFinished);
        saveLotData(lot);
    }

    /**
     * Displays the options and get the input and returns
     */
    private char menu() {
        String message = "===================================================\n" +
                "                         MENU                      \n" +
                "===================================================\n" +
                "A - Add new vehicle\n" +
                "R - Remove vehicle\n" +
                "D - Display the vehicles currently parked\n" +
                "T - Display the vehicle's types currently parked\n" +
                "F - Display the longest time parked vehicle\n" +
                "L - Display last parked vehicle\n" +
                "C - Sort Vehicles By Date\n" +
                "V - View parking charges\n" +
                "Q - Quit program\n";
        return validation("[a,r,d,t,f,l,c,v,q]", message, "option");
    }

    /**
     * Add a new vehicle
     */
    @Override
    public boolean addNewVehicle(Vehicle[] lot, Vehicle vehicle, int slotNo) {
        if (vehicle == null) {
            return false;
        }

        try {
            if (vehicle.getClass().getSimpleName().equals("Van")) {
                lot[slotNo - 1] = vehicle;
                lot[slotNo] = vehicle;
            } else {
                lot[slotNo - 1] = vehicle;
            }

            saveVehicleData("src/Vehicles_Entered_Time", vehicle);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Delete vehicle SlotNo is in users pov (not array index Based)
     */
    @Override
    public Vehicle deleteVehicle(Vehicle[] lot, int slotNo) throws NullPointerException {
        if (isEmpty(lot)) {
            return null;
        }

        saveVehicleData("src/Vehicles_Left_Time", lot[slotNo - 1]);

        Vehicle vehicle = null;
        try {
            /** If it's a van it*/
            if (lot[slotNo - 1].getClass().getSimpleName().equals("Van")) {
                if (slotNo > 1 && slotNo < 20) {
                    if (lot[slotNo - 1] == lot[slotNo]) {
                        vehicle = lot[slotNo];
                        lot[slotNo] = null;
                        lot[slotNo - 1] = null;
                    } else if (lot[slotNo - 1] == lot[slotNo - 2]) {
                        vehicle = lot[slotNo - 1];
                        lot[slotNo - 1] = null;
                        lot[slotNo - 2] = null;
                    } else {
                        return null;
                    }
                } else if (slotNo < 2) {
                    if (lot[slotNo - 1] != null && lot[slotNo] != null) {
                        vehicle = lot[slotNo - 1];
                        lot[slotNo] = null;
                        lot[slotNo - 1] = null;
                    } else {
                        return null;
                    }
                } else if (slotNo > 19) {
                    vehicle = lot[slotNo - 1];
                    lot[slotNo - 1] = null;
                    lot[slotNo - 2] = null;
                }
            } else {
                vehicle = lot[slotNo - 1];
                lot[slotNo - 1] = null;
            }
            return vehicle;

        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        } catch (NullPointerException e) {
            throw new NullPointerException("Vehicle is null");
        }
    }

    /**
     * Displays currently parked vehicle
     */
    @Override
    public void parkedVehicles(Vehicle[] lot) {
        System.out.println("\n----------- Currently Parked Vehicles -------------");
        //TODO change this to switch
        if (isEmpty(lot)) {
            System.out.println("Lot is Empty");
            return;
        }

        for (int x = 0; x < lot.length; x++) {
            if (lot[x] != null) {
                System.out.println("Slot " + (x + 1) + " " + lot[x].getClass().getSimpleName()
                        + " " + lot[x].getID() + " " + lot[x].getBrand() + " " + lot[x].getEntryTime());
            }
        }
    }

    /**
     * Displays some stats about currently parked vehicles
     */
    @Override
    public void vehicleStats(Vehicle[] lot) {
        if (isEmpty(lot)) {
            System.out.println("Lot is empty");
            return;
        }

        Double vans = 0.0;
        Double cars = 0.0;
        Double motorBikes = 0.0;

        for (int x = 0; x < lot.length; x++) {
            if (lot[x] != null) {
                switch (lot[x].getClass().getSimpleName()) {
                    case "Van":
                        if (!(x > 0 && (lot[x - 1] == lot[x]))) {
                            vans++;
                        }
                        break;
                    case "Car":
                        cars++;
                        break;
                    case "MotorBike":
                        motorBikes++;
                        break;
                }
            }
        }

        Double totVehicle = vans + cars + motorBikes;

        System.out.println("Currently Parked Vehicles : " + totVehicle.intValue());
        System.out.println("\tVans        : " + vans.intValue());
        System.out.println("\tCars        : " + cars.intValue());
        System.out.println("\tMotor Bikes : " + motorBikes.intValue());
        System.out.println("\nPercentage of vehicles");
        System.out.println("\tVans        : " + ((vans / totVehicle) * 100) + "%");
        System.out.println("\tCars        : " + ((cars / totVehicle) * 100) + "%");
        System.out.println("\tMotor Bikes : " + ((motorBikes / totVehicle) * 100) + "%");
    }

    /**
     * Return long time parked vehicle
     */
    @Override
    public Vehicle longTimeParkedVehicle(Vehicle[] lot, DateTime dateTime) {
        if (isEmpty(lot) || dateTime == null) {
            return null;
        }

        int minutes = 0;
        int max = 0;
        int index = 0;

        for (int x = 0; x < lot.length; x++) {
            if (lot[x] != null) {
                max = lot[x].getEntryTime().differenceInMinutes(dateTime);
                index = x;
                break;
            }
        }

        int minIndex = 0;
        for (int x = index; x < lot.length; x++) {
            if (lot[x] != null) {
                minutes = lot[x].getEntryTime().differenceInMinutes(dateTime);
                if (minutes > max) {
                    max = minutes;
                    minIndex = x;
                }
            }
        }

        return lot[minIndex];
    }

    /**
     * Returns last parked vehicle
     */
    @Override
    public Vehicle lastVehicle(Vehicle[] lot, DateTime dateTime) {
        if (isEmpty(lot)) {
            return null;
        }

        //TODO check hours different & change return type
        int minutes = 0;
        int min = 0;
        int index = 0;
        Vehicle vehicle;
        /** Assigning first non-null vehicle as min value */
        for (int x = 0; x < lot.length; x++) {
            if (lot[x] != null) {
                min = lot[x].getEntryTime().differenceInMinutes(dateTime);
                index = x;
                break;
            }
        }
        /** Process to find last parked vehicle*/
        int minIndex = index;
        for (int x = index; x < lot.length; x++) {
            if (lot[x] != null) {
                minutes = lot[x].getEntryTime().differenceInMinutes(dateTime);
                if (minutes < min) {
                    min = minutes;
                    minIndex = x;
                }
            }
        }

        return lot[minIndex];
    }

    /**
     * Sorting vehicle by date
     */
    @Override
    public void sortVehicleByDate(DateTime dateTime, File file, String option) {
        String thisLine = null;
        String[] arr;
        String[] time;
        String[] date;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            //TODO display message if empty
            while ((thisLine = br.readLine()) != null) {
                arr = thisLine.split(" ");
                time = arr[1].split(":");
                date = arr[2].split("/");

                if (option.equals("day")) {
                    if (Integer.parseInt(date[0]) == dateTime.getDay() && Integer.parseInt(date[1]) == dateTime.getMonth()
                            && Integer.parseInt(date[2]) == dateTime.getYear()) {
                        System.out.println(thisLine);
                    }
                } else if (option.equals("month")) {
                    if (Integer.parseInt(date[1]) == dateTime.getMonth()
                            && Integer.parseInt(date[2]) == dateTime.getYear()) {
                        System.out.println(thisLine);
                    }
                } else {
                    if (Integer.parseInt(date[2]) == dateTime.getYear()) {
                        System.out.println(thisLine);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets Slot[] and date and time and displays currently parked vehicles charges
     */
    @Override
    public int carParkCharges(Vehicle vehicle, DateTime dateTime) {
        if (vehicle == null) {
            return -1;
        }
        return calculateCharges(vehicle.getEntryTime().differenceInHours(dateTime));
    }

    /**
     * Stores programs lot details in a text file
     */
    public void saveLotData(Vehicle[] lot) {
        File file = new File("src/Car_Park_Details.txt");

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

            for (Vehicle slot : lot) {
                out.writeObject(slot);
            }

            out.close();
        } catch (IOException ex) {
            System.out.println("Something Went Wrong");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Gets back previously stored date in a file
     */
    private void getLotData(Vehicle[] lot) {
        File file = new File("src/Car_Park_Details.txt");

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            for (int x = 0; x < lot.length; x++) {
                lot[x] = (Vehicle) in.readObject();
            }
            in.close();
        } catch (IOException ex) {
            System.out.println("Something Went Wrong");
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * validation method gets print Menu , choices , messages
     * It validate the input - checks its matches with choices and it returns validated answer
     */
    private char validation(String choices, String menu, String message) {
        boolean isValidated = false;
        String option;
        do {
            System.out.println(menu);
            System.out.print("Enter the " + message + " - ");
            option = sc.next().toLowerCase();
            if (option.matches(choices)) {
                isValidated = true;
            } else {
                System.out.println("Invalid input Try again\n");
            }
        } while (!isValidated);
        return option.charAt(0);
    }

    /**
     * Checks lot is full
     */
    private boolean isFull(Vehicle[] lot) {
        for (Vehicle slot : lot) {
            if (slot == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks lot is empty
     */
    private boolean isEmpty(Vehicle[] lot) {
        for (Vehicle slot : lot) {
            if (slot != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks lot is full for van (Van need 2 slots for park)
     */
    private boolean isAvailableForVan(Vehicle[] lot) {
        for (int x = 0; x < lot.length - 1; x++) {
            if (lot[x] == null && lot[x + 1] == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Displays available slots
     */
    private void availableSlots(Vehicle[] lot) {
        System.out.println("\n----------------- Available Slots ------------------");
        for (int x = 0; x < lot.length / 2; x++) {
            if (lot[x] == null) {
                System.out.print(" Slot " + (x + 1) + " Available");
            } else {
                System.out.print(" Slot " + (x + 1) + " Not Available");
            }
            if (lot[x + 10] == null) {
                System.out.print("\t\t\t\t  Slot " + (x + 11) + " Available");
            } else {
                System.out.print("\t\t\t\t  Slot " + (x + 11) + " Not Available");
            }
            System.out.println();
        }
    }

    /**
     * Calculate chargers for parked hours and return total charge
     */
    private int calculateCharges(int hours) {
        if (hours == 0) {
            return 3;
        } else if (hours < 3) {
            return hours * 3;
        } else {
            hours -= 3;
            return hours + (3 * 3);
        }
    }

    /**
     * Gets date and time from user validates and returns a DateTime object
     */
    private DateTime getDateTimeFromUser() {
        int year, month, day, hour, minute;
        boolean isValidated = false;
        DateTime dateTime = null;

        do {
            try {
                System.out.print("Enter Date&Time in this format (hh mm DD MM YYYY): ");
                hour = sc.nextInt();
                minute = sc.nextInt();
                day = sc.nextInt();
                month = sc.nextInt();
                year = sc.nextInt();

                dateTime = new DateTime();
                if (dateTime.setDateTime(year, month, day, hour, minute, 0)) {
                    isValidated = true;
                } else {
                    System.out.println("Invalid date type\n");
                    sc.next();
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid Input try again\n");
                sc.next();
            }
        } while (!isValidated);
        return dateTime;
    }

    /**
     * Stores entered vehicles details
     */
    private void saveVehicleData(String fileName, Vehicle vehicle) throws NullPointerException {
        if (vehicle == null) {
            throw new NullPointerException("Vehicle is null");
        }

        File file = new File(fileName + ".txt");

        try {
            BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file, true));
            bufferWriter.write(vehicle.getID() + " " + vehicle.getEntryTime().toString() + "\n");
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong, Please try again");
            e.printStackTrace();
        }
    }

}