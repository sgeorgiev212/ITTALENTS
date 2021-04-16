package main.archer;

import main.archer.Archer;
import main.bow.WoodenBow;

public class JuniorArcher extends Archer {
    public JuniorArcher(String name, char gender, int age, WoodenBow bow, int yearsOfExperience) {
        super(name, gender, age, bow, yearsOfExperience, "junior");
    }

    @Override
    public boolean validateYearsOfExperience(int years) {
        return (years>=1);
    }

    @Override
    public int getNumberOfShots() {
        return 30;
    }

    @Override
    public double getMissChance() {
        return 10;
    }

    @Override
    public int getMinScore() {
        return 1;
    }


}
