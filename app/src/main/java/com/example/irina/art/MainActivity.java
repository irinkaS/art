package com.example.irina.art;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
