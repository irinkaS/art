package com.example.irina.art.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.irina.art.R;
import com.example.irina.art.dao.ArtistItemDao;
import com.example.irina.art.model.ArtistItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {ArtistItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ArtistItemDao artistItemDao();

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

    private static void populateWithTestData(AppDatabase db, Context context) {
        addArtistItem(db, createArtistsList(context));
    }

    private static List<ArtistItem> createArtistsList(Context context) {
        List<ArtistItem> artistsList = new ArrayList<>();
        artistsList.add(new ArtistItem(null, context.getString(R.string.Gogh)));
        artistsList.add(new ArtistItem(null, context.getString(R.string.Malevich)));
        artistsList.add(new ArtistItem(null, context.getString(R.string.Mone)));
        artistsList.add(new ArtistItem(null, context.getString(R.string.Picasso)));
        return artistsList;
    }
}
