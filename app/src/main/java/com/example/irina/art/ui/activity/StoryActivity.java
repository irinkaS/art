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
import com.example.irina.art.dao.ArtistWithStoryItemsDao;
import com.example.irina.art.db.AppDatabase;
import com.example.irina.art.model.ArtistWithStoryItems;
import com.example.irina.art.model.StoryItem;
import com.example.irina.art.ui.adapter.ProgressBarRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBarRecyclerViewAdapter progressBarRecyclerViewAdapter;
    //    private String[] mockData = {"Gogh portrait", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Autoportrait_de_Vincent_van_Gogh.JPG/210px-Autoportrait_de_Vincent_van_Gogh.JPG"};
    int counter = 0;
    private ArtistWithStoryItemsDao artistWithStoryItemsDao;
    private List<StoryItem> storyItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story); // shift f6 - переименование
        setTitle("Story");

        AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
        artistWithStoryItemsDao = appDatabase.artistWithStoryItemsDao();

        Intent intent = this.getIntent();
        Integer artistId = intent.getIntExtra("artistId", -1);
        ArtistWithStoryItems artistWithStoryItems = artistWithStoryItemsDao.loadArtistWithStoryItems(artistId);
        storyItems = artistWithStoryItems.storyItems;


        TextView textView = findViewById(R.id.storyText);
        ImageView imageView = findViewById(R.id.storyImage);
        showStoryItem(textView, imageView);

        initProgressBarsRecyclerView(storyItems.size(), 5000);
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
//            if (mockData[counter].contains("http")) {
//                imageView.setVisibility(View.VISIBLE);
//                textView.setVisibility(View.GONE);
//                Picasso.get().load(mockData[counter]).into(imageView);
//            } else {
//                imageView.setVisibility(View.GONE);
//                textView.setVisibility(View.VISIBLE);
//                textView.setText(mockData[counter]);
//            }

            showStoryItem(textView, imageView);
        };
    }

    private void showStoryItem(TextView textView, ImageView imageView) {
        if (!storyItems.isEmpty()) {
            StoryItem storyItem = storyItems.get(counter);
            Picasso.get().load(storyItem.getPictureLink()).into(imageView);
            textView.setText(storyItem.getText());
        }
        counter = (counter + 1) % storyItems.size();
    }

    public void toNextSlideOfStory(View view) {
        progressBarRecyclerViewAdapter.makeCurrentProgressBarEqual100Percent();
    }

}
