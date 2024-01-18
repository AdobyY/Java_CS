package  com.shpp.p2p.cs.byarinko.assignment1;

import com.shpp.karel.KarelTheRobot;

public class MyKarel extends KarelTheRobot{

    public void run() throws Exception{

        findCenter();

    }

    private void findCenter() throws Exception {
        int numberOfXSquares = 1;
        int numverOfYSquares = 1;

        while(frontIsClear()){
            move();
            numberOfXSquares++;
        }

        turnLeft();
        while(frontIsClear()){
            move();
            numverOfYSquares++;
        }

        if(numberOfXSquares % 2 == 1 && numverOfYSquares % 2 == 1){
            say("Зараз буде");
            pause();
            turnLeft();
            for (int i = 0; i<numberOfXSquares/2; i++){
                move();
            }

            turnLeft();
            for (int i = 0; i<numverOfYSquares/2; i++){
                move();
            }
            say("Знайдено середину!!");

        }else{
            say("Неможливо знайти середину");
        }

    }

    private void cleanSecondRowAndMoveToNext() throws Exception {
        while (notFacingWest()){
            turnLeft();
        }
        cleanRow();
        if(frontIsClear()){
            move();
        }
    }

    private void moveToNext() throws Exception {
        while(notFacingNorth()){
            turnLeft();

        }
        if (frontIsClear()){
            move();
        }
    }

    private void cleanFirstRowAndMoveToNext() throws Exception{
        while (notFacingEast()){
            turnLeft();
        }
        cleanRow();
        turnLeft();
        if (frontIsClear()){
            move();
        }
    }

    private void cleanRow() throws Exception{
        cleanTheCell();
        while(frontIsClear()){
            move();
            cleanTheCell();
        }
    }

    private void cleanTheCell() throws Exception {
        while (beepersPresent()){
            pickBeeper();
        }
    }


}