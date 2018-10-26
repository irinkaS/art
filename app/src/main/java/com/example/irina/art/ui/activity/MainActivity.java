package com.example.irina.art.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.irina.art.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main");
    }

    public void toClassification(View view){
        Intent myIntent = new Intent(this, ClassificationActivity.class);
//        myIntent.putExtra("key", value);
        this.startActivity(myIntent);
    }
}
