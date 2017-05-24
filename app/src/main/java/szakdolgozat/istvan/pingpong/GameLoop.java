package szakdolgozat.istvan.pingpong;

import android.graphics.Canvas;

/**
 * Created by Pisti on 2017. 03. 28..
 */

public class GameLoop extends Thread {
    static final int FPS = 30;
    private GameView gameView;
    private boolean running = false;
    private GameLogic gameLogic;

    public GameLoop(GameView gameView, GameLogic gameLogic)
    {
        this.gameView = gameView;
        this.gameLogic = gameLogic;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        while (running) {
            Canvas c = null;
            startTime = System.currentTimeMillis();
            try {
                c = gameView.getHolder().lockCanvas();
                synchronized (gameView.getHolder()) {
                    gameLogic.nextStep();
                    gameView.draw(c);
                }
            } finally {
                if (c != null) {
                    gameView.getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {}
        }
    }

    public boolean isRunning() {
        return running;
    }
}
