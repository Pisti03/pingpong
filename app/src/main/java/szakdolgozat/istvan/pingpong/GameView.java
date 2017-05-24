package szakdolgozat.istvan.pingpong;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

/**
 * Created by Pisti on 2017. 03. 22..
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private Bitmap bmp;
    private int kepX, kepY;
    private int width, height;
    private GameLoop gameLoop;
    private GameLogic gameLogic;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        holder = getHolder();
        holder.addCallback(this);
        this.width = Resources.getSystem().getDisplayMetrics().widthPixels;
        this.height = Resources.getSystem().getDisplayMetrics().heightPixels;
        gameLogic = new GameLogic(width, height);
        gameLoop = new GameLoop(this, gameLogic);
        setFocusable(true);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawColor(Color.WHITE);
        Ball temp = gameLogic.getGameState().getBall();
        paint.setColor(temp.getColor());
        canvas.drawCircle((float) temp.getX(), (float) temp.getY(), temp.getSize(), paint);
        Player temp2 = gameLogic.getGameState().getPlayer1();
        paint.setColor(temp2.getColor());
        canvas.drawRect((float) (temp2.getX() - temp2.getWidth() / 2), (float) (temp2.getY() - temp2.getHeight() / 2), (float) (temp2.getX() + temp2.getWidth() / 2), (float) (temp2.getY() + temp2.getHeight() / 2), paint);
        temp2 = gameLogic.getGameState().getPlayer2();
        paint.setColor(temp2.getColor());
        canvas.drawRect((float) (temp2.getX() - temp2.getWidth() / 2), (float) (temp2.getY() - temp2.getHeight() / 2), (float) (temp2.getX() + temp2.getWidth() / 2), (float) (temp2.getY() + temp2.getHeight() / 2), paint);
        paint.setAlpha(50); //Put a value between 0 and 255
        paint.setColor(Color.GRAY); //Put your line color
        canvas.drawLine(0, height/2, width, height/2, paint);
        //paint.setStrokeWidth(5); //Choose the width of your line
        /*kepX+=5;
        kepY+=5;
        canvas.drawBitmap(this.bmp, kepX-(bmp.getWidth()/2), kepY-(bmp.getHeight()/2), null);*/
    }


    public void rajzol(int x, int y) {
        kepX = x;
        kepY = y;
        Canvas canvas = holder.lockCanvas();
        canvas.drawBitmap(this.bmp, kepX - (bmp.getWidth() / 2), kepY - (bmp.getHeight() / 2), null);
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        System.out.println(w + " " + h);
        this.width = w;
        this.height = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        gameLoop.setRunning(false);
        while (retry) {
            try {
                gameLoop.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameLoop.setRunning(true);
        gameLoop.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        gameLogic.movePlayer1(x);
        return true;
    }

    public void restart()
    {
        gameLogic.restart();
        continueGame();

    }

    public void pauseGame(){
        gameLoop.setRunning(false);
        boolean retry = true;
        gameLoop.setRunning(false);
        while (retry) {
            try {
                gameLoop.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public void continueGame()
    {
        gameLoop = new GameLoop(this, gameLogic);
        gameLoop.setRunning(true);
        gameLoop.start();
    }
}

