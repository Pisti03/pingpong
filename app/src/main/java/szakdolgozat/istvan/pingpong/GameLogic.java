package szakdolgozat.istvan.pingpong;

import android.graphics.Color;

/**
 * Created by Pisti on 2017. 04. 05..
 */

public class GameLogic {
    private double screenWidth, screenHeight;
    private GameState gameState;

    public GameLogic(double x, double y) {
        this.screenWidth = x;
        this.screenHeight = y;
        restart();
    }

    public void restart()
    {
        gameState = new GameState(screenWidth, screenHeight);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void collision(){
        double nextX, nextY;
        nextX = gameState.getBall().getX() + gameState.getBall().getVeloX();
        nextY = gameState.getBall().getY() + gameState.getBall().getVeloY();
        double radius = gameState.getBall().getSize()/2;
        if((nextX + radius >= screenWidth) || (nextX - radius <= 0))
        {
            gameState.getBall().reverseX();
        }

        if(nextY >= screenHeight)
        {
            gameState.getBall().setPosition(screenWidth/2, screenHeight/2);
            gameState.getBall().generateNewDirection();
        }

        if(nextY <= 0)
        {
            gameState.getBall().setPosition(screenWidth/2, screenHeight/2);
            gameState.getBall().generateNewDirection();
        }

        if((nextX >= gameState.getPlayer1().getX()-gameState.getPlayer1().getWidth()/2 && nextX <= gameState.getPlayer1().getX()+gameState.getPlayer1().getWidth()/2)
                && (nextY + radius >= gameState.getPlayer1().getY()-gameState.getPlayer1().getHeight()/2 && nextY - radius <= gameState.getPlayer1().getY()+gameState.getPlayer1().getHeight()/2))
        {
            gameState.getBall().reverseY();
            if(nextX < gameState.getPlayer1().getX())
                gameState.getBall().setVeloX(Math.abs(gameState.getBall().getVeloX())*-1);
            else
                gameState.getBall().setVeloX(Math.abs(gameState.getBall().getVeloX()));
        }

        if((nextY >= gameState.getPlayer1().getY()-gameState.getPlayer1().getHeight()/2 && nextY <= gameState.getPlayer1().getY()+gameState.getPlayer1().getHeight()/2)
                && (nextX + radius >= gameState.getPlayer1().getX()-gameState.getPlayer1().getWidth()/2 && nextX - radius <= gameState.getPlayer1().getX()+gameState.getPlayer1().getWidth()/2)) {
            gameState.getBall().reverseX();
            gameState.getBall().reverseY();
        }

        if((nextX >= gameState.getPlayer2().getX()-gameState.getPlayer2().getWidth()/2 && nextX <= gameState.getPlayer2().getX()+gameState.getPlayer2().getWidth()/2)
                && (nextY - radius <= gameState.getPlayer2().getY()+gameState.getPlayer2().getHeight()/2 && nextY + radius >= gameState.getPlayer2().getY()-gameState.getPlayer2().getHeight()/2))
            gameState.getBall().reverseY();

        if((nextY <= gameState.getPlayer2().getY()+gameState.getPlayer2().getHeight()/2 && nextY >= gameState.getPlayer2().getY()-gameState.getPlayer2().getHeight()/2)
                && (nextX + radius >= gameState.getPlayer2().getX()-gameState.getPlayer2().getWidth()/2 && nextX - radius <= gameState.getPlayer2().getX()+gameState.getPlayer2().getWidth()/2)) {
            gameState.getBall().reverseX();
            gameState.getBall().reverseY();
        }
    }

    public void movePlayer1(int x) {
        if(!((x-gameState.getPlayer1().getWidth()/2) < 0 || (x + gameState.getPlayer1().getWidth()/2) > screenWidth))
        {
            if(gameState.getPlayer1().getMaxSpeed()!=0) {
                if (Math.abs(gameState.getPlayer1().getX() - gameState.getBall().getX()) < gameState.getPlayer1().getMaxSpeed())
                    gameState.getPlayer1().setX(gameState.getBall().getX());
                else if (x < gameState.getPlayer1().getX())
                    gameState.getPlayer1().setX(gameState.getPlayer1().getX() - gameState.getPlayer1().getMaxSpeed());
                else
                    gameState.getPlayer1().setX(gameState.getPlayer1().getX() + gameState.getPlayer1().getMaxSpeed());
            } else
                gameState.getPlayer1().setX(x);
        }
    }

    public void movePlayer2()
    {
        if(!(((gameState.getPlayer2().getX()-gameState.getPlayer2().getWidth()/2 < 0) && gameState.getPlayer2().getX()>gameState.getBall().getX())
                || (((gameState.getPlayer2().getX() + gameState.getPlayer2().getWidth()/2) > screenWidth) && gameState.getPlayer2().getX()<gameState.getBall().getX()) ))
        {
            if(gameState.getPlayer2().getMaxSpeed()!=0) {
                if (Math.abs(gameState.getPlayer2().getX() - gameState.getBall().getX()) < gameState.getPlayer2().getMaxSpeed())
                    gameState.getPlayer2().setX(gameState.getBall().getX());
                else if (gameState.getBall().getX() < gameState.getPlayer2().getX())
                    gameState.getPlayer2().setX(gameState.getPlayer2().getX() - gameState.getPlayer2().getMaxSpeed());
                else
                    gameState.getPlayer2().setX(gameState.getPlayer2().getX() + gameState.getPlayer2().getMaxSpeed());
            } else
                gameState.getPlayer2().setX(gameState.getBall().getX());
        }


    }
    public void nextStep(){
        collision();
        gameState.getBall().nextPosition();
        movePlayer2();
    }
}
