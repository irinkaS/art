package com.example.irina.art.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.irina.art.R;
import com.example.irina.art.db.AppDatabase;
import com.example.irina.art.model.ArtistItem;
import com.example.irina.art.ui.adapter.ArtistButtonRecyclerViewAdapter;

import java.util.List;

public class ArtistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);
        setTitle("Artists");
        AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
        List<ArtistItem> artistItems = appDatabase.artistItemDao().getAll();
        initArtistsRecyclerView(artistItems);
    }

//    public void toArtist(View view){
//        Intent myIntent = new Intent(this, StoryActivity.class);
//        myIntent.putExtra("artistName", "Van Gogh"); //Optional parameters
//        this.startActivity(myIntent);
//    }


//        LinearLayout layout = (LinearLayout) findViewById(R.id.activityArtistsLayout);
//        final Context context = this;
//
//        for (final ArtistItem artist:
//                artistsList) {
//            Button button = new Button(this);
//            button.setText(artist.getArtistName());
//            layout.addView(button);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context, artist.getArtistName(), Toast.LENGTH_LONG).show();
//                }
//            });
//
//        }


    public void initArtistsRecyclerView(List<ArtistItem> artistItems){
        RecyclerView artistButtonRecyclerView = findViewById(R.id.artistButtonRecyclerView);
        artistButtonRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArtistButtonRecyclerViewAdapter artistButtonRecyclerViewAdapter = new
                ArtistButtonRecyclerViewAdapter(artistItems);
        artistButtonRecyclerView.setAdapter(artistButtonRecyclerViewAdapter);
        artistButtonRecyclerViewAdapter.notifyDataSetChanged();
    }
}
