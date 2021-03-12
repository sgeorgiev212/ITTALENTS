package gastation_task;

import java.time.LocalDateTime;

public class ClientInfo {

    private FuelColumn column;
    private String fuelType;
    private double fuelAmount;
    private LocalDateTime time;

    public ClientInfo(FuelColumn column, String fuelType, double fuelAmount, LocalDateTime time) {
        this.column = column;
        this.fuelType = fuelType;
        this.fuelAmount = fuelAmount;
        this.time = time;
    }

    public FuelColumn getColumn() {
        return column;
    }

    public String getFuelType() {
        return fuelType;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
