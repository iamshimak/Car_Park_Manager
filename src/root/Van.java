package root;

/**
 * Created by ShimaK on 11-Nov-16.
 */
class Van extends Vehicle {

    private Double cargoVolume;

    public Van(String ID, String brand, DateTime entryTime, Double cargoVolume) {
        super(ID,brand,entryTime);
        this.cargoVolume = cargoVolume;
    }

    public Double getCargoVolume() {
        return cargoVolume;
    }

}
