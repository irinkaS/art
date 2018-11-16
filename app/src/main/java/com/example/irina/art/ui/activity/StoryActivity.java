package com.example.irina.art.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.irina.art.R;
import com.example.irina.art.ui.adapter.ProgressBarRecyclerViewAdapter;

public class StoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBarRecyclerViewAdapter progressBarRecyclerViewAdapter;




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
                ProgressBarRecyclerViewAdapter(quantity, duration);
        recyclerView.setAdapter(progressBarRecyclerViewAdapter);
        progressBarRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void toNextSlideOfStory(View view) {
        progressBarRecyclerViewAdapter.makeCurrentProgressBarEqual100Percent();
    }

}
