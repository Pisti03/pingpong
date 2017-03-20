package szakdolgozat.istvan.pingpong;

import android.app.Application;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void onButtonSingle(View view){
        Toast.makeText(getApplicationContext(), "Single Player", Toast.LENGTH_SHORT).show();
    }

    public void onButtonMulti(View view){
        Toast.makeText(getApplicationContext(), "Multi Player", Toast.LENGTH_SHORT).show();
    }

    public void onButtonHighScores(View view){
        Toast.makeText(getApplicationContext(), "High Scores", Toast.LENGTH_SHORT).show();
    }

    public void onButtonSettings(View view){
        Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
    }

    public void onButtonExit(View view){
        System.exit(0);
    }

}
