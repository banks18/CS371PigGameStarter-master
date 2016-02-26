package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by banks18 on 2/25/2016.
 */

public class PigGameState extends GameState {
    private int id;
    private int p0Score = 0;
    private int p1Score = 0;
    private int runningTotal = 0;
    private int currDieVal;

    public PigGameState() {
        id = 0;
        Random rand = new Random();
        currDieVal = rand.nextInt(6)+1;
    }
    public PigGameState(PigGameState pigGame) {
        this.id = pigGame.getId();
        this.p0Score = pigGame.getP0Score();
        this.p1Score = pigGame.getP1Score();
        this.runningTotal = pigGame.getRunningTotal();
        this.currDieVal = pigGame.getCurrDieVal();
    }
    public int getId() {
        return this.id;
    }
    public int getP0Score() {
        return this.p0Score;
    }
    public int getP1Score() {
        return this.p1Score;
    }
    public int getRunningTotal() {
        return this.runningTotal;
    }
    public int getCurrDieVal() {
        return this.currDieVal;
    }
    public void setId(int i) {
        this.id = i;
    }
    public void setP0Score(int i) {
        this.p0Score = i;
    }
    public void setP1Score(int i) {
        this.p1Score = i;
    }
    public void setRunningTotal(int i) {
        this.runningTotal = i;
    }
    public void setCurrDieVal(int i) {
        this.currDieVal = i;
    }
}
