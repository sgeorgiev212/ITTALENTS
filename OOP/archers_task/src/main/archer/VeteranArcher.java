package main.archer;

import main.archer.Archer;
import main.bow.CarbonBow;

public class VeteranArcher extends Archer {
    public VeteranArcher(String name, char gender, int age, CarbonBow bow, int yearsOfExperience) {
        super(name, gender, age, bow, yearsOfExperience,"veteran");
    }

    @Override
    public boolean validateYearsOfExperience(int years) {
        return (years>=10);
    }

    @Override
    public int getNumberOfShots() {
        return 60;
    }

    @Override
    public double getMissChance() {
        return 0;
    }

    @Override
    public int getMinScore() {
        return 6;
    }
}
