package yonsei.hcilab.lbproject.pilotspeaker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import yonsei.hcilab.lbproject.pilotspeaker.R;

public class Mainpage extends AppCompatActivity {

    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        btnStart = (Button)findViewById(R.id.btn_mainpage);
        btnStart.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mainpage.this, RecordDiary.class);
                startActivity(intent);
            }
        });

    }
}
