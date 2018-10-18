package com.example.android.mediaplayertesting;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "OnCreate..",Toast.LENGTH_SHORT).show();

        final Button play = findViewById(R.id.play);

        song = MediaPlayer.create(this, R.raw.antenna);
        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                song.start();
            }
        });

        Button pause = findViewById(R.id.pause);

        pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                song.pause();
            }
        });

        song.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){

            public void onCompletion(MediaPlayer song)
            {
                Toast.makeText(MainActivity.this, "File Done!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "OnStart..",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "OnStop..",Toast.LENGTH_SHORT).show();
        song.pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "OnRestart..",Toast.LENGTH_SHORT).show();
        song.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Resuming..",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Paused..",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Destroying..",Toast.LENGTH_SHORT).show();

    }
}
