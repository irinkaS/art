package com.example.irina.art.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irina.art.R;
import com.example.irina.art.ui.adapter.ProgressBarRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

public class StoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBarRecyclerViewAdapter progressBarRecyclerViewAdapter;
    private String[] mockData = {"Gogh portrait", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Autoportrait_de_Vincent_van_Gogh.JPG/210px-Autoportrait_de_Vincent_van_Gogh.JPG"};
    int counter = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story); // shift f6
        setTitle("Story");

        Intent intent = this.getIntent();
        Integer artistId = intent.getIntExtra("artistId", -1);


        TextView textView = findViewById(R.id.storyText);
        textView.setText("Loading...");

        initProgressBarsRecyclerView(5, 5000);
    }

    public void initProgressBarsRecyclerView(int quantity, int duration) {
        recyclerView = findViewById(R.id.progressBarRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        progressBarRecyclerViewAdapter = new
                ProgressBarRecyclerViewAdapter(quantity, duration, getOnCompleteFunction(findViewById(R.id.storyText), findViewById(R.id.storyImage)));
        recyclerView.setAdapter(progressBarRecyclerViewAdapter);
        progressBarRecyclerViewAdapter.notifyDataSetChanged();
    }

    @NonNull
    private Runnable getOnCompleteFunction(TextView textView, ImageView imageView) {
        return () -> {
            if (mockData[counter].contains("http")) {
                imageView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.GONE);
                Picasso.get().load(mockData[counter]).into(imageView);
            } else {
                imageView.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                textView.setText(mockData[counter]);
            }
            counter = (counter + 1) % 2;
        };
    }

    public void toNextSlideOfStory(View view) {
        progressBarRecyclerViewAdapter.makeCurrentProgressBarEqual100Percent();
    }

}
