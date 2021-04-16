package main.archer;

import main.archer.Archer;
import main.bow.ISeniorBow;

public class SeniorArcher extends Archer {
    public SeniorArcher(String name, char gender, int age, ISeniorBow bow, int yearsOfExperience) {
        super(name, gender, age, bow, yearsOfExperience,"senior");
    }

    @Override
    public boolean validateYearsOfExperience(int years) {
        return (years>=3);
    }

    @Override
    public int getNumberOfShots() {
        return 60;
    }

    @Override
    public double getMissChance() {
        return 5;
    }

    @Override
    public int getMinScore() {
        return 6;
    }

}
