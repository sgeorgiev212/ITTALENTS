package parts;

import parts.CarPart;

public class Seat extends CarPart {
    public Seat() {
        super("seat");
    }

    @Override
    public int getBuildTimeSeconds() {
        return 3;
    }
}
