package com.thosecreepypeople.fighterinsimpletimes;

/**
 * Created by Heather on 4/22/2015.
 * This class is to design the AI for the gladiator
 */
public class EnemyGladiator extends Gladiator{
    public void chooseMovement(int level){
        int levelNum=level;
        switch(level){
            case 1:
               // random();random movement
                break;
            case 2:
                //follow();Call the method that John made=follow sprite
                break;
        }
    }
}
