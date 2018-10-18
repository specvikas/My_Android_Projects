package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int score_A = 0;
    int score_B = 0;

    public void addThreeForA(View v){
        score_A += 3;
        displayForA(score_A);
    }

    public void addTwoForA(View v){
        score_A += 2;
        displayForA(score_A);
    }

    public void addOneForA(View v){
        score_A += 1;
        displayForA(score_A);
    }


    public void addThreeForB(View v){
        score_B += 3;
        displayForB(score_B);
    }

    public void addTwoForB(View v){
        score_B += 2;
        displayForB(score_B);
    }

    public void addOneForB(View v){
        score_B += 1;
        displayForB(score_B);
    }

    private void displayForA(int score){
        TextView scoreBoard = (TextView)findViewById(R.id.ScoreOfA);
        scoreBoard.setText(String.valueOf(score));
    }


    private void displayForB(int score){
        TextView scoreBoard = (TextView)findViewById(R.id.ScoreOfB);
        scoreBoard.setText(String.valueOf(score));
    }

    public void reset(View v)
    {
        score_A = 0;
        score_B = 0;
        displayForA(score_A);
        displayForB(score_B);
    }
}
