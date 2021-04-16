package main.archer;

import main.util.Util;
import main.bow.IBow;

import java.util.Objects;
import java.util.Random;

public abstract class Archer {

    private String name;
    private char gender;
    private int age;
    private IBow bow;
    private int yearsOfExperience;
    private int numberOfTournaments;
    private String type;
    private int score;
    private int tens;
    private int misses;

    public Archer(String name, char gender, int age, IBow bow, int yearsOfExperience,String type) {
        if(name.length()>=3) {
            this.name = name;
        }
        if(gender=='m'|| gender=='f') {
            this.gender = gender;
        }
        if(this.validateAge(age)) {
            this.age = age;
        }
        this.bow = bow;
        if(this.validateYearsOfExperience(yearsOfExperience)) {
            this.yearsOfExperience = yearsOfExperience;
        }
        this.numberOfTournaments = 0;
        this.type = type;
        this.score = 0;
    }

    public abstract boolean validateYearsOfExperience(int years);

    public boolean validateAge(int age){
        boolean result = false;
        if(age>=12 && age<=52){
            result = true;
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public void attend(){
        this.numberOfTournaments+=1;
    }

    public String getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    public abstract int getNumberOfShots();
    public abstract double getMissChance();
    public abstract int getMinScore();


    public void shoot(){
        for (int i = 0; i < this.getNumberOfShots(); i++) {
            int points;
            Random r = new Random();
            if(r.nextInt()<=this.getMissChance()){
                this.score+=0;
                this.misses++;
            }else{
                points = Util.randmoize(this.getMinScore(),10);
                if(points<10){
                    points+=this.bow.getAim()+this.bow.getStabilizer();
                }
                if(points==10){
                    this.tens++;
                }

                if(points>10){
                    points=10;
                    this.tens++;
                }
              this.score+=points;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Archer archer = (Archer) o;
        return age == archer.age &&
                Objects.equals(name, archer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public int getTens() {
        return tens;
    }

    public int getMisses() {
        return misses;
    }

    public char getGender() {
        return gender;
    }

    public IBow getBow() {
        return bow;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
}
