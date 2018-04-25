package yonsei.hcilab.lbproject.pilotspeaker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import yonsei.hcilab.lbproject.pilotspeaker.R;

public class BeforeStart4 extends AppCompatActivity {

    Button btnBefore;
    Button btnAfter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_start4);

        btnBefore = (Button)findViewById(R.id.btn_before_start4_before);
        btnAfter = (Button)findViewById(R.id.btn_before_start4_after);

        btnBefore.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAfter.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeforeStart4.this, StartApp.class);
                startActivity(intent);
            }
        });

    }
}
