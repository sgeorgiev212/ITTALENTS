import java.time.LocalDateTime;

public class Statistics {

    private int packageNumber;
    private int dockNumber;
    private String shipName;
    private int craneId;
    private LocalDateTime time;

    public Statistics(int packageNumber, int dockNumber, String shipName, int craneId, LocalDateTime time) {
        this.packageNumber = packageNumber;
        this.dockNumber = dockNumber;
        this.shipName = shipName;
        this.craneId = craneId;
        this.time = time;
    }

    public int getPackageNumber() {
        return packageNumber;
    }

    public int getDockNumber() {
        return dockNumber;
    }

    public String getShipName() {
        return shipName;
    }

    public int getCraneId() {
        return craneId;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
