package main.main;

import main.archer.Archer;
import main.archer.JuniorArcher;
import main.archer.SeniorArcher;
import main.archer.VeteranArcher;
import main.bow.AluminumBow;
import main.bow.CarbonBow;
import main.bow.WoodenBow;
import main.club.Club;
import main.util.Util;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Club club = new Club("IT Archers","sofia","krasi");

        for (int i = 0; i < 40; i++) {
            int num = Util.randmoize(1,3);
            switch (num){
                case 1:
                    WoodenBow bow = new WoodenBow("scott",23,Util.randmoize(20,30));
                    if(new Random().nextBoolean()){
                        Archer archer = new JuniorArcher(Util.getManName(),'m',Util.randmoize(12,52),bow,Util.randmoize(1,4));
                        club.addArcher(archer);
                    }else{
                        Archer archer = new JuniorArcher(Util.getWomanName(),'f',Util.randmoize(12,52),bow,Util.randmoize(1,4));
                        club.addArcher(archer);
                    }
                    break;
                case 2:
                    AluminumBow bow2 = new AluminumBow("Renegade",14,Util.randmoize(25,40));
                    CarbonBow bow3 = new CarbonBow("Hoyt",15,Util.randmoize(28,48));
                    if(new Random().nextBoolean()){
                        if(new Random().nextBoolean()){
                            Archer archer = new SeniorArcher(Util.getManName(),'m',Util.randmoize(12,52),bow2,Util.randmoize(3,10));
                            club.addArcher(archer);
                        }else{
                            Archer archer = new SeniorArcher(Util.getWomanName(),'f',Util.randmoize(12,52),bow2,Util.randmoize(3,10));
                            club.addArcher(archer);
                        }
                    }else{
                        if(new Random().nextBoolean()){
                            Archer archer = new SeniorArcher(Util.getManName(),'m',Util.randmoize(12,52),bow3,Util.randmoize(3,10));
                            club.addArcher(archer);
                        }else{
                            Archer archer = new SeniorArcher(Util.getWomanName(),'f',Util.randmoize(12,52),bow3,Util.randmoize(3,10));
                            club.addArcher(archer);
                        }
                    }
                    break;
                case 3:
                    CarbonBow carbonBow = new CarbonBow("Darton",23,Util.randmoize(28,48));
                    if(new Random().nextBoolean()){
                        Archer archer = new VeteranArcher(Util.getManName(),'m',Util.randmoize(12,52),carbonBow,Util.randmoize(10,15));
                        club.addArcher(archer);
                    }else{
                        Archer archer = new VeteranArcher(Util.getWomanName(),'f',Util.randmoize(12,52),carbonBow,Util.randmoize(10,15));
                        club.addArcher(archer);
                    }
                    break;
            }

        }

        System.out.println();
        club.organizeTournament();
    }

}
