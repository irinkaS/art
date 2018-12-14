package com.example.irina.art.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.irina.art.R;
import com.example.irina.art.dao.ArtistItemDao;
import com.example.irina.art.dao.ArtistWithStoryItemsDao;
import com.example.irina.art.dao.StoryItemDao;
import com.example.irina.art.model.ArtistItem;
import com.example.irina.art.model.StoryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

@Database(entities = {ArtistItem.class, StoryItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ArtistItemDao artistItemDao();

    public abstract ArtistWithStoryItemsDao artistWithStoryItemsDao();

    public abstract StoryItemDao storyItemDao();


    private static AppDatabase getAppDatabase(Context context) {
        return
                Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "artists-database")
                        // allow queries on the main thread.
                        // Don't do this on a real app! See PersistenceBasicSample for an example.
                        .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                AppDatabase db = getInstance(context);

                                populateWithTestData(db, context);
                            }
                        });
                    }
                })
                        .allowMainThreadQueries()
                        .build();
    }


    public synchronized static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = getAppDatabase(context);
        }
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }

    private static List<ArtistItem> addArtistItem(final AppDatabase db, List<ArtistItem> artistItems) {

        for (ArtistItem artist : artistItems) {
            db.artistItemDao().insert(artist);
        }

        return artistItems;
    }

    private static Map<String, List<StoryItem>> addStoryItems(final AppDatabase db, Map<String, List<StoryItem>> storyItemsByArtistName) {

        List<ArtistItem> artistItems = db.artistItemDao().getAll();
        for (ArtistItem artist : artistItems) {
            String artistName = artist.getArtistName();
            if (storyItemsByArtistName.containsKey(artistName)) {
                List<StoryItem> storyItems = storyItemsByArtistName.get(artistName);
                for (StoryItem storyItem : storyItems) {
                    storyItem.setArtistItemId(artist.getId());
                    db.storyItemDao().insert(storyItem);
                }
            }
        }

        return storyItemsByArtistName;
    }

    private static void populateWithTestData(AppDatabase db, Context context) {
        addArtistItem(db, createArtistsList(context));
        addStoryItems(db, createStoryItemsByArtistNameMap(context));
    }

    private static List<ArtistItem> createArtistsList(Context context) {
        List<ArtistItem> artistsList = new ArrayList<>();
        artistsList.add(new ArtistItem(null, context.getString(R.string.Gogh)));
        artistsList.add(new ArtistItem(null, context.getString(R.string.Malevich)));
        artistsList.add(new ArtistItem(null, context.getString(R.string.Mone)));
        artistsList.add(new ArtistItem(null, context.getString(R.string.Picasso)));
        return artistsList;
    }

    private static Map<String, List<StoryItem>> createStoryItemsByArtistNameMap(Context context) {
        Map<String, List<StoryItem>> storyItemsByArtistName = new HashMap<>();
        List<StoryItem> listOfStoryItems = new ArrayList<>();
        listOfStoryItems.add(new StoryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Autoportrait_de_Vincent_van_Gogh.JPG/210px-Autoportrait_de_Vincent_van_Gogh.JPG", "Gogh Portrait"));
        listOfStoryItems.add(new StoryItem("http://vangogen.ru/wp-content/uploads/2014/06/3851-763x1024.jpg", "Flowers"));
        storyItemsByArtistName.put(context.getString(R.string.Gogh), listOfStoryItems);
        listOfStoryItems = new ArrayList<>();
        storyItemsByArtistName.put(context.getString(R.string.Malevich), listOfStoryItems);
        //.add(new ArtistItem(null, context.getString(R.string.Gogh)));
        return storyItemsByArtistName;
    }
}
