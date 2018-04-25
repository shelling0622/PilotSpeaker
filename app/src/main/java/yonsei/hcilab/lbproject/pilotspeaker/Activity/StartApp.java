package yonsei.hcilab.lbproject.pilotspeaker.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import yonsei.hcilab.lbproject.pilotspeaker.R;

public class StartApp extends AppCompatActivity {

    Button btnStartApp;

    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);

        btnStartApp = (Button)findViewById(R.id.btn_start_app);

        btnStartApp.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("a", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("first", 1);
                editor.commit();

                Intent intent = new Intent(StartApp.this, RecordDiary.class);
                startActivity(intent);

            }
        });

        if ((ActivityCompat.checkSelfPermission(StartApp.this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(StartApp.this, new String[] { Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE },
                    10);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //
            } else {
                Toast.makeText(StartApp.this, "앱을 종료시켰다 다시 접속해주세요.", Toast.LENGTH_LONG).show();
                //User denied Permission.
            }
        }
    }


}
