package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

import java.util.Random;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    private PigGameState pigGameState;
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        pigGameState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == pigGameState.getId()) {
            return true;
        }
        return false;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof PigHoldAction) {
            if (pigGameState.getId() == 0) {
                pigGameState.setP0Score(pigGameState.getP0Score() + pigGameState.getRunningTotal());
                pigGameState.setRunningTotal(0);
                if(playerNames.length > 1)
                    pigGameState.setId(1);
                return true;
            } else {
                pigGameState.setP1Score(pigGameState.getP1Score() + pigGameState.getRunningTotal());
                pigGameState.setRunningTotal(0);
                pigGameState.setId(0);
                return true;
            }
        } else if (action instanceof PigRollAction) {
            Random rand = new Random();
            pigGameState.setCurrDieVal(rand.nextInt(6)+1);
            if(pigGameState.getCurrDieVal()!=1) {
                pigGameState.setRunningTotal(pigGameState.getRunningTotal()
                        + pigGameState.getCurrDieVal());
            } else {
                pigGameState.setRunningTotal(0);
                if(pigGameState.getId() == 0) {
                    if (playerNames.length > 1) {
                        pigGameState.setId(1);
                    }
                } else if(pigGameState.getId() == 1) {
                    pigGameState.setId(0);
                }
            }
            return true;
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PigGameState copyGameState = new PigGameState(pigGameState);
        p.sendInfo(copyGameState);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {

            if(pigGameState.getP0Score() >= 50) {
                return playerNames[0] + " wins! They got a score of " + pigGameState.getP0Score();
            }
      else
            if(pigGameState.getP1Score() >= 50) {
                return playerNames[1] + " wins! They got a score of " + pigGameState.getP1Score();
            }

        return null;
    }

}// class PigLocalGame
