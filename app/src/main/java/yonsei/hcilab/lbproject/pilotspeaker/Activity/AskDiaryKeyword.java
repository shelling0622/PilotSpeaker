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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import yonsei.hcilab.lbproject.pilotspeaker.R;

public class AskDiaryKeyword extends AppCompatActivity {

    private String filePath;
    private String fileName;

    ImageView imgAskDiaryKeywordIcon;
    ImageView imgAskDiaryKeywordRecording;
    Button btnAskDiaryKeywordFinish;

    private MediaPlayer mediaPlayer;
    private MediaRecorder mediaRecorder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_diary_keyword);

        imgAskDiaryKeywordIcon = (ImageView) findViewById(R.id.img_askdiarykeyword_icon);
        imgAskDiaryKeywordRecording = (ImageView) findViewById(R.id.img_askdiarykeyword_recording);
        btnAskDiaryKeywordFinish = (Button) findViewById(R.id.btn_askdiarykeyword_finish);

        try {
            playAudio(R.raw.message2);

        } catch (Exception e) {

        }

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                imgAskDiaryKeywordIcon.setImageResource(R.drawable.microphone);
                imgAskDiaryKeywordRecording.setVisibility(View.VISIBLE);
                btnAskDiaryKeywordFinish.setVisibility(View.VISIBLE);

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
                imgAskDiaryKeywordRecording.setAnimation(animation);

                if ((ActivityCompat.checkSelfPermission(AskDiaryKeyword.this, Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(AskDiaryKeyword.this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            10);
                } else {
                    recordOn();
                }

            }
        }, 3500);

        btnAskDiaryKeywordFinish.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordOff();
                Intent intent = new Intent(AskDiaryKeyword.this, AskEmotion.class);
                startActivity(intent);
            }
        });

    }

    public void setFileNameAndPath() {

        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        fileName = "keyword" + timestampFormat.format(new Date()).toString() + ".mp4";
        filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PilotSpeaker/Keyword/";

        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void recordOn() {

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

        mediaPlayer = MediaPlayer.create(AskDiaryKeyword.this, url);
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
                //User denied Permission.
            }
        }
    }

}
