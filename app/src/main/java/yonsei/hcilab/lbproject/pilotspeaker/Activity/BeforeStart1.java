package yonsei.hcilab.lbproject.pilotspeaker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import yonsei.hcilab.lbproject.pilotspeaker.R;

public class BeforeStart1 extends AppCompatActivity {

    Button btnAfter;

    @Override
    public void onBackPressed() {
        //
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_start1);


        btnAfter = (Button)findViewById(R.id.btn_before_start1_after);
        btnAfter.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeforeStart1.this, BeforeStart2.class);
                startActivity(intent);
            }
        });



    }
}
