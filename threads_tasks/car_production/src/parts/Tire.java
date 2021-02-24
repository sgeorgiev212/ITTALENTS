package parts;

import parts.CarPart;

public class Tire extends CarPart {
    public Tire() {
        super("tire");
    }

    @Override
    public int getBuildTimeSeconds() {
        return 2;
    }
}
