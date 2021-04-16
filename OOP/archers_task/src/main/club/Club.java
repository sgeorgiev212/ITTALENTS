package main.club;

import main.archer.Archer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Club {

    private String name;
    private String address;
    private String coach;
    private HashSet<Archer> archers;
    private HashMap<String,HashSet<Archer>> statistics;

    public Club(String name, String address, String coach) {
        this.name = name;
        this.address = address;
        this.coach = coach;
        this.archers = new HashSet<>();
        this.statistics = new HashMap<>();
    }

    public void addArcher(Archer a){
        this.archers.add(a);
    }

    public void organizeTournament(){
          this.printArchersByName();
        for (Archer archer : archers) {
            archer.attend();
            archer.shoot();
            addArcherToStatistics(archer);
        }

        this.printWinnerForCategory();
        printAverageForCategory();
        printShooterWithMostTens();
        printShooterWIthMostMisses();
        printWomenByAccuracy();
        printMenByYears();

    }

    public void printArchersByName(){
        ArrayList<Archer> archersList = new ArrayList<>();
        archersList.addAll(this.archers);
        archersList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        System.out.println("---------------Archers by name--------------------");
        for (Archer archer : archersList) {
            System.out.println(archer.getName()+" "+archer.getScore());
        }
    }

    public void addArcherToStatistics(Archer archer){
        if(!this.statistics.containsKey(archer.getType())){
            this.statistics.put(archer.getType(),new HashSet<Archer>());
        }

        statistics.get(archer.getType()).add(archer);
    }

    public void printWinnerForCategory() {
        ArrayList<Map.Entry<String, HashSet<Archer>>> list = new ArrayList<>();
        list.addAll(statistics.entrySet());
        for (Map.Entry<String, HashSet<Archer>> entry : list) {
            System.out.println("------------Best shooter for "+entry.getKey()+"------------------");
              ArrayList<Archer> archers = new ArrayList<>();
              archers.addAll(entry.getValue());
              archers.sort((o1, o2) -> o2.getScore()-o1.getScore());
            System.out.println(archers.get(0).getName()+" "+archers.get(0).getScore());
        }
    }

    public void printAverageForCategory(){
        ArrayList<Map.Entry<String, HashSet<Archer>>> list = new ArrayList<>();
        list.addAll(statistics.entrySet());
        for (Map.Entry<String, HashSet<Archer>> entry : list) {
            System.out.println("------------Average score for "+entry.getKey()+"-----------------");
            ArrayList<Archer> archers = new ArrayList<>();
            archers.addAll(entry.getValue());
            int number = 0;
            double score = 0;
            for (Archer archer : archers) {
                score+=archer.getScore();
                number++;
            }
            System.out.println(score/number);
        }
    }

    public void printShooterWithMostTens(){
        Archer bestArcher = null;
        for (Map.Entry<String, HashSet<Archer>> entry : statistics.entrySet()) {
         ArrayList<Archer> list = new ArrayList<>();
         list.addAll(entry.getValue());
         if(bestArcher==null){
             bestArcher=list.get(0);
         }
            for (Archer archer : list) {
                if(archer.getTens()>bestArcher.getTens()){
                    bestArcher=archer;
                }
            }
        }
        System.out.println("------------Archer with most tens-----------------");
        System.out.println(bestArcher.getName()+" "+bestArcher.getTens());
    }

    public void printShooterWIthMostMisses(){
        Archer worstArcher = null;
        for(Map.Entry<String,HashSet<Archer>> entry : statistics.entrySet()){
            ArrayList<Archer> archers = new ArrayList<>();
            archers.addAll(entry.getValue());
            if(worstArcher == null) {
                worstArcher = archers.get(0);
            }
                for (Archer archer : archers) {
                    if(archer.getMisses()>worstArcher.getMisses()){
                        worstArcher = archer;
                    }
                }
            }
        System.out.println("------------Archer with most misses-----------------");
        System.out.println(worstArcher.getName()+" "+worstArcher.getMisses());
    }

    public void printWomenByAccuracy(){
        System.out.println("------------------Women By Accuracy for each category------------------------------------");
        for (Map.Entry<String, HashSet<Archer>> entry : statistics.entrySet()) {
            System.out.println("------------------------------"+entry.getKey()+"-------------------------------------");
            ArrayList<Archer> women = new ArrayList<>();
            for (Archer archer : entry.getValue()) {
                if(archer.getGender()=='f'){
                    women.add(archer);
                }
            }
            women.sort((o1, o2) -> o2.getScore()-o1.getScore());
            for (Archer woman : women) {
                System.out.println(woman.getName()+" "+woman.getScore());
            }
        }
    }
    
    public void printMenByYears(){
        ArrayList<Archer> menWithCarbons = new ArrayList<>();
        for (Map.Entry<String, HashSet<Archer>> entry : statistics.entrySet()) {
            for (Archer archer : entry.getValue()) {
                if(archer.getBow().getBowType().equals("carbon") && archer.getGender()=='m'){
                    menWithCarbons.add(archer);
                }
            }
        }
        menWithCarbons.sort((o1, o2) -> o2.getYearsOfExperience() - o1.getYearsOfExperience());
        System.out.println("------------------------------Men with carbon bows Sorted By Years Of Experience-------------------------------------");
        for (Archer arc : menWithCarbons) {
            System.out.println(arc.getName()+" "+arc.getYearsOfExperience());
        }
    }

}
