package parts;

import parts.CarPart;

public class Engine extends CarPart {
    public Engine() {
        super("engine");
    }

    @Override
    public int getBuildTimeSeconds() {
        return 7;
    }
}
