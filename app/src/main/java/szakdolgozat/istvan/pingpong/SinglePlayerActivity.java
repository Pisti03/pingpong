package szakdolgozat.istvan.pingpong;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

public class SinglePlayerActivity extends AppCompatActivity {
    private GameView gameView;
    private boolean paused = false;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        gameView = (GameView) findViewById(R.id.gameView); //new GameView(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if(!paused) {
                gameView.pauseGame();
                paused = true;
                callPopUp();
            } else{
                popupWindow.dismiss();
                gameView.continueGame();
                paused = false;
            }
            return false;
        }
        return true;
    }


    private void callPopUp()
    {

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.pausepopup, null);

        Button buttonContinue = (Button) popupView.findViewById(R.id.buttonContinue);
        buttonContinue.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
                gameView.continueGame();
                paused=false;
            }
        });
        Button buttonRestart = (Button) popupView.findViewById(R.id.buttonRestart);
        buttonRestart.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
                gameView.restart();
                paused=false;
            }
        });
        Button buttonExit = (Button) popupView.findViewById(R.id.buttonExit2);
        buttonExit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
                finish();
            }
        });

        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
            }
}
