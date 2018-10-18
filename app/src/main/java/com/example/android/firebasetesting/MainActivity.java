package com.example.android.firebasetesting;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    private Button AddFireBase;

    private EditText Name;

    private EditText Value;

    private Firebase mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        mRootRef = new Firebase("https://fir-testing-5d83f.firebaseio.com/Users");

        AddFireBase = findViewById(R.id.button);

        Name = findViewById(R.id.Name);

        Value = findViewById(R.id.Value);

        AddFireBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = Name.getText().toString();

                String value = Value.getText().toString();

                Firebase mRefChild = mRootRef.child(name);

                mRefChild.setValue(value);

            }
        });
    }
}
