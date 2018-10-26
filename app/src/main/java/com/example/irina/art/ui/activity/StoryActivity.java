package com.example.irina.art.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.irina.art.R;
import com.example.irina.art.db.AppDatabase;
import com.example.irina.art.model.ArtistItem;

public class StoryActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story); // shift f6
        setTitle("Story");

        progressBar = (ProgressBar) findViewById(R.id.progressbar);


        Intent intent = this.getIntent();
        Integer artistId = intent.getIntExtra("artistId", -1);


        TextView textView = findViewById(R.id.textField4);
        textView.setText("Loading...");
        initializeThread(() -> {
            if (artistId != -1) {
                AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
                ArtistItem artistItem = appDatabase.artistItemDao().findById(artistId);
                textView.setText(artistItem.getArtistName());
            } else {
                textView.setText("Wrong Id");
            }
        });
    }

    void initializeThread(Runnable onComplete) {
        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus++;
                android.os.SystemClock.sleep(50);
                handler.post(() -> progressBar.setProgress(progressStatus));
            }
            handler.post(onComplete);
        }).start();
    }
//
//    public void intent(View view){
//        Intent myIntent = new Intent(this, ArtistsActivity.class);
////        myIntent.putExtra("key", value); //Optional parameters
//        this.startActivity(myIntent);
//    }
}
