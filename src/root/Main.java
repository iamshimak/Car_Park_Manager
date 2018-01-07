package root;

public class Main {

    public static void main(String[] args) {
	// write your code here
        WestminsterCarParkManagerView view = new WestminsterCarParkManagerView();
        WestminsterCarParkManager westminsterCarParkManager = new WestminsterCarParkManager(view);
        westminsterCarParkManager.start();
    }

}
