package ships_task;

import java.time.LocalDateTime;

public class PackageInfo {

    private int packageId;
    private int dockId;
    private String shipName;
    private int  craneId;
    private LocalDateTime unloading_time;

    public PackageInfo(int packageId, int dockId, String shipName, int craneId, LocalDateTime unloading_time) {
        this.packageId = packageId;
        this.dockId = dockId;
        this.shipName = shipName;
        this.craneId = craneId;
        this.unloading_time = unloading_time;
    }

    public int getPackageId() {
        return packageId;
    }

    public int getDockId() {
        return dockId;
    }

    public String getShipName() {
        return shipName;
    }

    public int getCraneId() {
        return craneId;
    }

    public LocalDateTime getUnloading_time() {
        return unloading_time;
    }
}
