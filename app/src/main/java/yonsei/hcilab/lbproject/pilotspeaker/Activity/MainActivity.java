package yonsei.hcilab.lbproject.pilotspeaker.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import yonsei.hcilab.lbproject.pilotspeaker.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("a", MODE_PRIVATE);
        int first = sp.getInt("first", 0);

        if(first != 1){
            Intent intent = new Intent(MainActivity.this, BeforeStart1.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(MainActivity.this, Mainpage.class);
            startActivity(intent);
        }

    }
}
