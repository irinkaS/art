package com.example.irina.art;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story); // shift f6
        setTitle("Story");
        Intent intent = this.getIntent();
        String artistName = intent.getStringExtra("artistName");
        TextView textView = findViewById(R.id.textField4);
        textView.setText(artistName);
    }
//
//    public void intent(View view){
//        Intent myIntent = new Intent(this, ArtistsActivity.class);
////        myIntent.putExtra("key", value); //Optional parameters
//        this.startActivity(myIntent);
//    }
}
