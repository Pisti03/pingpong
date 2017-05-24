package szakdolgozat.istvan.pingpong;

import android.graphics.Color;

/**
 * Created by Pisti on 2017. 05. 02..
 */

public class GameState {
    private Ball ball;
    private Player player1, player2;

    public GameState(double screenWidth, double screenHeight) {
        ball = new Ball((int)(screenHeight+screenWidth)/90, screenWidth/2, screenHeight/2, screenWidth/80, Color.BLUE);
        ball.generateNewDirection();
        player1 = new Player(0, screenWidth/2, screenHeight - screenHeight/9, screenWidth/5, screenHeight/50, Color.GREEN);
        player2 = new Player(0, screenWidth/2, screenHeight/9, screenWidth/5, screenHeight/50, 5, Color.RED);
    }

    public Ball getBall() {
        return ball;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
