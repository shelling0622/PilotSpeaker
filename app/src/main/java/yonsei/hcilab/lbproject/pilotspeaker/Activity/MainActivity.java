package yonsei.hcilab.lbproject.pilotspeaker.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import yonsei.hcilab.lbproject.pilotspeaker.R;

public class MainActivity extends AppCompatActivity {

    Button btnMainStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMainStart = (Button)findViewById(R.id.btn_main_start);
        btnMainStart.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecordNListenStory.class);
                startActivity(intent);
            }
        });

    }

}
