package parts;

import parts.CarPart;

public class Frame extends CarPart {
    public Frame() {
        super("frame");
    }

    @Override
    public int getBuildTimeSeconds() {
        return 5;
    }
}
