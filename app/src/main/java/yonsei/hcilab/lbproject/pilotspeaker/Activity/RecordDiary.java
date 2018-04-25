package yonsei.hcilab.lbproject.pilotspeaker.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import yonsei.hcilab.lbproject.pilotspeaker.R;

public class RecordDiary extends AppCompatActivity {

    private String filePath;
    private String fileName;

    ImageView imgRecordDiaryIcon;
    ImageView imgRecordDiaryRecording;
    Button btnRecordDiaryFinish;

    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Toast.makeText(this, "기록 진행중입니다.", Toast.LENGTH_LONG);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_diary);

        imgRecordDiaryIcon = (ImageView) findViewById(R.id.img_recorddiary_icon);
        imgRecordDiaryRecording = (ImageView) findViewById(R.id.img_recorddiary_recording);
        btnRecordDiaryFinish = (Button) findViewById(R.id.btn_recorddiary_finish);

        try {
            playAudio(R.raw.message1);

        } catch (Exception e) {

        }

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                imgRecordDiaryIcon.setImageResource(R.drawable.microphone);
                imgRecordDiaryRecording.setVisibility(View.VISIBLE);
                btnRecordDiaryFinish.setVisibility(View.VISIBLE);

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
                imgRecordDiaryRecording.setAnimation(animation);

                if ((ActivityCompat.checkSelfPermission(RecordDiary.this, Manifest.permission.RECORD_AUDIO)
                        == PackageManager.PERMISSION_GRANTED)) {
                    recordOn();

                }else{
                    Toast.makeText(RecordDiary.this, "Record no",Toast.LENGTH_LONG).show();
                }


            }
        }, 2500);

        btnRecordDiaryFinish.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordOff();
                Intent intent = new Intent(RecordDiary.this, AskDiaryKeyword.class);
                startActivity(intent);
            }
        });

    }

    public void setFileNameAndPath() {

        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        fileName = "diary" + timestampFormat.format(new Date()).toString() + ".mp4";
        filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PilotSpeaker/Diary/";

        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void recordOn() {


        Toast.makeText(RecordDiary.this, "Record",Toast.LENGTH_LONG).show();


        setFileNameAndPath();
        if (mediaRecorder != null) {
            mediaRecorder.release();
        }

        mediaRecorder = new MediaRecorder();

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setOutputFile(filePath + fileName);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Exception e) {
            Log.e("Audio Recorder", "Exception", e);
        }

    }

    public void recordOff() {
        if (mediaRecorder == null) {
            return;
        }

        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaRecorder = new MediaRecorder();
    }

    private void playAudio(int url) throws Exception {
        killMediaPlayer();

        mediaPlayer = MediaPlayer.create(RecordDiary.this, url);
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                recordOn();
            } else {
                Toast.makeText(RecordDiary.this, "앱을 종료시켰다 다시 접속해주세요.", Toast.LENGTH_LONG).show();
                //User denied Permission.
            }
        }
    }

}
