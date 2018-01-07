package root;

import java.util.Scanner;

public class WestminsterCarParkManagerView {

    public enum MenuTypes {
        ADD_VEHICLE,
        REMOVE_VEHICLE,
        PARKED_VEHICLES,
        PARKED_VEHICLES_TYPES,
        LONGEST_PARKED_VEHICLE,
        LAST_PARKED_VEHICLE,
        SORT_VEHICLE_BY_DATE,
        PARKING_CHARGES,
        MENU,
        WELCOME,
        ADD_VEHICLE_MENU,
        ADD_VEHICLE_CONTINUE,
        DELETE_VEHICLE_CONTINUE,
        QUIT
    }

    private Scanner sc = new Scanner(System.in);

    public void designForView(MenuTypes type) {
        switch (type) {
            case ADD_VEHICLE:
                System.out.println("\n=================== Add Vehicle ===================");
                System.out.println("===================================================\n");
                break;
            case REMOVE_VEHICLE:
                System.out.println("\n================ Remove Vehicle ==================");
                System.out.println("===================================================\n");
                break;
            case PARKED_VEHICLES:
                System.out.println("\n----------- Currently Parked Vehicles -------------");
                break;
            case PARKED_VEHICLES_TYPES:
                System.out.println("\n================== Vehicle Types ==================");
                System.out.println("===================================================\n");
                break;
            case LONGEST_PARKED_VEHICLE:
                System.out.println("\n============ Long Time Parked Vehicle =============");
                System.out.println("===================================================\n");
                break;
            case LAST_PARKED_VEHICLE:
                System.out.println("\n=============== Last Parked Vehicle ===============");
                System.out.println("===================================================\n");
                break;
            case SORT_VEHICLE_BY_DATE:
                System.out.println("\n============== Sort Vehicles By Date ==============");
                System.out.println("===================================================\n");
                break;
            case PARKING_CHARGES:
                System.out.println("\n================ Car Park Chargers ================");
                System.out.println("===================================================\n");
                break;
            case MENU:
                System.out.println("===================================================\n" +
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
                        "Q - Quit program\n");
                break;
            case WELCOME:
                System.out.println("===================================================");
                System.out.println("===== Welcome to Westminster Car Park Manager =====");
                break;
            case ADD_VEHICLE_MENU:
                System.out.println("Vehicle Types\nV - Van\nC - Car\nM - Motor Bike");
                break;
            case ADD_VEHICLE_CONTINUE:
                System.out.println("Do you want add another vehicle (Y/N) ?");
                break;
            case DELETE_VEHICLE_CONTINUE:
                System.out.println("Do you want delete another vehicle (Y/N) ?");
                break;
            case QUIT:
                break;
        }
    }

    public char getInputForMenu() {
        return getInput("[a,r,d,t,f,l,c,v,q]", MenuTypes.MENU);
    }

    public char getVehicleChoiceAddVehicle() {
        return getInput("[v,c,m]", MenuTypes.ADD_VEHICLE_MENU);
    }

    public char getContinueChoiceAddVehicle() {
        return getInput("[y,n]", MenuTypes.ADD_VEHICLE_CONTINUE);
    }

    public char getContinueChoiceDeleteVehicle() {
        return getInput("[y,n]", MenuTypes.DELETE_VEHICLE_CONTINUE);
    }

    private char getInput(String choices, MenuTypes type) {
        boolean isValidated = false;
        String option;

        do {
            designForView(type);
            System.out.print("Enter the options - ");
            option = sc.next().toLowerCase();

            if (option.matches(choices)) {
                isValidated = true;
            } else {
                System.out.println("Invalid input Try again\n");
            }
        } while (!isValidated);

        return option.charAt(0);
    }
}
